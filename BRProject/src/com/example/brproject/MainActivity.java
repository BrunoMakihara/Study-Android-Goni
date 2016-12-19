package com.example.brproject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static final String TAG = "Goni";
	EditText et1;

	// 얘는 Manifest에 등록하지 않고 사용할 수 있어.
	// 또한 UI를 변경할 수 있게 돼!
	BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			et1.setText(intent.getStringExtra("name"));

		}
	};

	@Override
	protected void onResume() {
		super.onResume();
		IntentFilter filter = new IntentFilter();
		// add라서 쭉쭉 넣을 수 있다.
		filter.addAction("com.example.brproject.intent.action.SAVE");
		filter.addAction("com.example.brproject.intent.action.LOAD");

		registerReceiver(receiver, filter);

	}

	@Override
	protected void onPause() {
		unregisterReceiver(receiver);
		super.onPause();
	}

	View.OnClickListener bHandler = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button1:
				// 액션은 내맘대로 정하면 돼! 충돌을 피하기 위해서 권장사항은
				// 내 Package명.intent.action.키워드; 로 권!장! 한다.
				Intent intent = new Intent(
						"com.example.brproject.intent.action.SAVE");
				intent.putExtra("name", "홍길동");
				sendBroadcast(intent);

				Log.v(TAG, "send success");
				break;
			case R.id.button2:
				Intent intent1 = new Intent(
						"com.example.brproject.intent.action.LOAD");
				intent1.putExtra("name", "안드로이드");
				sendBroadcast(intent1);
				break;
			case R.id.button3:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);
		findViewById(R.id.button3).setOnClickListener(bHandler);

		et1 = (EditText) findViewById(R.id.editText1);
	}
}
