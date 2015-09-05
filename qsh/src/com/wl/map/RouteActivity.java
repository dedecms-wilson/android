package com.wl.map;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.SupportMapFragment;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.overlayutil.TransitRouteOverlay;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;

public class RouteActivity extends FragmentActivity  implements OnGetRoutePlanResultListener {
	
	private RoutePlanSearch mSearch = null;
	MapView mMapView = null; 
	BaiduMap mBaiduMap = null;
	SupportMapFragment mapFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_routeplan);
		
		//初始化百度地图Fragment
		mapFragment = SupportMapFragment.newInstance();
		//添加到界面显示
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.add(R.id.routeMap, mapFragment);
		transaction.commit();
	
		
		mSearch = RoutePlanSearch.newInstance();
		mSearch.setOnGetRoutePlanResultListener(this);

	}
	
	public void search(View btn) {
		mBaiduMap = mapFragment.getBaiduMap();
		mBaiduMap.clear();
		
		PlanNode stNode = PlanNode.withCityNameAndPlaceName("北京", "龙泽");  
		PlanNode enNode = PlanNode.withCityNameAndPlaceName("北京", "西单");
		mSearch.transitSearch((new TransitRoutePlanOption())  
			    .from(stNode)  
			    .city("北京")  
			    .to(enNode));
	}

	public void onGetDrivingRouteResult(DrivingRouteResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onGetTransitRouteResult(TransitRouteResult result) {
		List<TransitRouteLine> lines = result.getRouteLines();
		for(TransitRouteLine line: lines) {
			TransitRouteOverlay overlay = new TransitRouteOverlay(mBaiduMap);
			overlay.setData(line);
			overlay.addToMap();
			overlay.zoomToSpan();
		}
		
		
	}

	public void onGetWalkingRouteResult(WalkingRouteResult arg0) {
		// TODO Auto-generated method stub
		
	}

	


}
