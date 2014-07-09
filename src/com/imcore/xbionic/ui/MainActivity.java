package com.imcore.xbionic.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.imcore.xbionic.R;
import com.imcore.xbionic.http.Constant;
import com.imcore.xbionic.http.DataRequest;
import com.imcore.xbionic.http.RequestQueueSingleton;

public class MainActivity extends Activity implements OnClickListener {	
	private EditText mUser,mPassword;
	private Button mBack,mEnter,mForget;
	private ProgressDialog progress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mUser = (EditText) findViewById(R.id.user_login);
		mPassword = (EditText)findViewById(R.id.pwd_login);
		mBack = (Button)findViewById(R.id.btn_back_login);
		mEnter = (Button)findViewById(R.id.btn_enter_login);
		mForget = (Button)findViewById(R.id.btn_forget_login);
		mBack.setOnClickListener(this);
		mEnter.setOnClickListener(this);
		mForget.setOnClickListener(this);		
	}

	@SuppressWarnings("unused")
	private void doLogin() {
		final String userName = "";
		final String password = "";

		String url = Constant.HOST + "/passport/login.do";
		DataRequest request = new DataRequest(Request.Method.POST, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						// 解析用户信息的json，保存userid和token

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
