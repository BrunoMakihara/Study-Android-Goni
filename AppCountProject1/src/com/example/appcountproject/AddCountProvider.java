package com.example.appcountproject;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;
import android.widget.RemoteViews;

public class AddCountProvider extends AppWidgetProvider {
	private static final String TAG = "MainActivity";
	int cnt = 0;

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// 이건 최소 30분마다 1번씩 호출 되기 때문에 즉시 업데이트 안돼
		cnt++; // 누적이 안되 BR의 자식이기 때문에 한번 실행하고 Memory Out 되버리니까!
		Log.v(TAG, "onUpdate cnt : " + cnt);

		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.appcount1); // 이 것으로만 접근 가능해!!

		views.setTextViewText(R.id.textView1, "COUNT");
		// 1번째 내가 접근할 ID 2번째 상수

		Intent intent = new Intent("aaa.bbb.action.START");
		// "aaa...." 에는 패키지명이 와야 해
		// Menifrest에 등록하면 돼
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
				intent, PendingIntent.FLAG_CANCEL_CURRENT);

		views.setTextViewText(R.id.button1, "Start");
		views.setOnClickPendingIntent(R.id.button1, pendingIntent);

		Intent activity = new Intent(context, MainActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(context, 0,
				activity, PendingIntent.FLAG_CANCEL_CURRENT);
		
		views.setOnClickPendingIntent(R.id.imageView1, pIntent);

		/* STOP 버튼이 있었을 경우 */
		// Intent intent1 = new Intent("aaa.bbb.action.STOP");
		// // "aaa...." 에는 패키지명이 와야 해
		// // Menifrest에 등록하면 돼
		// PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, 0,
		// intent1, PendingIntent.FLAG_CANCEL_CURRENT);
		//
		// views.setOnClickPendingIntent(R.id.button2, pendingIntent1);

		/* 이렇게 하지마 */
		// Intent intent = new Intent(context, CountService.class);
		// // Service는 무조건 Menifrest에 등록을 해야 한다
		// // 뒤에가 Activity면 Activity를 실행하고 Service이니까 service 실행 함
		//
		// PendingIntent pendingIntent = PendingIntent.getService(context, 0,
		// intent, PendingIntent.FLAG_CANCEL_CURRENT);
		// // 4번째 기존에 있던거를 바꿔라
		// views.setOnClickPendingIntent(R.id.button1, pendingIntent);
		// // Intent는 화면 실행시키거나 BR하거나 Service하거나 3가지바께 못해
		// // 멈추는게 없다.
		// // AppWidget는 오직 pendingIntent만 할 수 있다. Toast 못해

		appWidgetManager.updateAppWidget(appWidgetIds, views);
		// 이걸 해야 변경이 된다.!

		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
}
