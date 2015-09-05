package com.wl.ndk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class NdkActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);  
    }
    
    public void ndkCall(View button) {
    	Log.i("ndk call", getText());
    	Toast.makeText(this, getText(), 1000).show();
    }
    
    //native method
    public native String getText();
    
    //load so
    static {
    	System.loadLibrary("hello");
    }
}