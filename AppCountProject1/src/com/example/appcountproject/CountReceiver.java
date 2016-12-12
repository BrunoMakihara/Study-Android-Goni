package com.example.appcountproject;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class CountReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		// 액션이 날라와 분기할려고 이렇게 함
		
		if(action.equals("aaa.bbb.action.START")) {
			doStartService(context, intent);
		} else if (action.equals("aaa.bbb.action.STOP")) {
			doStopService(context, intent);
		} else if (action.equals("aaa.bbb.action.COUNT")) {
			doCountAppWidget(context, intent);
		}
	}
	
	void doCountAppWidget(Context context, Intent intent) {
		// 여기에서 AppWidget을 수정해야 함
		// AppWidget은 RemoteViews를 통해 수정할 수 있지롱
		// RemoteViews는 UI를 변경할 수 있찌롱
		
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appcount1);
		// 1번째 package 명 2번째 접근하고 싶은 layout!
		
		int cnt = intent.getIntExtra("cnt", 100);
		// 1번째 name이고 2번째는 만약 없을 경우 임의의 값으로 받아라.
		
		views.setTextViewText(R.id.textView1, "cnt : " + cnt);
		// 1번째 변경하고 싶은 UI의 ID 2번째 변경 넣고 싶은 Text 오직 Text만 들어감!
		// 이걸로 버튼의 글자도 바꿀 수 있따 Button은 TextView의 자식이기 때문이지롱
		
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		// 얘는 New가 없다. 1개바꼐 없기 때문이다.
		
		ComponentName provider = new ComponentName(context, AddCountProvider.class);
		// 1번째 인자 Context, 2번째는 class 이름을 적어야 햄
		manager.updateAppWidget(provider, views);
		// 여긴 int[]이 없기 때문에 위처럼 ComponentName을 만들어서 addCountProvider로 전달해줘
		//여기서 UI 변경을 해준다.
	}

	void doStopService(Context context, Intent intent) {
		Intent service = new Intent(context, CountService.class);
		context.stopService(service);
		
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appcount1);
		// 1번째 package 명 2번째 접근하고 싶은 layout!
		
		views.setImageViewResource(R.id.imageView1, R.drawable.icon01);
		Intent intent1 = new Intent("aaa.bbb.action.START");
		// "aaa...." 에는 패키지명이 와야 해
		// Menifrest에 등록하면 돼
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
				intent1, PendingIntent.FLAG_CANCEL_CURRENT);
		
		views.setTextViewText(R.id.button1, "Start");
		views.setOnClickPendingIntent(R.id.button1, pendingIntent);
		
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		// 얘는 New가 없다. 1개바꼐 없기 때문이다.
		
		ComponentName provider = new ComponentName(context, AddCountProvider.class);
		// 1번째 인자 Context, 2번째는 class 이름을 적어야 햄
		manager.updateAppWidget(provider, views);
		// 여긴 int[]이 없기 때문에 위처럼 ComponentName을 만들어서 addCountProvider로 전달해줘
		//여기서 UI 변경을 해준다.
	}
	
	void doStartService(Context context, Intent intent){
		Intent service = new Intent(context, CountService.class);
		context.startService(service);
		
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appcount1);
		// 1번째 package 명 2번째 접근하고 싶은 layout!
		
		views.setImageViewResource(R.id.imageView1, R.drawable.icon02);
		
		Intent intent1 = new Intent("aaa.bbb.action.STOP");
		// "aaa...." 에는 패키지명이 와야 해
		// Menifrest에 등록하면 돼
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
				intent1, PendingIntent.FLAG_CANCEL_CURRENT);
		
		views.setTextViewText(R.id.button1, "Stop");
		views.setOnClickPendingIntent(R.id.button1, pendingIntent);
		
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		// 얘는 New가 없다. 1개바꼐 없기 때문이다.
		
		ComponentName provider = new ComponentName(context, AddCountProvider.class);
		// 1번째 인자 Context, 2번째는 class 이름을 적어야 햄
		manager.updateAppWidget(provider, views);
		// 여긴 int[]이 없기 때문에 위처럼 ComponentName을 만들어서 addCountProvider로 전달해줘
		//여기서 UI 변경을 해준다.
	}

}
