package com.example.appcountproject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class CountService extends Service {
	private static final String TAG = "MainActivity";
	int cnt = 0; // 누적 변수
	boolean onAir = false; // While 문 제어 변수

	class CountThread extends Thread {
		public void run() {
			Intent intent = null;
			// Service에서 방송을 하기 위 함
			while (onAir) {
				cnt++;
				Log.v(TAG, "cnt : " + cnt);
				intent = new Intent("aaa.bbb.action.COUNT");
				// 방송을 만들어 !
				intent.putExtra("cnt", cnt);
				// 부가 data를 첨부해서
				sendBroadcast(intent);
				// 방송을 쏩니다.!!! 서비스가 1번 돌때마다 방송이 막 가겠네!?
				SystemClock.sleep(500);
			}
		}
	}

	@Override
	public void onCreate() {
		onAir = true;
		new CountThread().start();
		super.onCreate();
	}

	@Override
	public void onDestroy() { // 서비스가 죽기전에 실행 됌
		onAir = false;
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) { // 다른 APK에서 내 Service에 접근 할 경우 사용
		// TODO Auto-generated method stub
		return null;
	}

}
