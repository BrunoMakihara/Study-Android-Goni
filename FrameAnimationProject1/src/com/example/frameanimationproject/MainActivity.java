package com.example.frameanimationproject;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	AnimationDrawable aniDrawable;
	int[] resId = { R.id.button1, R.id.button2, R.id.button3 };
	
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch( v.getId() ) {
			case R.id.button1 :
				aniDrawable.start();
				break;
			case R.id.button3 :
				aniDrawable.stop();
				break;
			}
			
		}
	};
	
	
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		//onResume 다음에 화면이 생기는데 이때 여기서 hasFocus가 True 나옴
		if ( hasFocus ) {
			aniDrawable.start();
		} else {
			aniDrawable.start();
		}
		super.onWindowFocusChanged(hasFocus);
	}



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		for(int id : resId) {
			findViewById(id).setOnClickListener(bHandler);
		}
		
		aniDrawable = (AnimationDrawable)findViewById(R.id.button2).getBackground();
		// 버튼 2번째 backGround에 지금 frame(animation - list)를 뽑아서 연결 시킴!!!
		

	}
}
