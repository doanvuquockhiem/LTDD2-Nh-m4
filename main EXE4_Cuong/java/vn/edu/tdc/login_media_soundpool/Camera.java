package vn.edu.tdc.login_media_soundpool;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class Camera extends Activity {
    protected Button imgButton;
    protected ImageView image;
    protected TextView field;
    protected Button wallpapaerBtn;
    protected boolean taken;
    protected boolean imgCapFlag;
    protected static final String PHOTO_TAKEN = "photo_taken";
    protected String path;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        image = (ImageView) findViewById(R.id.image);
        field = (TextView) findViewById(R.id.field);
        imgButton = (Button) findViewById(R.id.button);
        wallpapaerBtn = (Button) findViewById(R.id.button1);
        imgButton.setOnClickListener(new ButtonClickHandler());
        wallpapaerBtn.setOnClickListener(new ButtonClickHandler1());
        path = Environment.getExternalStorageDirectory()
                + "/images/make_machine_example.jpg";

    }

    public class ButtonClickHandler implements View.OnClickListener {
        public void onClick(View view) {
            startCameraActivity();
        }
    }

    protected void startCameraActivity() {
        File file = new File(path);
        Uri outputFileUri = Uri.fromFile(file);
        Intent intent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 0:
                break;
            case -1:
                onPhotoTaken();
                break;
        }
    }

    protected void onPhotoTaken() {
        taken = true;
        imgCapFlag = true;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        image.setImageBitmap(bitmap);
        field.setVisibility(View.GONE);
    }

    public class ButtonClickHandler1 implements View.OnClickListener {
        public void onClick(View view) {
            try {
                if (imgCapFlag) {
                    BitmapDrawable drawable = (BitmapDrawable) image
                            .getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    WallpaperManager wallpaperManager = WallpaperManager
                            .getInstance(Camera.this);
                    wallpaperManager.setBitmap(bitmap);
                    Toast.makeText(Camera.this,
                            "Wallpaper set : )", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(
                            Camera.this,
                            "You must capture photo before you try to set wallpaper!",
                    Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(Camera.PHOTO_TAKEN, taken);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.getBoolean(Camera.PHOTO_TAKEN)) {
            onPhotoTaken();
        }
    }
}
