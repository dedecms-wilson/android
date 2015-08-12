package com.wz.startmode;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;

public class Activity_startmodeActivity extends Activity {
    /** Called when the activity is first created. */
	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //startPhoneCall();
        startCamera();
    }

	private void startPhoneCall() {

		Intent DialIntent=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:13767957883"));
        DialIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(DialIntent);	
	}
	
	private void startCamera() {
		Intent intent = new Intent();
		
		intent.setAction(Intent.ACTION_CAMERA_BUTTON);
		//intent.setAction("android.intent.action.MAIN");
		startActivity(intent);
		startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
		
	}
}