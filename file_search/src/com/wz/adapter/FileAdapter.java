package com.wz.adapter;

import java.lang.ref.SoftReference;
import java.util.List;

import com.wz.bean.FileSearch;
import com.wz.filesearch.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileAdapter extends BaseAdapter{
	
	List<FileSearch> files;
	private Context context;
	LayoutInflater inflater;
	public FileAdapter(Context context,List<FileSearch> files){
		this.context = context;
		this.files = files;
		inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		
	}
	public int getCount() {
		return files.size();
	}

	public Object getItem(int position) {
		return files.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		FileSearch fileSearch = files.get(position);
		ViewHolder viewHolder;
		if(convertView == null){
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.listview, null);
			ImageView image = (ImageView) convertView.findViewById(R.id.image);
			TextView filename = (TextView) convertView.findViewById(R.id.filename);
			
			viewHolder.setFilename(filename);
			viewHolder.setImage(image);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if(fileSearch.getBitmapImage().get() == null) {
			FileTask task = new FileTask();
			viewHolder.getImage().setImageResource(R.string.white);
			task.execute(fileSearch.getFilePath(),String.valueOf(position));
		} else {
			viewHolder.getImage().setImageBitmap(fileSearch.getBitmapImage().get());
		}
		
		viewHolder.getFilename().setText(fileSearch.getFilename());
		
		return convertView;
	}
	private class FileTask extends AsyncTask<String, Void, Bitmap>{

		@Override
		protected Bitmap doInBackground(String... params) {
			String path = params[0];
			int position = Integer.valueOf(params[1]);
			files.get(position).setBitmapImage(new SoftReference<Bitmap>(BitmapFactory.decodeFile(path)));
			//return BitmapFactory.decodeFile(path);
			return null;
		}
		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			FileAdapter.this.notifyDataSetChanged();
	    }

		
		
	}
	private class ViewHolder{
		private ImageView image;
		private TextView filename;
		
		public ImageView getImage() {
			return image;
		}
		public void setImage(ImageView image) {
			this.image = image;
		}
		public TextView getFilename() {
			return filename;
		}
		public void setFilename(TextView filename) {
			this.filename = filename;
		}
	}

}
