package com.example.appwidgetproject;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;

public class MyAppWidgetProvider extends AppWidgetProvider {
	private static final String TAG = "MainActivity";

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		Log.v(TAG, "onDelete");		// Widget이 삭제될 때마다 호출이 된다.
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		Log.v(TAG, "onDisabled");	// Home 화면에 Widget이 없을 경우 실행이 된다.
		super.onDisabled(context);
	}

	@Override
	public void onEnabled(Context context) {
		Log.v(TAG, "onEnabled");	// 최초 실행 시 실행이 된다.
		super.onEnabled(context);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) { // 여기서 UI를 바꿀 수 있다.
		// 만들거나 update될 경우 호출 된다.
		Log.v(TAG, "onUpdate");
		
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget);
		//첫번째 패키지명, 2번째 R.java
		//views로 다 바꿀 수 있다. 다 set으로 접근 하면 돼 findViewbyId 이런거 없어
		
		views.setTextViewText(R.id.textView1, "변경문자열");
		// 1번째 ID를 입력 2번째 실제로 바뀌는 인자값
		
		
		appWidgetManager.updateAppWidget(appWidgetIds, views);
		//AppWidgetManager appWidgetManager 얘가 진짜로 마지막으로 바꾸는 것
		// 1번째 int[] 을 넣고 (3번째 인자에 있어 onUpdate)그대로 쓰고, 2번째는 내가 변경하려는 RemoteViews야!
		
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	
	
}
