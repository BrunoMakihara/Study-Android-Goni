package com.example.inflateproject;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 방법 1 생성 명시적 inflate
		// LinearLayout main = new LinearLayout(this);
		// main.setOrientation(LinearLayout.VERTICAL);
		// main.setBackgroundColor(Color.GREEN); // 0x0000ff00 으로 표기해도 됨 (투명도를
		// 줘야 해 00으로)
		//
		// TextView tv = new TextView(this);
		// tv.setText(R.string.str1);
		// tv.setTextColor(Color.RED);
		// tv.setTextSize(45);
		// tv.setGravity(Gravity.CENTER_HORIZONTAL);
		//
		// main.addView(tv);
		//
		// setContentView(main);

		// 방법 2 생성 명시적 inflate
		// LinearLayout main = (LinearLayout)View.inflate(this,
		// R.layout.activity_main, null);
		// setContentView(main);

		// 방법 3 생성 묵시적 inflate
		setContentView(R.layout.activity_main);

	}
}
