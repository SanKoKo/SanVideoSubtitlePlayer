package net.sandevelopment.sansubtitleplayersample;

import android.Manifest;
import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.nio.Buffer;
import java.util.List;

import san.video.subtitle.player.SanVideoSubtitle;

public class MainActivity extends AppCompatActivity {
    private String TAG = this.getClass().getName()+":::";
    private TextView tv;
    File file;
    int currentPosition,duration;
    Handler handler;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        VideoView vv = findViewById(R.id.video);
         tv = findViewById(R.id.tv_subtitle);
         //file = new File(Environment.getExternalStorageDirectory(),"edperfect.srt");

        //vv.setVideoURI(Uri.parse("android.resource://"+getPackageName()+ "/"+R.raw.ed));
        vv.setMediaController(new MediaController(this));

         //mediaPlayer = MediaPlayer.create(this,R.raw.ed);
        mediaPlayer.start();
        duration = mediaPlayer.getDuration();
        currentPosition = mediaPlayer.getCurrentPosition();
        mediaPlayer.setVolume(0,0);
        vv.start();

        handler = new Handler();
        handler.postDelayed(runnable,100);


        /*try {
            SanVideoSubtitle.renderAndShowSubtitle(file,mediaPlayer,tv);
            SanVideoSubtitle.setSubtitleSpeed(-7);
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }

        SanVideoSubtitle.isRunning();
        SanVideoSubtitle.stopSubtitle();*/

    }
    String s;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            currentPosition = mediaPlayer.getCurrentPosition();
            if (currentPosition != duration) {

                try {

                     s = SanVideoSubtitle.renderAndShowSubtitle(file, currentPosition, duration);
                    SanVideoSubtitle.setSubtitleSpeed(-18);


                    if(s!=null){
                        tv.setText(s);
                    }
                } catch (InvalidObjectException e) {
                    e.printStackTrace();
                }
                handler.postDelayed(runnable,100);
            } else {
                handler.removeCallbacks(runnable);
            }
        }
    };
}
