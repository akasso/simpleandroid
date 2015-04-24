package uk.co.akasso.simpleandroidapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.content.res.AssetManager;
import android.widget.Button;

import java.io.IOException;

import uk.co.akasso.simpleandroidapplication.famework.impl.AndroidMusic;


public class MainActivity extends ActionBarActivity {

    AudioManager audioManager;
    AndroidMusic music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        try{
            AssetManager assetManager = getAssets();
            AssetFileDescriptor discriptor = assetManager.openFd("background.mp3");
            music = new AndroidMusic(discriptor);
            music.setLooping(true);
            music.setVolume(1);
            music.play();
        }catch (IOException e){

        }
    }

    public void startGame(View view){
        //starts the game
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void audioSwitch(View view)
    {
        Context context = this;
        audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        Button audioBtn = (Button) findViewById(R.id.btnMainAudio);

        if(audioManager.getStreamVolume(audioManager.STREAM_MUSIC) > 0){
            audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
            audioBtn.setText("Sound Off");
        }else{
            audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
            audioBtn.setText("Sound On");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        music.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        music.play();
    }
}
