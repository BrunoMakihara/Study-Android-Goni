package com.example.lifeproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static final String TAG = "Goni";
	EditText et1;
	int x;
	int y;

	View.OnClickListener bHandler = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button1:
				Intent intent = new Intent(getApplicationContext(),
						SubActivity.class);
				startActivityForResult(intent, 0);
				break;
			case R.id.button2:
				x++;
				y++;
				Log.v(TAG, "x : " + x + ", y : " + y);
				et1.setText("x : " + x + ", y : " + y);
				break;
			}
		}
	};

	void saveData() {
		// 1번째 File의 이름( .xml 확장자는 알아서 붙혀 줌 ), 2번째 접근할 수 있게 하는 것?
		// 2번째 MODE_PRIVATE( = 0)일 경우 내 패키지만 접근 가능하다.
		SharedPreferences sp = getSharedPreferences("appDate", MODE_PRIVATE);
		// SharedPreferences.Editor 얘가 수정하고 삭제하고 등등을 다 함
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt("y", y);

		// 이거를 꼭 써줘야 진짜로 저장이 된다.
		editor.commit();
	}

	void loadData() {
		// 1번째 File의 이름( .xml 확장자는 알아서 붙혀 줌 ), 2번째 접근할 수 있게 하는 것?
		// 2번째 MODE_PRIVATE( = 0)일 경우 내 패키지만 접근 가능하다.
		SharedPreferences sp = getSharedPreferences("appDate", MODE_PRIVATE);
		// 불러오는 것은 그냥 sp임
		y = sp.getInt("y", 99999);

	}

	@Override
	// outState Bundle을 여기서 저장하면 onCreate Bundle과 같기 때문에 저기서 불러 짐
	protected void onSaveInstanceState(Bundle outState) {
		// 여기에 현재 재생중인 목록들을 저장하고 사용해
		// 1번째 이름, 2번째 저장할 실 Data
		outState.putInt("x", x);
		super.onSaveInstanceState(outState);
	}

	@Override
	//
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// onStop()을 타고나면 null이 아니야
		// savedInstanceState는 처음에는 Null이기 때문에 이프문 걸어줘야 해!
		if (savedInstanceState != null) {
			x = savedInstanceState.getInt("x");
		}

		loadData();

		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);
		Log.v(TAG, "Main onCreate " + savedInstanceState);

		et1 = (EditText) findViewById(R.id.et1);

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.v(TAG, "Main onStart");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v(TAG, "Main onResume");
	}

	@Override
	protected void onStop() {
		Log.v(TAG, "Main onStop");
		super.onStop();
	}

	@Override
	protected void onPause() {
		Log.v(TAG, "Main onPause");
		saveData();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		Log.v(TAG, "Main onDestory");
		super.onDestroy();
	}
}
