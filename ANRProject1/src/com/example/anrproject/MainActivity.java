package com.example.anrproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	TextView tv;
	ProgressDialog pDialog = null;
	
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// Before task
			pDialog = ProgressDialog.show(MainActivity.this, "", "DownTastking...");
			new JobThread().start();
			// After task
		}
	};
	
	int cnt = 0;
	
	class JobThread extends Thread {
		public void run() {
			doForLongJob();
		}
	}
	
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case 100:
				tv.setText("시작 cnt : " + cnt);
				break;
			case 200:
				if(pDialog != null) {
					pDialog.cancel();
				}
				tv.setText("마지막 cnt : " + cnt);
				break;
			}
		}
		
	};
	void doForLongJob() {
		cnt++;
		handler.sendEmptyMessage(100);
//		tv.setText("시작 cnt : " + cnt);
		for(int i = 0; i < 10; i++) {
			Log.v(TAG, "for i : " + i);
			SystemClock.sleep(1000);
		}
		handler.sendEmptyMessage(200);
//		pDialog.cancel();				//UI 
//		tv.setText("마지막 cnt : " + cnt);
	}
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.button1).setOnClickListener(bHandler);
		
		tv = (TextView)findViewById(R.id.textView1);
	}
}
