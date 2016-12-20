package com.example.eventproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static final String TAG = "Goni";

	Button btnAdd, btnSub;
	EditText etResult;
	int i = 0;

	// MyListener bHandler = new MyListener();
	//
	// class MyListener implements View.OnClickListener {
	// @Override
	// public void onClick(View v) {
	// Log.v(TAG, "Click!");
	// etResult.append(i++ + "");
	// }
	//
	// }

	// 위에를 축약하면 아래와 같은 소스가 됨
	View.OnClickListener bHandler = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btnAdd:
				etResult.setText(++i + "");
				break;
			case R.id.btnSub:
				etResult.setText(--i + "");
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnAdd = (Button) findViewById(R.id.btnAdd);
		btnSub = (Button) findViewById(R.id.btnSub);
		etResult = (EditText) findViewById(R.id.etResult);

		btnAdd.setOnClickListener(bHandler);
		btnSub.setOnClickListener(bHandler);
	}
}
