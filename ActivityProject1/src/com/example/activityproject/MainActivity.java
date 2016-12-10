package com.example.activityproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	private static final boolean DEBUG = true;
	TextView tv1, tv2;

	View.OnClickListener bHandler = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.button1:
				doStartActivity();
				break;
			case R.id.button2:
				doStartBoard();
				break;
			}
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (DEBUG) {
			Log.v(TAG, "requestCode :" + requestCode);
			Log.v(TAG, "resultCode :" + resultCode);
			Log.v(TAG, "data :" + data);
		}
	
		switch (resultCode) {
		case RESULT_OK:
			String result = data.getStringExtra("result");
			switch (requestCode) {
			case 900:
				tv1.setText(result);
				break;
			case 800:
				tv2.setText(result);
				break;
			}

			break;
		case RESULT_CANCELED:
			tv1.setText("유저가 취소함");
			break;
		case 999 :
			setResult(999);
			finish();
			break;
		}

		super.onActivityResult(requestCode, resultCode, data);

	}

	void doStartBoard() {
		Intent intent = new Intent(this, BoardActivity.class);
		intent.putExtra("name", "mokwon");
		startActivityForResult(intent, 900);

		// startActivity(intent);
	}

	void doStartActivity() {
		if (DEBUG) {
			Log.v(TAG, "startActivity");
		}
		Intent intent = new Intent(this, SubActivity.class);
		intent.putExtra("name", "hongkildong");
		intent.putExtra("cnt", 123);

		startActivityForResult(intent, 800);
		// startActivity(intent);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);

		tv1 = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
	}
}
