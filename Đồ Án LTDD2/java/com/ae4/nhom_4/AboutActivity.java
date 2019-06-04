package com.ae4.nhom_4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {
    Button btnback;
    MediaPlayer mediaPlayer;
    FrameLayout frameLayout;
    TextView textView;
    SharedPreferences saveData;
    SharedPreferences.Editor editor;
    String TongTien = "tongtien";
    String AmThanh = "amthanh";
    int tien = 3000;
    int amthanh = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "test.ttf");
        saveData = getSharedPreferences("Data_Game", Context.MODE_PRIVATE);
        textView = (TextView) findViewById(R.id.textrs);
        textView.setTypeface(typeface);
        frameLayout = (FrameLayout) findViewById(R.id.resatgame);
        btnback = (Button) findViewById(R.id.btnBack);
        mediaPlayer = MediaPlayer.create(this, R.raw.button9);

        if (saveData.getInt(TongTien, 3000) == 0 || saveData.getInt(TongTien, 3000) >= 1000000) {
            frameLayout.setVisibility(View.VISIBLE);
        } else {
            frameLayout.setVisibility(View.INVISIBLE);
        }

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                editor = saveData.edit();
                editor.putInt(TongTien, tien);
                editor.putInt(AmThanh, amthanh);
                editor.commit();
                Toast.makeText(AboutActivity.this, "Cho chú 3000$ chiến tiếp đi nào", Toast.LENGTH_SHORT).show();
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                Intent intent = new Intent(AboutActivity.this, Menu.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
