package com.example.activityproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BoardActivity extends Activity {
	private static final String TAG = "MainActivity";
	private static final boolean DEBUG = true;
	
	TextView tv = null;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId()) {
			case R.id.button1 :
				Intent data = new Intent();		// 반환하고자 하는 인텐트는 생성자에 전달인자를 안넣어줘도 돼
				data.putExtra("result", "Kim Sung-Gon");
				setResult(RESULT_OK, data);
				finish();
				break;
			}
		}
	};
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.board);
	    
	    tv = (TextView)findViewById(R.id.textView1);
	    
	    Intent intent = getIntent();
	    String name = intent.getStringExtra("name");
	    tv.setText(name);
	    
	    findViewById(R.id.button1).setOnClickListener(bHandler);
	}

}
