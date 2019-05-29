package vn.edu.tdc.login_media_soundpool;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;
import android.os.Handler;
import java.util.logging.LogRecord;

import pl.droidsonroids.gif.GifImageView;

public class Media extends AppCompatActivity {
//    private MediaPlayer mediaPlayer;
//    public TextView songName, duration;
//    private double timeElapsed = 0, finalTime = 0;
//    private int forwardTime = 2000, backwardTime = 2000;
//    private Handler durationHandler = new Handler();
//    private SeekBar seekbar;
//    private ImageButton btn;
    private GifImageView gif;
    private ImageView img;




    private ImageButton b1,b2,b3,b4;
    private ImageView iv;
    private MediaPlayer mediaPlayer;

    private double startTime = 0;
    private double finalTime = 0;

    private Handler myHandler = new Handler();;
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private SeekBar seekbar;
    private TextView tx1,tx2,tx3;

    public static int oneTimeOnly = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set the layout of the Activity
        setContentView(R.layout.media_activity);

        gif = (GifImageView) findViewById(R.id.icon);
        img = (ImageView) findViewById(R.id.imageView);
        b1 = (ImageButton) findViewById(R.id.button);
        b2 = (ImageButton) findViewById(R.id.button2);
        b3 = (ImageButton)findViewById(R.id.button3);
        b4 = (ImageButton)findViewById(R.id.button4);
        iv = (ImageView)findViewById(R.id.imageView);

        tx1 = (TextView)findViewById(R.id.textView2);
        tx2 = (TextView)findViewById(R.id.textView3);
        tx3 = (TextView)findViewById(R.id.textView4);
        tx3.setText("Song.mp3");

        mediaPlayer = MediaPlayer.create(this, R.raw.b1);
        seekbar = (SeekBar)findViewById(R.id.seekBar);
        seekbar.setClickable(false);
        b2.setEnabled(false);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation icon = AnimationUtils.loadAnimation(Media.this,R.anim.lefttoright);
                Animation animation =  AnimationUtils.loadAnimation(Media.this,R.anim.rotate);
                gif.startAnimation(icon);
                img.startAnimation(animation);
                mediaPlayer.start();

                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();

                if (oneTimeOnly == 0) {
                    seekbar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }

                tx2.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        finalTime)))
                );

                tx1.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                        startTime)))
                );

                seekbar.setProgress((int)startTime);
                myHandler.postDelayed(UpdateSongTime,100);
                b2.setEnabled(true);
                b3.setEnabled(false);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                b2.setEnabled(false);
                b3.setEnabled(true);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;

                if((temp+forwardTime)<=finalTime){
                    startTime = startTime + forwardTime;
                    mediaPlayer.seekTo((int) startTime);
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int)startTime;

                if((temp-backwardTime)>0){
                    startTime = startTime - backwardTime;
                    mediaPlayer.seekTo((int) startTime);
                }
            }
        });
        //initialize views
        //initializeViews();
    }

//    public void initializeViews(){
//        btn = (ImageButton) findViewById(R.id.media_play);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                img = (ImageView) findViewById(R.id.mp3Image);
//                Animation animation =  AnimationUtils.loadAnimation(Media.this,R.anim.rotate);
//                img.startAnimation(animation);
//            }
//        });
//        songName = (TextView) findViewById(R.id.songName);
//        mediaPlayer = MediaPlayer.create(this, R.raw.b1);
//        finalTime = mediaPlayer.getDuration();
//        duration = (TextView) findViewById(R.id.songDuration);
//        seekbar = (SeekBar) findViewById(R.id.seekBar);
//        songName.setText("Sample_Song.mp3");
//
//        seekbar.setMax((int) finalTime);
//        seekbar.setClickable(false);
//    }
//
//    // play mp3 song
//    public void play(View view) {
//        mediaPlayer.start();
//        timeElapsed = mediaPlayer.getCurrentPosition();
//        seekbar.setProgress((int) timeElapsed);
//        durationHandler.postDelayed(updateSeekBarTime, 100);
//    }
//
//    //handler to change seekBarTime
//    private Runnable updateSeekBarTime = new Runnable() {
//        public void run() {
//            //get current position
//            timeElapsed = mediaPlayer.getCurrentPosition();
//            //set seekbar progress
//            seekbar.setProgress((int) timeElapsed);
//            //set time remaing
//            double timeRemaining = finalTime - timeElapsed;
//            duration.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining), TimeUnit.MILLISECONDS.toSeconds((long) timeRemaining) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining))));
//
//            //repeat yourself that again in 100 miliseconds
//            durationHandler.postDelayed(this, 100);
//        }
//    };
//
//    // pause mp3 song
//    public void pause(View view) {
//        mediaPlayer.pause();
//    }
//
//    // go forward at forwardTime seconds
//    public void forward(View view) {
//        //check if we can go forward at forwardTime seconds before song endes
//        if ((timeElapsed + forwardTime) <= finalTime) {
//            timeElapsed = timeElapsed + forwardTime;
//
//            //seek to the exact second of the track
//            mediaPlayer.seekTo((int) timeElapsed);
//        }
//    }
//
//    // go backwards at backwardTime seconds
//    public void rewind(View view) {
//        //check if we can go back at backwardTime seconds after song starts
//        if ((timeElapsed - backwardTime) > 0) {
//            timeElapsed = timeElapsed - backwardTime;
//
//            //seek to the exact second of the track
//            mediaPlayer.seekTo((int) timeElapsed);
//        }
//    }
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            tx1.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekbar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };
}
