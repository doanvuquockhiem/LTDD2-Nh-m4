package com.example.exe3;

import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.buton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpAnimation();

            }
        });

    }

    private void setUpAnimation(){
        final ImageView textAnimation = (ImageView) findViewById(R.id.text_animation);
        final ImageView chuot = (ImageView) findViewById(R.id.chuot);

        ValueAnimator valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(
                this, R.animator.value_animator_ex);

        ValueAnimator run = (ValueAnimator) AnimatorInflater.loadAnimator(
                this, R.animator.value_run);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                textAnimation.setTranslationY(progress);
                chuot.setTranslationY(progress);

            }
        });
        valueAnimator.start();
    }



}

