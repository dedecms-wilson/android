package www.wz.com;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
//import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
import android.widget.Toast;

public class DialogActivity extends Activity {
	
	private RelativeLayout mainLayout;
	private Button bt;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setContentView(mainLayout);
        
        
        
    }
	private void initView() {
		mainLayout=new RelativeLayout(this);
        
        bt = new Button(this);
        bt.setText("popup window");
        
        mainLayout.addView(bt);	
        bt.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Log.i("info", "click button");
				//openNormalDialog();
				//openProgressDialog();
				//openCustomDialog();
				//openTimePickerDialog();
				openPopupWin();
			}
		});
	}

	protected void openPopupWin() {
		View view = LayoutInflater.from(this).inflate(R.layout.popup_win, null);
		
		PopupWindow popWin = new PopupWindow(bt);
		popWin.setContentView(view);
		popWin.setWidth(300);
		popWin.setHeight(80);
		popWin.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.local_popup_bg));
		int[] location = new int[2];
		bt.getLocationInWindow(location);
		popWin.setFocusable(true);
		popWin.showAtLocation(mainLayout, Gravity.LEFT|Gravity.TOP, 0, location[1] + bt.getHeight());

	}
	protected void openTimePickerDialog() {
		Calendar calendar = Calendar.getInstance();
		Date day = calendar.getTime();
	
		TimePickerDialog timeDialog = new TimePickerDialog(this, new OnTimeSetListener() {
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				Toast.makeText(DialogActivity.this, "hour is: " + hourOfDay + ",minute is: " + minute, 1000).show();
				
			}
		}, day.getHours(), day.getMinutes(), true);
		
		timeDialog.show();
		
	}
	
	
	protected void openCustomDialog() {
		View view = LayoutInflater.from(this).inflate(R.layout.customdialog, null);
		
		AlertDialog.Builder builder = new Builder(DialogActivity.this);
		builder.setTitle("Customer popup window");
		//builder.setMessage("This is my message");
		builder.setView(view);
		
		final Dialog dialog = builder.create();
		dialog.show();
		
		final EditText password = (EditText) view.findViewById(R.id.password);
		final EditText cpassword = (EditText) view.findViewById(R.id.cpassword);
		Button sure = (Button) view.findViewById(R.id.confirm);
		sure.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if(password.getText() != null && cpassword.getText() != null && password.getText().toString().equals(cpassword.getText().toString())) {
					Toast.makeText(DialogActivity.this, "click ok button in dialog," + "password:" + password.getText().toString(), 1000).show();	
					dialog.dismiss();
				} else {
					Toast.makeText(DialogActivity.this, "password is not same, please reset", 1000).show();
				}
					
			}
		});
		
	}
	protected void openProgressDialog() {
		ProgressDialog progDialog = new ProgressDialog(this);
		progDialog.setMessage("loading....");
		progDialog.setMax(100);
		progDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progDialog.show();
		progDialog.setProgress(20);
		
	}
	private void openNormalDialog() {
		AlertDialog.Builder builder = new Builder(DialogActivity.this);
		builder.setTitle("normal popup window");
		builder.setMessage("This is my message");
		builder.setView(new EditText(this));
		builder.setIcon(R.drawable.ic_launcher);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(DialogActivity.this, "click ok button", 1000).show();
				dialog.dismiss();	
			}

		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(DialogActivity.this, "click cancel button", 1000).show();
				dialog.dismiss();	
			}

		});
		builder.create().show();
		
	}
}