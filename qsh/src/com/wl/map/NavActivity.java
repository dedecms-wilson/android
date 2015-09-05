package com.wl.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NavActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nav);
	}
	
	/**
	 * 跳转
	 * @param btn
	 */
	public void jump(View btn){
		Intent intent = new Intent();
		switch (btn.getId()) {
		case R.id.btn_poi:
			intent.setClass(this, PoiSearchActivity.class);
			break;
		case R.id.btn_map:
			intent.setClass(this, MapActivity.class);
			break;	
		case R.id.btn_route:
			intent.setClass(this, RouteActivity.class);
			break;
		default:
			break;
		}
		startActivity(intent);
	}
}
