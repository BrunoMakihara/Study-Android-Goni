package com.example.activityproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SubActivity extends Activity {
	private static final String TAG = "MainActivity";
	private static final boolean DEBUG = true;

	View.OnClickListener bHandler = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button1:
				Intent data = new Intent();
				data.putExtra("result", "Goni");
				setResult(RESULT_OK, data);
				finish();
				break;
			case R.id.button2:
				setResult(999);
				finish();
				break;
			}

		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.test1);
		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);

		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		String address = intent.getStringExtra("address");
		int cnt = intent.getIntExtra("cnt", 4000);
		int num = intent.getIntExtra("num", 9000);
		Log.v(TAG, "name :" + name);
		Log.v(TAG, "address :" + address);
		Log.v(TAG, "cnt :" + cnt);
		Log.v(TAG, "num :" + num);
	}

}
