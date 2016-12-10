package com.example.animationproject;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends Activity {
	private static final String TAG = "Goni";

	View tv, btn;
	// Frame AniMation에서 쓰는 것
	AnimationDrawable aniDrawble;

	// 모든 Animation을 받을 수 있다.
	Animation animation;

	View.OnClickListener bHandler = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			v.startAnimation(animation);
			switch (v.getId()) {
			case R.id.button1:
				aniDrawble.start();
				break;
			case R.id.button2:
				aniDrawble.stop();
				break;
			case R.id.button3:
				btn.startAnimation(animation);
				break;
			}

		}
	};

	// 화면이 떠서 사용자들이 반응할 상황까지 왔을 때 뜨는 것
	// 여기서 UI 체이지 하는게 가장 깔끔
	public void onWindowFocusChanged(boolean hasFocus) {
		if (aniDrawble != null) {
			if (hasFocus) {
				// 화면에 보일 때 스타트!
				aniDrawble.start();
			} else {
				// 화면에 안보일때는 정리!
				aniDrawble.stop();
			}
		}
		super.onWindowFocusChanged(hasFocus);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = findViewById(R.id.textView1);

		btn = findViewById(R.id.button4);

		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);
		findViewById(R.id.button3).setOnClickListener(bHandler);

		aniDrawble = (AnimationDrawable) tv.getBackground();

		animation = AnimationUtils.loadAnimation(this, R.anim.scale);
		
		findViewById(R.id.button1).animate();
	}
}
