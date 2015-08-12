package com.wz.customview.view;

import com.wz.customview.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;

public class SoundView extends View{

	private Paint paint;
	private Bitmap green;
	private Bitmap gray;
	private AudioManager am;
	private int maxVolum;
	private int currentVolumn;
	public SoundView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		paint.setColor(Color.RED);
		green = BitmapFactory.decodeResource(getResources(), R.drawable.green);
		gray = BitmapFactory.decodeResource(getResources(), R.drawable.gray);
		
		am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		maxVolum = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		currentVolumn = am.getStreamVolume(AudioManager.STREAM_MUSIC);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		//canvas.drawText("self define ", 100, 100, paint);
		int top = 0;
		for (int i = 0; i < maxVolum - currentVolumn; i++ ){
			top = i*2*gray.getHeight();
			canvas.drawBitmap(gray, 10, top, paint);
		}
		
		for (int i = maxVolum - currentVolumn; i < maxVolum ; i++ ){
			top = i*2*green.getHeight();
			canvas.drawBitmap(green, 10, top, paint);
		}
		super.onDraw(canvas);
	}

	public int getMaxVolum() {
		return maxVolum;
	}

	public void setMaxVolum(int maxVolum) {
		this.maxVolum = maxVolum;
	}

	public int getCurrentVolumn() {
		return currentVolumn;
	}

	public void setCurrentVolumn(int currentVolumn) {
		this.currentVolumn = currentVolumn;
	}
	
	
	

	

}
