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
      //����Markerͼ��  
      BitmapDescriptor bitmap = BitmapDescriptorFactory  
          .fromResource(R.drawable.icon_marka);  
      //����MarkerOption�������ڵ�ͼ�����Marker  
      OverlayOptions option = new MarkerOptions()  
          .position(point)  
          .icon(bitmap)
          .draggable(true);  
      //�ڵ�ͼ�����Marker������ʾ  
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
      
    //�������ε��������  
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
      //�����û����ƶ���ε�Option����  
      OverlayOptions polygonOption = new PolygonOptions()  
          .points(pts)  
          .stroke(new Stroke(5, 0xAA00FF00))  
          .fillColor(0xAAFFFF00);  
      //�ڵ�ͼ����Ӷ����Option��������ʾ  
      mBaiduMap.addOverlay(polygonOption);
    }
    
    @Override  
    protected void onDestroy() {  
        super.onDestroy();  
        //��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���  
        mMapView.onDestroy();  
    }  
    @Override  
    protected void onResume() {  
        super.onResume();  
        //��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���  
        mMapView.onResume();  
        }  
    @Override  
    protected void onPause() {  
        super.onPause();  
        //��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���  
        mMapView.onPause();  
    }  
    
}