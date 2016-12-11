package com.example.anrproject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static final String TAG = "Goni";
	EditText et1;
	int cnt = 0;

	View.OnClickListener bHandler = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button1:
				// doForLongJob();
				doStart();
				break;
			case R.id.button2:
				break;
			}
		}
	};

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// Super는 하는일 없어 지우자
			switch (msg.what) {
			case 777:
				et1.setText("작업 시작");
				break;
			case 888:
				et1.setText("작업 종료");
				break;
			}
		}
	};

	// Thread에서는 UI를 변경할 수 없다.
	class JobThread extends Thread {
		@Override
		public void run() {
			// Super는 지워 하는일 없어
			doForLongJob();
		}
	}

	// doForLongJob을 Thread에서 동작하도록 함
	void doStart() {
		JobThread trd1 = new JobThread();
		trd1.start();
	}

	void doForLongJob() {
		// et1.setText("작업 시작");
		// what만 보낼 때 이걸로 보내면 댐
		handler.sendEmptyMessage(777);

		for (int i = 0; i < 10; i++) {
			Log.v(TAG, "작업중..." + i);
			SystemClock.sleep(1000);
		}

		// et1.setText("작업 종료");
		// what만 보낼 때 이걸로 보내면 댐
		handler.sendEmptyMessage(888);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);

		et1 = (EditText) findViewById(R.id.editText1);
	}
}
