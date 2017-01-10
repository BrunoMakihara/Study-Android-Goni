package com.example.lifeproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SubActivity extends Activity {
	private static final String TAG = "Goni";


	View.OnClickListener bHandler = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button1:
				finish();
				break;
			}
		}
	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sub);

		findViewById(R.id.button1).setOnClickListener(bHandler);
		Log.v(TAG, "Sub onCreate " + savedInstanceState);
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
		Log.v(TAG, "Sub onResume");
	}

	@Override
	protected void onStop() {
		Log.v(TAG, "Sub onStop");
		super.onStop();
	}

	@Override
	protected void onPause() {
		Log.v(TAG, "Sub onPause");
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		Log.v(TAG, "Sub onDestory");
		super.onDestroy();
	}

}
