package com.ae4.nhom_4;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class SupportActivity extends AppCompatActivity {
    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8;
    Button back;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        txt1= (TextView) findViewById(R.id.txt1);
        txt2= (TextView) findViewById(R.id.txt2);
        txt3= (TextView) findViewById(R.id.txt3);
        txt4= (TextView) findViewById(R.id.txt4);
        txt5= (TextView) findViewById(R.id.txt5);
        txt6= (TextView) findViewById(R.id.txt6);
        txt7= (TextView) findViewById(R.id.txt7);
        txt8= (TextView) findViewById(R.id.txt8);
        Typeface tp2=Typeface.createFromAsset(getAssets(),"font_nho.otf");
        txt1.setTypeface(tp2);
        txt2.setTypeface(tp2);
        txt3.setTypeface(tp2);
        txt4.setTypeface(tp2);
        txt5.setTypeface(tp2);
        txt6.setTypeface(tp2);
        txt7.setTypeface(tp2);
        txt8.setTypeface(tp2);
        back= (Button) findViewById(R.id.back);
        mediaPlayer=MediaPlayer.create(this,R.raw.button9);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SupportActivity.this,Menu.class);
                startActivity(intent);
                mediaPlayer.start();
                SupportActivity.this.finish();
            }
        });

    }
}
