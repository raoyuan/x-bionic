package com.imcore.xbionic.ui;

import java.util.HashMap;
import java.util.Map;

import javax.sql.ConnectionEvent;
import javax.xml.validation.ValidatorHandler;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.imcore.xbionic.R;
import com.imcore.xbionic.http.Constant;
import com.imcore.xbionic.http.DataRequest;
import com.imcore.xbionic.http.RequestQueueSingleton;
import com.imcore.xbionic.util.ConnectivityUtil;
import com.imcore.xbionic.util.JsonUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
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

	@SuppressWarnings("unused")
	private void doLogin() {
		final String userName = mUser.getText().toString();
		final String password = mPassword.getText().toString();

		String url = Constant.HOST + "/passport/login.do";
		DataRequest request = new DataRequest(Request.Method.POST, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						// 解析用户信息的json，保存userid和token
						onResponseForLogin(response);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Toast.makeText(LoginActivity.this, "请求服务器发生错误:"+error.getMessage(), Toast.LENGTH_SHORT).show();
					}
				}) {
			@Override
			protected Map<String, String> getParams()
					throws AuthFailureError {
				// 在此方法中设置要提交的请求参数
				Map<String,String> params = new HashMap<String, String>();
				params.put("phoneNumber", userName);
				params.put("password", password);
				
				
				return params;
			}
		};
		
		RequestQueueSingleton.getInstance(this).addToRequestQueue(request);
	}
	private void onResponseForLogin(String response){
		
		String userAddress = JsonUtil.getJsonValueByKey(response, "userAddress");
		JSONObject jo;
		String userId =null;
		try {
			jo = new JSONObject(userAddress);
			userId = jo.getString("userId");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String token = JsonUtil.getJsonValueByKey(response, "token");
		String firstName = JsonUtil.getJsonValueByKey(response, "firstName");
		String lastName = JsonUtil.getJsonValueByKey(response, "lastName");
		String userName = lastName+firstName;
		
		SharedPreferences sp = getSharedPreferences("userinfo",Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.putString("userId", userId);
		edit.putString("token", token);
		edit.putString("userName", userName);
		edit.putBoolean("isLogin", true);
		edit.commit();
		
		Intent intent = new Intent(LoginActivity.this,MainActivity.class);
		intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);//清除栈内其他activity;
		startActivity(intent);
	} 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_enter_login:
			doLogin();
			break;
		case R.id.btn_forget_login:
			Intent intent = new Intent(this,ForgetpwdActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_back_login:
			finish();
			break;
		
		}
		
	}
}
