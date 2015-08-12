package com.wz.customview;

import com.wz.customview.view.SoundView;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;

public class mainActivity extends Activity {
	
	private AudioManager am;
	private SoundView soundview;
	
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        am = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        soundview = (SoundView) findViewById(R.id.sv);
    }
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
			am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
		}else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
			am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
		}
		int currentVolumn = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		soundview.setCurrentVolumn(currentVolumn);
		soundview.invalidate();
		return super.onKeyDown(keyCode, event);
	}

    
    
}