package com.sxx.vlctest;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.Menu;
import android.view.MenuItem;

import com.sxx.vlctest.util.VLCInstance;

import org.videolan.libvlc.IVLCVout;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;

public class MainActivity extends AppCompatActivity {

    private String mUrl = "smb://smb:123@DPC/videos/30364.mpg";

    private MediaPlayer mMediaPlayer;
    private SurfaceView mSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        mSurfaceView = (SurfaceView) findViewById(R.id.player);

        mMediaPlayer = new MediaPlayer(VLCInstance.get());
        final IVLCVout vlcVout = mMediaPlayer.getVLCVout();

        vlcVout.detachViews();
        vlcVout.setVideoView(mSurfaceView);

        vlcVout.attachViews();

        mSurfaceView.setKeepScreenOn(true);

        play(mUrl);
    }

    private void play(String path) {
        try {
            Media media = new Media(VLCInstance.get(), Uri.parse(path));
            mMediaPlayer.setMedia(media);
            mMediaPlayer.play();
        }catch (Exception e) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
