package com.imcore.xbionic.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.imcore.xbionic.R;

public class MainActivity extends Activity implements OnClickListener, OnItemClickListener {	
	
	public DrawerLayout drawer;
	public ActionBarDrawerToggle toggle;
	public ListView drawerList;
	public List<String> list;
	private Button btnaccount,btnsearch;
	private Button btnproduct, btnstory, btnactivity, btnintroduce;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initializeList();
		inDrawerLayout();
		btnsearch = (Button) findViewById(R.id.btn_main_search);
		btnproduct = (Button) findViewById(R.id.btn_product);
		btnstory = (Button) findViewById(R.id.btn_story);
		btnintroduce = (Button) findViewById(R.id.btn_introduce);
		btnsearch.setOnClickListener(this);
		btnproduct.setOnClickListener(this);
		btnstory.setOnClickListener(this);
		btnintroduce.setOnClickListener(this);
		
		btnaccount = (Button) findViewById(R.id.btn_drawer);
		btnaccount.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.btn_drawer:
					drawer.openDrawer(drawerList);
					break;
				default:
					break;

				}
			}
		});
	}

	private void inDrawerLayout() {
		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		View view = getLayoutInflater().inflate(R.layout.activity_drower_head,
				null);
		drawerList = (ListView) findViewById(R.id.left_drawer);
		drawerList.addHeaderView(view);
		drawerList.setAdapter(new LtAdapter());
		drawerList.setOnItemClickListener(this);
		initialDrawerListener();
	}

	private void initialDrawerListener() {
		toggle = new ActionBarDrawerToggle(this, drawer,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}
		};
		drawer.setDrawerListener(toggle);
	}

	private void initializeList() {
		list = new ArrayList<String>();
		list.add("您的收藏");
		list.add("账户设置");
		list.add("达人申请");
		list.add("部落社区");
		list.add("购物车");
		list.add("订阅信息");
		list.add("分享设置");
	}

	private class LtAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = getLayoutInflater().inflate(
					R.layout.activity_drower_item, null);
			TextView textView = (TextView) convertView
					.findViewById(R.id.tv_home);
			textView.setText(list.get(position));
			return convertView;
		}
	};

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = null;
		switch (position) {
		case 1:
			intent = new Intent(this,CollectionActivity.class);
			startActivity(intent);
			break;
		case 2:
			intent = new Intent(MainActivity.this, SetActivity.class);
			startActivity(intent);
			break;
		case 3:
			intent = new Intent(MainActivity.this, MasterActivity.class);
			startActivity(intent);
			break;
		case 4:
			intent = new Intent(MainActivity.this, MainActivity.class);
			startActivity(intent);
			break;
		case 5:
			intent = new Intent(MainActivity.this, ShoppingActivity.class);
			startActivity(intent);
			break;
		case 7:
			intent = new Intent(MainActivity.this, SharesetActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		drawer.closeDrawer(drawerList);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}




	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch(v.getId()){
		case R.id.btn_product:
			intent = new Intent(this,ProductActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_story:
			intent = new Intent(this,StoryActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_activity:
			intent = new Intent(this,XActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_introduce:
			intent = new Intent(this,IntroduceActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_main_search:
			intent = new Intent(this,SearchActivity.class);
			startActivity(intent);
			break;
		}
	}



}
