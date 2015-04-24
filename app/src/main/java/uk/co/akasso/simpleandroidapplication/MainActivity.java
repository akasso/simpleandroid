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
