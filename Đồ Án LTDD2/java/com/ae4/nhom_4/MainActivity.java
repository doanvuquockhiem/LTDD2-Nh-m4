package com.ae4.nhom_4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Integer[] dsQuan = {R.drawable.ca1,
            R.drawable.cua1,
            R.drawable.bau1,
            R.drawable.nai1,
            R.drawable.tom1,
            R.drawable.ga1};
    AnimationDrawable xs1, xs2, xs3;
    ImageView xucsac1, xucsac2, xucsac3;
    Button btnxucsac;
    int giatri1, giatri2, giatri3;
    Random randomxs1, randomxs2, randomxs3;
    MediaPlayer xucsac, tick, untick, backgroundsound, bayve;
    Button tangtienbau, tangtiencua, tangtienca, tangtienhuou, tangtienga, tangtientom;
    Button giamtienbau, giamtiencua, giamtienca, giamtienhuou, giamtienga, giamtientom;
    TextView tiendatbau, tiendatcua, tiendatca, tiendathuou, tiendatga, tiendattom;
    TextView txttienhienthi;
    ImageView loa;
    Button btnloa, backbtn;
    int demamthanh = 0;
    int tienga = 0, tienca = 0, tienbau = 0, tientom = 0, tienhuou = 0, tiencua = 0;
    int tienthuong;
    Animation anim, animmat;
    TextView animtxt, thuacuoc1;
    LinearLayout themtien, thuacuoctxt, thuacuocmain;
    ImageView imgdau;
    int tienthuongtxt = 0;
    String TongTien = "tongtien";
    String AmThanh = "amthanh";
    SharedPreferences save_Data;
    SharedPreferences.Editor editor;
    int tiencongtong;
    MediaPlayer you_win,game_over,matien,duoctien;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        AnhXa();
        save_Data = getSharedPreferences("Data_Game", Context.MODE_PRIVATE);
        editor = save_Data.edit();
        tienthuong = save_Data.getInt(TongTien, 3000);
        txttienhienthi.setText("" + tienthuong);
        demamthanh = save_Data.getInt(AmThanh, 0);

        tiencongtong = tienthuong;

        LuuData();
        tick = MediaPlayer.create(this, R.raw.button1);
        untick = MediaPlayer.create(this, R.raw.button19);
        bayve = MediaPlayer.create(this, R.raw.button9);

        matien=MediaPlayer.create(this,R.raw.matien);
        duoctien=MediaPlayer.create(this,R.raw.duoctien);

        backgroundsound = MediaPlayer.create(this, R.raw.amthanh);
        backgroundsound.setLooping(true);
        backgroundsound.start();

        you_win=MediaPlayer.create(this,R.raw.you_win);
        game_over=MediaPlayer.create(this,R.raw.game_over);

        XuLyThuaCuoc();
        XuLyAmThanhNen();
        btnloa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                demamthanh++;
                XuLyAmThanhNen();
            }
        });

        anim = AnimationUtils.loadAnimation(this, R.anim.anim_thuong);
        animmat = AnimationUtils.loadAnimation(this, R.anim.anim_mat);
        XuLyGiamTienCuoc();
        XuLyTangTienCuoc();
    }

    private void LuuData() {
        editor.putInt(TongTien, tienthuong);
        editor.putInt(AmThanh, demamthanh);
        editor.commit();
    }

    private void XuLyAmThanhNen() {
        LuuData();
        if (demamthanh % 2 == 0) {
            backgroundsound.start();
            loa.setImageResource(R.drawable.shound);
        } else {
            backgroundsound.pause();
            loa.setImageResource(R.drawable.unshound);
        }
    }

    private void XuLyGiamTienCuoc() {
        giamtienbau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tienbau > 0) {
                    tiencongtong += 100;
                    tienbau -= 100;
                    tick.start();
                    tiendatbau.setText("" + tienbau);
                } else {
                    untick.start();
                    Toast.makeText(MainActivity.this, "Không đặt giá trị âm", Toast.LENGTH_SHORT).show();
                }
            }
        });
        giamtientom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tientom > 0) {
                    tiencongtong += 100;
                    tientom -= 100;
                    tick.start();
                    tiendattom.setText("" + tientom);
                } else {
                    untick.start();
                    Toast.makeText(MainActivity.this, "Không đặt giá trị âm", Toast.LENGTH_SHORT).show();
                }
            }
        });
        giamtiencua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tiencua > 0) {
                    tiencongtong += 100;
                    tiencua -= 100;
                    tick.start();
                    tiendatcua.setText("" + tiencua);
                } else {
                    untick.start();
                    Toast.makeText(MainActivity.this, "Không đặt giá trị âm", Toast.LENGTH_SHORT).show();
                }
            }
        });
        giamtienca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tienca > 0) {
                    tiencongtong += 100;
                    tienca -= 100;
                    tick.start();
                    tiendatca.setText("" + tienca);
                } else {
                    untick.start();
                    Toast.makeText(MainActivity.this, "Không đặt giá trị âm", Toast.LENGTH_SHORT).show();
                }
            }
        });
        giamtienga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tienga > 0) {
                    tiencongtong += 100;
                    tienga -= 100;
                    tick.start();
                    tiendatga.setText("" + tienga);
                } else {
                    untick.start();
                    Toast.makeText(MainActivity.this, "Không đặt giá trị âm", Toast.LENGTH_SHORT).show();
                }
            }
        });
        giamtienhuou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tienhuou > 0) {
                    tiencongtong += 100;
                    tienhuou -= 100;
                    tick.start();
                    tiendathuou.setText("" + tienhuou);
                } else {
                    untick.start();
                    Toast.makeText(MainActivity.this, "Không đặt giá trị âm", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void XuLyTangTienCuoc() {
        tangtienbau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tiencongtong>0) {
                    tiencongtong-=100;
                    tienbau += 100;
                    tick.start();
                    tiendatbau.setText("" + tienbau);
                } else {
                    untick.start();
                    Toast.makeText(MainActivity.this, "Quá giới hạn cho phép", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tangtiencua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tiencongtong>0) {
                    tiencongtong-=100;
                    tiencua += 100;
                    tick.start();
                    tiendatcua.setText("" + tiencua);
                } else {
                    untick.start();
                    Toast.makeText(MainActivity.this, "Quá giới hạn cho phép", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tangtienca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tiencongtong>0) {
                    tiencongtong-=100;
                    tienca += 100;
                    tick.start();
                    tiendatca.setText("" + tienca);
                } else {
                    untick.start();
                    Toast.makeText(MainActivity.this, "Quá giới hạn cho phép", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tangtienga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tiencongtong>0) {
                    tiencongtong-=100;
                    tienga += 100;
                    tick.start();
                    tiendatga.setText("" + tienga);
                } else {
                    untick.start();
                    Toast.makeText(MainActivity.this, "Quá giới hạn cho phép", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tangtientom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tiencongtong>0) {
                    tiencongtong-=100;
                    tientom += 100;
                    tick.start();
                    tiendattom.setText("" + tientom);
                } else {
                    untick.start();
                    Toast.makeText(MainActivity.this, "Quá giới hạn cho phép", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tangtienhuou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tiencongtong>0) {
                    tiencongtong-=100;
                    tienhuou += 100;
                    tick.start();
                    tiendathuou.setText("" + tienhuou);
                } else {
                    untick.start();
                    Toast.makeText(MainActivity.this, "Quá giới hạn cho phép", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Randomgt1() {
        randomxs1 = new Random();
        int rd1 = randomxs1.nextInt(6);
        switch (rd1) {
            case 0:
                xucsac1.setImageResource(dsQuan[0]);
                giatri1 = rd1;
                break;
            case 1:
                xucsac1.setImageResource(dsQuan[1]);
                giatri1 = rd1;
                break;
            case 2:
                xucsac1.setImageResource(dsQuan[2]);
                giatri1 = rd1;
                break;
            case 3:
                xucsac1.setImageResource(dsQuan[3]);
                giatri1 = rd1;
                break;
            case 4:
                xucsac1.setImageResource(dsQuan[4]);
                giatri1 = rd1;
                break;
            case 5:
                xucsac1.setImageResource(dsQuan[5]);
                giatri1 = rd1;
                break;
        }
    }

    private void Randomgt2() {
        randomxs2 = new Random();
        int rd2 = randomxs2.nextInt(6);
        switch (rd2) {
            case 0:
                xucsac2.setImageResource(dsQuan[0]);
                giatri2 = rd2;
                break;
            case 1:
                xucsac2.setImageResource(dsQuan[1]);
                giatri2 = rd2;
                break;
            case 2:
                xucsac2.setImageResource(dsQuan[2]);
                giatri2 = rd2;
                break;
            case 3:
                xucsac2.setImageResource(dsQuan[3]);
                giatri2 = rd2;
                break;
            case 4:
                xucsac2.setImageResource(dsQuan[4]);
                giatri2 = rd2;
                break;
            case 5:
                xucsac2.setImageResource(dsQuan[5]);
                giatri2 = rd2;
                break;
        }
    }

    private void Randomgt3() {
        randomxs3 = new Random();
        int rd3 = randomxs3.nextInt(6);
        switch (rd3) {
            case 0:
                xucsac3.setImageResource(dsQuan[0]);
                giatri3 = rd3;
                break;
            case 1:
                xucsac3.setImageResource(dsQuan[1]);
                giatri3 = rd3;
                break;
            case 2:
                xucsac3.setImageResource(dsQuan[2]);
                giatri3 = rd3;
                break;
            case 3:
                xucsac3.setImageResource(dsQuan[3]);
                giatri3 = rd3;
                break;
            case 4:
                xucsac3.setImageResource(dsQuan[4]);
                giatri3 = rd3;
                break;
            case 5:
                xucsac3.setImageResource(dsQuan[5]);
                giatri3 = rd3;
                break;
        }
    }

    private void XucSac() {

        xucsac1.setImageResource(R.drawable.animation_item);
        xucsac2.setImageResource(R.drawable.animation_item_1);
        xucsac3.setImageResource(R.drawable.animation_item_2);

        xs1 = (AnimationDrawable) xucsac1.getDrawable();
        xs2 = (AnimationDrawable) xucsac2.getDrawable();
        xs3 = (AnimationDrawable) xucsac3.getDrawable();
        xs1.start();
        xs2.start();
        xs3.start();
        xucsac = MediaPlayer.create(this, R.raw.laclaclac);
        xucsac.start();
        int time_out = 1100;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                xucsac.stop();
                Randomgt1();
                Randomgt2();
                Randomgt3();
                TraKetQuaTrungThuong();
                tiencongtong=tienthuong;

                tangtienbau.setVisibility(View.VISIBLE);
                tangtiencua.setVisibility(View.VISIBLE);
                tangtienca.setVisibility(View.VISIBLE);
                tangtienga.setVisibility(View.VISIBLE);
                tangtienhuou.setVisibility(View.VISIBLE);
                tangtientom.setVisibility(View.VISIBLE);

                giamtienbau.setVisibility(View.VISIBLE);
                giamtiencua.setVisibility(View.VISIBLE);
                giamtienca.setVisibility(View.VISIBLE);
                giamtienga.setVisibility(View.VISIBLE);
                giamtienhuou.setVisibility(View.VISIBLE);
                giamtientom.setVisibility(View.VISIBLE);

                LuuData();
                tienbau = 0;
                tienhuou = 0;
                tiencua = 0;
                tienga = 0;
                tienca = 0;
                tientom = 0;
                tiendathuou.setText("" + 0);
                tiendatga.setText("" + 0);
                tiendatca.setText("" + 0);
                tiendatcua.setText("" + 0);
                tiendatbau.setText("" + 0);
                tiendattom.setText("" + 0);
                btnxucsac.setVisibility(View.VISIBLE);
            }
        }, time_out);
    }

    private void TraKetQuaTrungThuong() {
        XuLyCa();
        XuLyCua();
        XuLyBau();
        XuLyHuou();
        XuLYTom();
        XuLyGa();

        XuLyThuaCuoc();

        if (tienthuongtxt > 0) {
            duoctien.start();
            imgdau.setImageResource(R.drawable.cong);
        } else if (tienthuongtxt < 0) {
            matien.start();
            imgdau.setImageResource(R.drawable.tru);
            tienthuongtxt = -1 * tienthuongtxt;
        }
        animtxt.setText("" + tienthuongtxt);
        themtien.setVisibility(View.VISIBLE);
        themtien.startAnimation(anim);

        txttienhienthi.setText("" + tienthuong);
        tienthuongtxt = 0;
    }

    private void XuLyThuaCuoc() {
        if (tienthuong == 0 || tienthuong >= 1000000) {
            thuacuoctxt.setVisibility(View.VISIBLE);
            thuacuocmain.setVisibility(View.INVISIBLE);
            if (tienthuong == 0) {
                if (demamthanh%2==0) {
                    backgroundsound.stop();
                }
                game_over.start();
                thuacuoc1.setText("PHÁ SẢN!");
            } else {
                if(demamthanh%2==0) {
                    backgroundsound.stop();
                }
                you_win.start();
                thuacuoc1.setText("Thắng Rồi!");
            }
        } else {
            thuacuocmain.setVisibility(View.VISIBLE);
            thuacuoctxt.setVisibility(View.INVISIBLE);
        }
    }

    private void XuLyGa() {
        if (giatri1 == 5) {
            tienthuong += tienga;
            tienthuongtxt += tienga;
        }
        if (giatri2 == 5) {
            tienthuong += tienga;
            tienthuongtxt += tienga;
        }
        if (giatri3 == 5) {
            tienthuong += tienga;
            tienthuongtxt += tienga;
        }
        if (giatri1 != 5 && giatri2 != 5 && giatri3 != 5) {
            tienthuong -= tienga;
            tienthuongtxt -= tienga;
        }
    }

    private void XuLYTom() {
        if (giatri1 == 4) {
            tienthuong += tientom;
            tienthuongtxt += tientom;
        }
        if (giatri2 == 4) {
            tienthuong += tientom;
            tienthuongtxt += tientom;
        }
        if (giatri3 == 4) {
            tienthuong += tientom;
            tienthuongtxt += tientom;
        }
        if (giatri1 != 4 && giatri2 != 4 && giatri3 != 4) {
            tienthuong -= tientom;
            tienthuongtxt -= tientom;
        }
    }

    private void XuLyHuou() {
        if (giatri1 == 3) {
            tienthuong += tienhuou;
            tienthuongtxt += tienhuou;
        }
        if (giatri2 == 3) {
            tienthuong += tienhuou;
            tienthuongtxt += tienhuou;
        }
        if (giatri3 == 3) {
            tienthuong += tienhuou;
            tienthuongtxt += tienhuou;
        }
        if (giatri1 != 3 && giatri2 != 3 && giatri3 != 3) {
            tienthuong -= tienhuou;
            tienthuongtxt -= tienhuou;
        }
    }

    private void XuLyBau() {
        if (giatri1 == 2) {
            tienthuong += tienbau;
            tienthuongtxt += tienbau;
        }
        if (giatri2 == 2) {
            tienthuong += tienbau;
            tienthuongtxt += tienbau;
        }
        if (giatri3 == 2) {
            tienthuong += tienbau;
            tienthuongtxt += tienbau;
        }
        if (giatri1 != 2 && giatri2 != 2 && giatri3 != 2) {
            tienthuong -= tienbau;
            tienthuongtxt -= tienbau;
        }
    }

    private void XuLyCua() {
        if (giatri1 == 1) {
            tienthuong += tiencua;
            tienthuongtxt += tiencua;
        }
        if (giatri2 == 1) {
            tienthuong += tiencua;
            tienthuongtxt += tiencua;
        }
        if (giatri3 == 1) {
            tienthuong += tiencua;
            tienthuongtxt += tiencua;
        }
        if (giatri1 != 1 && giatri2 != 1 && giatri3 != 1) {
            tienthuong -= tiencua;
            tienthuongtxt -= tiencua;
        }
    }

    private void XuLyCa() {
        if (giatri1 == 0) {
            tienthuong += tienca;
            tienthuongtxt += tienca;
        }
        if (giatri2 == 0) {
            tienthuong += tienca;
            tienthuongtxt += tienca;
        }
        if (giatri3 == 0) {
            tienthuong += tienca;
            tienthuongtxt += tienca;
        }
        if (giatri1 != 0 && giatri2 != 0 && giatri3 != 0) {
            tienthuong -= tienca;
            tienthuongtxt -= tienca;
        }
    }

    private void AnhXa() {
        xucsac1 = (ImageView) findViewById(R.id.xucsac1);
        xucsac2 = (ImageView) findViewById(R.id.xucsac2);
        xucsac3 = (ImageView) findViewById(R.id.xucsac3);

        xucsac1.setImageResource(R.drawable.cua);
        xucsac2.setImageResource(R.drawable.cua);
        xucsac3.setImageResource(R.drawable.cua);

        btnxucsac = (Button) findViewById(R.id.btnxucsac);
        txttienhienthi = (TextView) findViewById(R.id.txttienhienthi);
        txttienhienthi.setText("" + tienthuong);
        loa = (ImageView) findViewById(R.id.loa);
        btnloa = (Button) findViewById(R.id.btnloa);
        backbtn = (Button) findViewById(R.id.backbtn);

        tangtienbau = (Button) findViewById(R.id.tangtienbau);
        tangtienca = (Button) findViewById(R.id.tangtienca);
        tangtienga = (Button) findViewById(R.id.tangtienga);
        tangtiencua = (Button) findViewById(R.id.tangtiencua);
        tangtientom = (Button) findViewById(R.id.tangtientom);
        tangtienhuou = (Button) findViewById(R.id.tangtienhuou);

        giamtienbau = (Button) findViewById(R.id.giamtienbau);
        giamtienga = (Button) findViewById(R.id.giamtienga);
        giamtienca = (Button) findViewById(R.id.giamtienca);
        giamtiencua = (Button) findViewById(R.id.giamtiencua);
        giamtientom = (Button) findViewById(R.id.giamtientom);
        giamtienhuou = (Button) findViewById(R.id.giamtienhuou);

        tiendatbau = (TextView) findViewById(R.id.tiendatbau);
        tiendatga = (TextView) findViewById(R.id.tiendatga);
        tiendatca = (TextView) findViewById(R.id.tiendatca);
        tiendatcua = (TextView) findViewById(R.id.tiendatcua);
        tiendattom = (TextView) findViewById(R.id.tiendattom);
        tiendathuou = (TextView) findViewById(R.id.tiendathuou);

        animtxt = (TextView) findViewById(R.id.animtxt);
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "crackman.ttf");
        animtxt.setTypeface(typeface1);
        themtien = (LinearLayout) findViewById(R.id.themtien);
        thuacuoctxt = (LinearLayout) findViewById(R.id.thuacuoctxt);
        thuacuocmain= (LinearLayout) findViewById(R.id.thuacuocmain);
        thuacuoc1 = (TextView) findViewById(R.id.thuacuoc1);
        imgdau = (ImageView) findViewById(R.id.imgdau);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backgroundsound.stop();
                LuuData();
                Intent back = new Intent(MainActivity.this, Menu.class);
                bayve.start();
                startActivity(back);
                MainActivity.this.finish();
            }
        });
        btnxucsac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tangtienbau.setVisibility(View.INVISIBLE);
                tangtiencua.setVisibility(View.INVISIBLE);
                tangtienca.setVisibility(View.INVISIBLE);
                tangtienga.setVisibility(View.INVISIBLE);
                tangtienhuou.setVisibility(View.INVISIBLE);
                tangtientom.setVisibility(View.INVISIBLE);

                giamtienbau.setVisibility(View.INVISIBLE);
                giamtiencua.setVisibility(View.INVISIBLE);
                giamtienca.setVisibility(View.INVISIBLE);
                giamtienga.setVisibility(View.INVISIBLE);
                giamtienhuou.setVisibility(View.INVISIBLE);
                giamtientom.setVisibility(View.INVISIBLE);

                XucSac();
                btnxucsac.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(demamthanh%2==0) {
            backgroundsound.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(demamthanh%2==0) {
            backgroundsound.pause();
        }
    }
}
