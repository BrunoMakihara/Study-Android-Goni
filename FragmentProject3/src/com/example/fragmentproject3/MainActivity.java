package com.example.fragmentproject3;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity {
	private static final String TAG = "Goni";

	FragmentManager fManager = null;
	FragmentTransaction transaction = null;
	MyFragment1 fragment1;
	MyFragment2 fragment2;
	MyFragment3 fragment3;
	MyFragment4 fragment4;

	View.OnTouchListener tHandler = new View.OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			transaction = fManager.beginTransaction();
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				switch (v.getId()) {
				case R.id.textView1:
					if (fragment1 == null) {
						fragment1 = new MyFragment1();
					}
					transaction.replace(R.id.fl, fragment1);
					break;
				case R.id.textView2:
					if (fragment2 == null) {
						fragment2 = new MyFragment2();
					}
					transaction.replace(R.id.fl, fragment2);
					break;
				case R.id.textView3:
					if (fragment3 == null) {
						fragment3 = new MyFragment3();
					}
					transaction.replace(R.id.fl, fragment3);
					break;
				case R.id.textView4:
					if (fragment4 == null) {
						fragment4 = new MyFragment4();
					}
					transaction.replace(R.id.fl, fragment4);
					break;
				}
				// 바뀌는 replace Method를 Stack에 쌓아서 저장해놓고 취소버튼을 누르면
				// 방금했던 작업들을 하나씩 빼버리는 역할을 한다.
				// 맨 마지막으로 오면 (Stack을 다 써버리면) 취소버튼의 기능을 제대로 역할한다.
				transaction.addToBackStack(null);

				transaction.commit();
				break;
			}

			// 내가 처리했으니 뒤에서 처리하지마세요.
			return true;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.textView1).setOnTouchListener(tHandler);
		findViewById(R.id.textView2).setOnTouchListener(tHandler);
		findViewById(R.id.textView3).setOnTouchListener(tHandler);
		findViewById(R.id.textView4).setOnTouchListener(tHandler);

		fManager = getFragmentManager();
		transaction = fManager.beginTransaction();

		if (savedInstanceState == null) {
			fragment1 = new MyFragment1();
			transaction.add(R.id.fl, fragment1);
		}
		transaction.commit();

	}
}
