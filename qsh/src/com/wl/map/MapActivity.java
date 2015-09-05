package com.wl.map;

import java.util.ArrayList;
import java.util.List;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerDragListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MapActivity extends Activity {
	MapView mMapView = null; 
	BaiduMap mBaiduMap = null;
	
	Marker marker = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mMapView = (MapView) findViewById(R.id.bmapView);
        
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); 
        //mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        mBaiduMap.setTrafficEnabled(true);
        //mBaiduMap.setBaiduHeatMapEnabled(true);
        
        LatLng point = new LatLng(39.963175, 116.400244);  
      //构建Marker图标  
      BitmapDescriptor bitmap = BitmapDescriptorFactory  
          .fromResource(R.drawable.icon_marka);  
      //构建MarkerOption，用于在地图上添加Marker  
      OverlayOptions option = new MarkerOptions()  
          .position(point)  
          .icon(bitmap)
          .draggable(true);  
      //在地图上添加Marker，并显示  
      marker= (Marker)(mBaiduMap.addOverlay(option));
      mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {

		public boolean onMarkerClick(Marker marker) {
			Button btn = new Button(MapActivity.this);
			btn.setBackgroundResource(R.drawable.popup);
			btn.setText("Get location");
			
			InfoWindow infoWindow = new InfoWindow(btn,marker.getPosition(),-50);
			mBaiduMap.showInfoWindow(infoWindow);
			return false;
		}
		
		
      });
      
      mBaiduMap.setOnMarkerDragListener(new OnMarkerDragListener(){

		public void onMarkerDrag(Marker marker) {
			Log.e("drag", "on drag");
			
		}

		public void onMarkerDragEnd(Marker marker) {
			Log.e("drag", "on drag end");
			
		}

		public void onMarkerDragStart(Marker marker) {
			Log.e("drag", "on drag start");
			
		}
    	  
      });
      
    //定义多边形的五个顶点  
      LatLng pt1 = new LatLng(39.93923, 116.357428);  
      LatLng pt2 = new LatLng(39.91923, 116.327428);  
      LatLng pt3 = new LatLng(39.89923, 116.347428);  
      LatLng pt4 = new LatLng(39.89923, 116.367428);  
      LatLng pt5 = new LatLng(39.91923, 116.387428);  
      List<LatLng> pts = new ArrayList<LatLng>();  
      pts.add(pt1);  
      pts.add(pt2);  
      pts.add(pt3);  
      pts.add(pt4);  
      pts.add(pt5);  
      //构建用户绘制多边形的Option对象  
      OverlayOptions polygonOption = new PolygonOptions()  
          .points(pts)  
          .stroke(new Stroke(5, 0xAA00FF00))  
          .fillColor(0xAAFFFF00);  
      //在地图上添加多边形Option，用于显示  
      mBaiduMap.addOverlay(polygonOption);
    }
    
    @Override  
    protected void onDestroy() {  
        super.onDestroy();  
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理  
        mMapView.onDestroy();  
    }  
    @Override  
    protected void onResume() {  
        super.onResume();  
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
        mMapView.onResume();  
        }  
    @Override  
    protected void onPause() {  
        super.onPause();  
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理  
        mMapView.onPause();  
    }  
    
}