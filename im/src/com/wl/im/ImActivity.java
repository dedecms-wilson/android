package com.wl.im;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.packet.Presence;

import com.wl.im.manager.ConnectionManager;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ImActivity extends Activity {
    
	private EditText et_name;
	private EditText et_pwd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        et_name = (EditText) findViewById(R.id.et_name);
		et_pwd = (EditText) findViewById(R.id.et_pwd);
    }
    
    public void login(View btn){
		//登录
		new LoginTask().execute();
		
	}
	
	
	class LoginTask extends AsyncTask<Void, Void, Boolean>{

		@Override
		protected Boolean doInBackground(Void... params) {
			//获取用户名密码
			String username = et_name.getText().toString();
			String password = et_pwd.getText().toString();
			
			AbstractXMPPConnection connection = ConnectionManager.getConnection();
			try {
				//登录
				connection.login(username, password);
				//登录成功，发送状态给服务器更新用户的在线状态
				Presence p = new Presence(Presence.Type.available);
				connection.sendStanza(p);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} 
			return true;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				Log.d("jason", "登录成功！");
				Toast.makeText(ImActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(ImActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();
			}
		}
		
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}