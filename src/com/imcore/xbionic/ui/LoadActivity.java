package com.imcore.xbionic.ui;

import com.imcore.xbionic.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class LoadActivity extends Activity implements OnClickListener{
	private Button btnsina,btnqq,btnlogin,btnregister;
	private ImageButton btnhelp,btnserve;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load);
		btnsina = (Button)findViewById(R.id.btn_sina_login);
		btnqq = (Button) findViewById(R.id.btn_qq_login);
		btnlogin = (Button) findViewById(R.id.btn_login);
		btnregister = (Button) findViewById(R.id.btn_register);
		btnhelp = (ImageButton)findViewById(R.id.img_but_help);
		btnsina.setOnClickListener(this);
		btnqq.setOnClickListener(this);
		btnlogin.setOnClickListener(this);
		btnregister.setOnClickListener(this);
		btnhelp.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_login:
			Intent intent = new Intent(LoadActivity.this,MainActivity.class);
		}
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0){
			dialog();
			return true;
		}
		return true;
	}

	private void dialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("真的要退出么？？？");
		builder.setTitle("请注意");
		builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				android.os.Process.killProcess(android.os.Process.myPid());
			}
		});
		builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {			
				dialog.dismiss();
			}
		});
			builder.create().show();
		}
	}
		

