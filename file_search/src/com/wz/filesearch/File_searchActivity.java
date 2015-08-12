package com.wz.filesearch;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import com.wz.adapter.FileAdapter;
import com.wz.bean.FileSearch;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class File_searchActivity extends Activity implements OnItemClickListener {
    /** Called when the activity is first created. */
	ListView lv;
	FileAdapter adapter;
	List<FileSearch> files;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        initView();
        
        initData();
        
    }
	private void initData() {
		File file = Environment.getExternalStorageDirectory().getAbsoluteFile();
		//System.out.println(file.getAbsolutePath());
		Log.i("external path", file.getAbsolutePath());
		getAllFiles(file.getAbsolutePath());
		
	}
	private void initView() {
		lv = (ListView) findViewById(R.id.lv);
		
		
	}
	private void getAllFiles(String path){  
        if(path == "") return; //if go back to root,will get error
        
		files = new ArrayList<FileSearch>();
		String prePath = path.substring(0, path.lastIndexOf('/'));
		FileSearch fileSearch = new FileSearch();
		fileSearch.setFilename("back");
		fileSearch.setFilePath(prePath);
		fileSearch.setFile(new File(prePath));
		fileSearch.setBitmapImage(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(), R.drawable.folder)));
		files.add(fileSearch);
		
		File file = new File(path);
		File[] filelist = file.listFiles();  
          
        if(filelist != null) {
        	for (File f:filelist){
        		FileSearch fileSearchList = new FileSearch();
        		
    			fileSearchList.setFilePath(f.getAbsolutePath());
    			fileSearchList.setFile(f);
        		if(f.isDirectory()) {
        			fileSearchList.setFilename(f.getName());
        			fileSearchList.setBitmapImage(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(), R.drawable.folder)));
        		}else {
        			fileSearchList.setFilename(f.getName().substring(0, f.getName().lastIndexOf('.')));
        			if(isPic(f)){
        				
        				//fileSearchList.setBitmapImage(BitmapFactory.decodeFile(f.getAbsolutePath()));
        				fileSearchList.setBitmapImage(new SoftReference<Bitmap>(null));
            		}else {
            			fileSearchList.setBitmapImage(new SoftReference<Bitmap>(BitmapFactory.decodeResource(getResources(), R.drawable.folder)));
            		}
        		}
        		files.add(fileSearchList);

        	}
        } 
        
        adapter = new FileAdapter(this, files);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
    } 
	private boolean isPic(File f){
		if(f.getName().toLowerCase().endsWith(".jpg") || f.getName().toLowerCase().endsWith(".png") || f.getName().toLowerCase().endsWith(".gif")) {
			return true;
		}
		return false;
		
	}
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		FileSearch fileSearch = files.get(position);
		if(fileSearch.getFile().isDirectory()) {
			Log.i("info", "click item is directory");
			getAllFiles(fileSearch.getFilePath());
		} else {
			Log.i("info", "click item is not directory");
		}
		
		
	}
}