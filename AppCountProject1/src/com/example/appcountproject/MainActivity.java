package com.example.appcountproject;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";

	View.OnClickListener bHandler = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			doChangeAppWidget();

		}
	};

	void doChangeAppWidget() {
		Intent intent = new Intent(this, CountService.class);
		stopService(intent);
		// 우선 서비스를 멈춰주고!
		
		RemoteViews views = new RemoteViews(getPackageName(),
				R.layout.appcount1);
		// 1번째 package 명 2번째 접근하고 싶은 layout!

		views.setTextViewText(R.id.textView1, "clear");
		// 1번째 변경하고 싶은 UI의 ID 2번째 변경 넣고 싶은 Text 오직 Text만 들어감!
		// 이걸로 버튼의 글자도 바꿀 수 있따 Button은 TextView의 자식이기 때문이지롱

		AppWidgetManager manager = AppWidgetManager.getInstance(this);
		// 얘는 New가 없다. 1개바꼐 없기 때문이다.

		ComponentName provider = new ComponentName(this,
				AddCountProvider.class);
		// 1번째 인자 Context, 2번째는 class 이름을 적어야 햄
		manager.updateAppWidget(provider, views);
		// 여긴 int[]이 없기 때문에 위처럼 ComponentName을 만들어서 addCountProvider로 전달해줘
		// 여기서 UI 변경을 해준다.
		
		finish();
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		findViewById(R.id.button1).setOnClickListener(bHandler);

	}

}
