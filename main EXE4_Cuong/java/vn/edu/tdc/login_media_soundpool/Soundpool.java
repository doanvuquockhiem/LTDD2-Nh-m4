package vn.edu.tdc.login_media_soundpool;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;

public class Soundpool extends Activity {
    private SoundPool soundPool;
    private AudioManager audioManager;

    // Số luồng âm thanh phát ra tối đa.
    private static final int MAX_STREAMS = 5;
    // Chọn loại luồng âm thanh để phát nhạc.
    private static final int streamType = AudioManager.STREAM_MUSIC;
    private boolean loaded;
    private int soundIdDestroy;
    private int soundIdGun;
    private float volume;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soundpool_activity);
        // Đối tượng AudioManager sử dụng để điều chỉnh âm lượng.
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Chỉ số âm lượng hiện tại của loại luồng nhạc cụ thể (streamType).
        float currentVolumeIndex = (float) audioManager.getStreamVolume(streamType);


        // Chỉ số âm lượng tối đa của loại luồng nhạc cụ thể (streamType).
        float maxVolumeIndex  = (float) audioManager.getStreamMaxVolume(streamType);

        // Âm lượng  (0 --> 1)
        this.volume = currentVolumeIndex / maxVolumeIndex;

        // Cho phép thay đổi âm lượng các luồng kiểu 'streamType' bằng các nút
        // điều khiển của phần cứng.
        this.setVolumeControlStream(streamType);

        // Với phiên bản Android SDK >= 21
        if (Build.VERSION.SDK_INT >= 21 ) {
            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool.Builder builder= new SoundPool.Builder();
            builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);

            this.soundPool = builder.build();
        }
        // Với phiên bản Android SDK < 21
        else {
            // SoundPool(int maxStreams, int streamType, int srcQuality)
            this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }

        // Sự kiện SoundPool đã tải lên bộ nhớ thành công.
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });

        // Tải file nhạc tiếng vật thể bị phá hủy (destroy.war) vào SoundPool.
        this.soundIdDestroy = this.soundPool.load(this, R.raw.destroy,1);

        // Tải file nhạc tiếng súng (gun.wav) vào SoundPool.
        this.soundIdGun = this.soundPool.load(this, R.raw.gun,1);

    }
    // Khi người dùng nhấn vào button "Gun".
    public void playSoundGun(View view)  {
        if(loaded)  {
            float leftVolumn = volume;
            float rightVolumn = volume;
            // Phát âm thanh tiếng súng. Trả về ID của luồng mới phát ra.
            int streamId = this.soundPool.play(this.soundIdGun,leftVolumn, rightVolumn, 1, 0, 1f);
            Intent intent = new Intent(Soundpool.this, Camera.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }
    }

    // Khi người dùng nhấn vào button "Destroy".
    public void playSoundDestroy(View view)  {
        if(loaded)  {
            float leftVolumn = volume;
            float rightVolumn = volume;

            // Phát âm thanh tiếng vật thể bị phá hủy.
            // Trả về ID của luồng mới phát ra.
            int streamId = this.soundPool.play(this.soundIdDestroy,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }
}
