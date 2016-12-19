package com.example.brproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "Goni";
	int cnt = 0;

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		String name = intent.getStringExtra("name");

		if ("com.example.brproject.intent.action.LOAD".equals(action)) {
			Log.v(TAG, "읽기 작업");
		} else if ("com.example.brproject.intent.action.SAVE".equals(action)) {
			Log.v(TAG, "저장 작업");
		}

		// 메모리에 상주하지 않기 때문에 cnt는 증가하지 않고 항상 1로 찍힐 것이다.
		cnt++;

		Log.v(TAG, "action : " + action);
		Log.v(TAG, "name : " + name);
		Log.v(TAG, "cnt : " + cnt);

	}

}
