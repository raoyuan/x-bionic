package com.imcore.xbionic.ui;

import java.util.ArrayList;
import java.util.List;

import com.imcore.xbionic.R;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ImageView;

public class OpenActivity extends FragmentActivity{
	private List<ImageView> list;
	private ViewPager viewpager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_open);
		
		SharedPreferences inP = getSharedPreferences("hadlogin", 0);
		SharedPreferences.Editor editor = inP.edit();
		editor.putString("ind", "value");
		editor.commit();
		
		list = new ArrayList<ImageView>();
		ImageView first = (ImageView)findViewById(R.id.page1);
		ImageView second = (ImageView)findViewById(R.id.page2);
		ImageView third = (ImageView)findViewById(R.id.page3);
		list.add(first);
		list.add(second);
		list.add(third);
		
		viewpager = (ViewPager)findViewById(R.id.viewpager);
		viewpager.setAdapter(new ViewpagerAdapter());
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				for(int i=0;i<list.size();i++){
					if(i==position){
						list.get(position).setImageResource(R.drawable.yes);
					}else{
						list.get(position).setImageResource(R.drawable.no);
					}
				}
				
			}
			
			@Override
			public void onPageScrolled(int position, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int position) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	private final class ViewpagerAdapter extends FragmentStatePagerAdapter{

		public ViewpagerAdapter() {
			super(getSupportFragmentManager());
		}

		@Override
		public Fragment getItem(int v) {
			OpenFragment openfrag = new OpenFragment();
			Bundle bundle = new Bundle();
			if(v==2){
				bundle.putInt("show", 1);
			}
			bundle.putInt("position", v);
			openfrag.setArguments(bundle);
			return openfrag;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}		
	}	
}
