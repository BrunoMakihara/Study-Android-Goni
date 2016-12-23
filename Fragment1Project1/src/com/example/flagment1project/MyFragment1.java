package com.example.flagment1project;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.TextView;

public class MyFragment1 extends Fragment {
	private static final String TAG ="MainActivity";
	
	TextView tv1;
	int cnt = 0;
	
	MainActivity main;		// 방법2 MainActivity에서 Activity를 전달해서 여기서 받아서 MainActivity를 접근할 수 있다.
	public void setActivity(MainActivity main) {
		this.main = main;
	}
	
	public void setChangeData(String data) {		// 외부에서 tv변경하기 위한 함수
		tv1.setText(data);
	}
	
	View.OnClickListener bHanlder = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId()) {
			case R.id.button1 :
				cnt++;
				tv1.setText("cnt : " + cnt);
				break;
			case R.id.button2 :
//				((MainActivity)activity).doPlus(cnt);		// 방법 1 형변환을 이용하여 Main에 접근 방법
				main.doPlus(cnt);
				break;
			}
			
		}
	};

	Activity activity;	// 액티비티 선언
	@Override
	public void onAttach(Activity activity) {
		this.activity = activity;		// 여기가 제일 먼저 실행이 되기 때문에 여기서 엮어 이걸로 점 찍어서 사용
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag1, null);		// inflate하는 것을 inflater를 이용해!
		
		tv1 = (TextView)view.findViewById(R.id.textView1);
		view.findViewById(R.id.button1).setOnClickListener(bHanlder);
		view.findViewById(R.id.button2).setOnClickListener(bHanlder);
		
		return view;
	}

	@Override
	public void onDetach() {
		if(activity != null) {
			activity = null;
		}
		super.onDetach();
	}
	
	
}
