package com.example.frameproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private static final String TAG = "Goni";

	LinearLayout ll1, ll2, ll3;
	FrameLayout fl;

	View.OnTouchListener tHandler = new View.OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				Log.v(TAG, "down");
				switch (v.getId()) {
				case R.id.tv1:
					hideView();
					ll1.setVisibility(View.VISIBLE);
					break;
				case R.id.tv2:
					hideView();
					ll2.setVisibility(View.VISIBLE);
					break;
				case R.id.tv3:
					hideView();
					ll3.setVisibility(View.VISIBLE);
					break;
				}
				break;
			case MotionEvent.ACTION_UP:
				break;
			case MotionEvent.ACTION_MOVE:
				Log.v(TAG,
						"move : x : " + event.getX() + " y : " + event.getY());
				break;
			}
			// 이벤트 처리할 수 있는게 겹쳐있고 false면 위에가 처리 안하고 아래가 처리할거에요.
			// True일 경우 앞에서 이벤트 처리 했으니까 뒤에는 처리 하지 마세요.
			return true;
		}
	};

	void hideView() {
		ll1.setVisibility(View.INVISIBLE);
		ll2.setVisibility(View.INVISIBLE);
		ll3.setVisibility(View.INVISIBLE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fl = (FrameLayout) findViewById(R.id.fl);
		ll1 = (LinearLayout) findViewById(R.id.ll1);
		ll2 = (LinearLayout) findViewById(R.id.ll2);
		ll3 = (LinearLayout) findViewById(R.id.ll3);

		findViewById(R.id.tv1).setOnTouchListener(tHandler);
		findViewById(R.id.tv2).setOnTouchListener(tHandler);
		findViewById(R.id.tv3).setOnTouchListener(tHandler);
	}
}
