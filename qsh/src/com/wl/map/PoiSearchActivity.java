package com.wl.map;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.SupportMapFragment;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

public class PoiSearchActivity extends FragmentActivity  implements OnGetPoiSearchResultListener {
	
	private PoiSearch mPoiSearch = null;
	private EditText city;
	private AutoCompleteTextView searchkey;
	MapView mMapView = null; 
	BaiduMap mBaiduMap = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_poisearch);
		
		city = (EditText) findViewById(R.id.city);
		searchkey = (AutoCompleteTextView) findViewById(R.id.searchkey);
		
		mPoiSearch  = PoiSearch.newInstance();
		mPoiSearch.setOnGetPoiSearchResultListener(this);
		
		SupportMapFragment mFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		mBaiduMap = mFragment.getBaiduMap();
	
		
	}
	
	public void search(View btn) {
		PoiCitySearchOption options = (new PoiCitySearchOption())  
	    .city(city.getText().toString())  
	    .keyword(searchkey.getText().toString())  
	    .pageNum(10);
		mPoiSearch.searchInCity(options);
	}

	public void onGetPoiDetailResult(PoiDetailResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onGetPoiResult(PoiResult result) {
		
		PoiOverlay overlay = new PoiOverlay(mBaiduMap);
		overlay.setData(result);
		overlay.addToMap();
		overlay.zoomToSpan();

	}

}
