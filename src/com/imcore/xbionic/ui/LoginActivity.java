package com.imcore.xbionic.ui;

import java.util.HashMap;
import java.util.Map;

import javax.sql.ConnectionEvent;
import javax.xml.validation.ValidatorHandler;

import com.imcore.xbionic.R;
import com.imcore.xbionic.util.ConnectivityUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements android.view.View.OnClickListener{
	private EditText mUser,mPassword;
	private Button mBack,mEnter,mForget;
	private ProgressDialog progress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_login);
		super.onCreate(savedInstanceState);
		mUser = (EditText) findViewById(R.id.user_login);
		mPassword = (EditText)findViewById(R.id.pwd_login);
		mBack = (Button)findViewById(R.id.btn_back_login);
		mEnter = (Button)findViewById(R.id.btn_enter_login);
		mForget = (Button)findViewById(R.id.btn_forget_login);
		mBack.setOnClickListener(this);
		mEnter.setOnClickListener(this);
		mForget.setOnClickListener(this);
		
		if(mUser != null&&mPassword != null){
			SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
			String username = preferences.getString("username", "");
			String password = preferences.getString("password", "");
			mUser.setText(username);
			mPassword.setText(password);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_enter_login:
			dologin();
			break;
		case R.id.btn_back_login:
			finish();
			break;
		}	
	}

	private void dologin() {
		if(ConnectivityUtil.isOnline(this)){
			String inputUsername = mUser.getText().toString();
			String inputPassword = mPassword.getText().toString();
		}
	}
	private boolean validaterinput(String username,String password){
		if(username==null||username.equals("")){
			Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
			mUser.requestFocus();
			return false;
		}
		if(password==null||password.equals("")){
			Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
			mPassword.requestFocus();
			return false;
		}
		return true;	
	}
	class LoginTask extends AsyncTask<Void, Void, String>{
		private String muser;
		private String mpassword;
		public LoginTask(String user,String pwd) {
			muser=user;
			mpassword=pwd;
		}
		@Override
		protected void onPreExecute() {
			progress = ProgressDialog.show(LoginActivity.this, "请稍后", "努力加载中。。。");
			super.onPreExecute();
		}
		@Override
		protected String doInBackground(Void... params) {
			
			return mpassword;		
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
	}
}
