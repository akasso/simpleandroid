package uk.co.akasso.simpleandroidapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.res.AssetManager;

import java.io.IOException;

import uk.co.akasso.simpleandroidapplication.famework.impl.AndroidMusic;


public class MainActivity extends ActionBarActivity {

    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        try{
            AssetManager assetManager = getAssets();
            AssetFileDescriptor discriptor = assetManager.openFd("background.mp3");
            AndroidMusic music = new AndroidMusic(discriptor);
            music.setLooping(true);
            music.setVolume(1);
            music.play();
        }catch (IOException e){

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

    public void startGame(View view){
        //starts the game
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void audioSwitch(Context context)
    {
        if(audioManager.isMusicActive() == true){
            audioOff(context);
        }else{
            audioOn(context);
        }

    }

    public void audioOn(Context context){
        audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
    }

    public void audioOff(Context context){
        audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
    }


}
