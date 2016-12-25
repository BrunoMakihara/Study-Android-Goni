package com.example.fragmentproject2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

public class MainActivity extends FragmentActivity {
	private static final String TAG = "Goni";

	FragmentManager fManager = null;
	FragmentTransaction transaction = null;
	MyFragment2 fragment = null;
	FrameLayout fl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fl = (FrameLayout) findViewById(R.id.fl);

		fManager = getSupportFragmentManager();
		// Transaction을 만들어 줌
		transaction = fManager.beginTransaction();

		// 그냥 생성자만 넘어가면 된다.
		fragment = new MyFragment2();

		// add하면 Transaction에 집어 넣음
		// 1번째 집어넣을 ID, 2번째는 Fragment를 넣어!
		// 2번의 Fragment를 1번에 넣어주세요.
		// transaction.add(R.id.fl, fragment);

		// 반환 Type이 FragmentTransaction이기 때문에 바로 .commit을 붙힐 수 있다.
		transaction.replace(R.id.fl, fragment).commit();

		// transaction.commit();
	}
}
