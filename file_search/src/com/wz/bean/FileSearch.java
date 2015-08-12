package com.wz.bean;

import java.io.File;
import java.lang.ref.SoftReference;

import android.graphics.Bitmap;

public class FileSearch {
	private String filename;
	private String filePath;
	private SoftReference<Bitmap> bitmapImage;
	private File file;
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public SoftReference<Bitmap> getBitmapImage() {
		return bitmapImage;
	}
	public void setBitmapImage(SoftReference<Bitmap> bitmapImage) {
		this.bitmapImage = bitmapImage;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	

}
