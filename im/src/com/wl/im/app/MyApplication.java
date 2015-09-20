package com.wl.im.app;

import org.jivesoftware.smack.android.AndroidSmackInitializer;

import android.app.Application;

public class MyApplication extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		new AndroidSmackInitializer().initialize();
		
	}

}
