package com.example.gameanim;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageButton imgBell;
    ImageView imgMario;
    ArrayList<Song> arraySong;
    int position = 0;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddSong();
        KhoiTaoMedia();

        imgBell = (ImageButton) findViewById(R.id.btnPlay);
        final Animation animBell = AnimationUtils.loadAnimation(this, R.anim.anim_bellring);
        imgMario = (ImageView) findViewById(R.id.imgMario);
        final Animation animGoto = AnimationUtils.loadAnimation(this, R.anim.anim_goto);
        imgBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.startAnimation(animBell);
                imgMario.startAnimation(animGoto);
                mediaPlayer.start();

            }
        });
    }
    private void AddSong() {
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Jingle Bell", R.raw.gogog));
    }

    private void KhoiTaoMedia(){
        mediaPlayer = MediaPlayer.create(MainActivity.this, arraySong.get(position).getFile());
    }
}