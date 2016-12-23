package com.example.flagment1project;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	private static final String TAG ="MainActivity";
	
	FragmentManager fManager = null;
	MyFragment1 fragment1 = null;
	TextView tv1;
	
	public void doPlus(int cnt) {		// 외부(Fragment)에서 접근하기 위함
		tv1.setText("cnt : " + cnt);
	}
	
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch( v.getId() ) {
			case R.id.button1 :
				fragment1.setChangeData("Change"); 	// Fragment TextView를 바꾸기 위 해
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.button1).setOnClickListener(bHandler);
		tv1 = (TextView)findViewById(R.id.textView1);
		
		fManager = getSupportFragmentManager();		// Fragement를 찾아내야 해
		fragment1 = (MyFragment1)fManager.findFragmentById(R.id.fragment1); 	
		// Fragement가 올 것 그래서 형변환
		
		fragment1.setActivity(this);		// 방법 2 이걸 만들어서 써 Myfragment1에 MainActivity를 보내는 것  
	}
}
