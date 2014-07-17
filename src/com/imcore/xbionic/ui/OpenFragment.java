package com.imcore.xbionic.ui;

import com.imcore.xbionic.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class OpenFragment extends Fragment{
	private Button button;
	private static final int[] open = new int[]{R.drawable.welcompage1,R.drawable.welcompage2,R.drawable.welcompage3};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_open_fragment, null);
		Bundle bundle = getArguments();
		int position = bundle.getInt("position");
		ImageView imageView = (ImageView)view.findViewById(R.id.img_open);
		imageView.setImageResource(open[position]);
		int openText = bundle.getInt("show");
		if(openText==1){
			button = (Button)view.findViewById(R.id.btn_open);
			button.setText("开始体验");
			button.setBackgroundResource(R.drawable.enterbound);
			button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(getActivity(),LoadActivity.class);
					startActivity(intent);
				}
			});
		}
		return view;
	}
}
