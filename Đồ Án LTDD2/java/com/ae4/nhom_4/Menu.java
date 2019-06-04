package com.ae4.nhom_4;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class Menu extends AppCompatActivity implements View.OnClickListener {
    MediaPlayer tick_music;
    Typeface typeface;
    FrameLayout frameLayoutplay,
            frameLayoutabout,
            frameLayoutsuport,
            frameLayoutexit;
    TextView textplay,
            textabout,
            textsuport,
            textexit;
    Animation animation;
    ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AnhXa();
    }

    private void AnhXa() {
        animation= AnimationUtils.loadAnimation(this,R.anim.anim_icon);
        typeface=Typeface.createFromAsset(getAssets(),"test.ttf");

        icon= (ImageView) findViewById(R.id.icon);
        icon.startAnimation(animation);

        frameLayoutplay= (FrameLayout) findViewById(R.id.framLayoutplay);
        frameLayoutabout= (FrameLayout) findViewById(R.id.framLayoutabout);
        frameLayoutsuport= (FrameLayout) findViewById(R.id.framLayoutsuport);
        frameLayoutexit= (FrameLayout) findViewById(R.id.framLayoutexit);

        textplay= (TextView) findViewById(R.id.textplay);
        textsuport= (TextView) findViewById(R.id.textsuport);
        textabout= (TextView) findViewById(R.id.textabout);
        textexit= (TextView) findViewById(R.id.textexit);

        textplay.setTypeface(typeface);
        textabout.setTypeface(typeface);
        textsuport.setTypeface(typeface);
        textexit.setTypeface(typeface);

        tick_music=MediaPlayer.create(this,R.raw.button9);

        frameLayoutplay.setOnClickListener(this);
        frameLayoutabout.setOnClickListener(this);
        frameLayoutexit.setOnClickListener(this);
        frameLayoutsuport.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        tick_music.start();

        if(view==frameLayoutabout) {
            Intent intent2=new Intent(Menu.this,AboutActivity.class);
            startActivity(intent2);
        }else if(view==frameLayoutexit) {
            Menu.this.finish();
        }else if(view==frameLayoutsuport) {
            Intent intent2=new Intent(Menu.this,SupportActivity.class);
            startActivity(intent2);
        }else if(view==frameLayoutplay) {
            Intent intent2=new Intent(Menu.this,MainActivity.class);
            startActivity(intent2);
        }
        Menu.this.finish();
    }
}
