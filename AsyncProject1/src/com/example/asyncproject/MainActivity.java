package com.example.asyncproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	
	TextView tv;
	ProgressBar pBar;
	ProgressDialog pDialog;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			new JobTask().execute(20, 80, 2);
			switch(v.getId()) {
			case R.id.button1 :
				break;
			}
		}
	};
	
	class JobTask extends AsyncTask<Integer, Integer, String> {

		@Override
		protected void onPreExecute() {
			// Before Task; UI change
			pDialog = ProgressDialog.show(MainActivity.this, "Goni", "Downloading...");
		}

		@Override
		protected String doInBackground(Integer... params) {	// Logic NoNO

			int start = params[0];
			int end = params[1];
			int step = params[2];
			
			int num = start;
			while(num <= end) {
				num += step;
				if( num % 10 == 0 ){
					publishProgress(num);		// UI GoGo!!
				}
				Log.v(TAG, "num : " + num);
				SystemClock.sleep(400);
			}
			return "job Ok";
		}

		@Override
		protected void onPostExecute(String result) {	// UI okok
			// TODO Auto-generated method stub
			if(pDialog != null) {
				pDialog.cancel();
			}
			
			tv.setText(result);
			Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
			super.onPostExecute(result);
		}


		@Override
		protected void onProgressUpdate(Integer... values) { // Ui okok
			// TODO Auto-generated method stub
			tv.setText("value :" + values[0]);
			pBar.setProgress(values[0]);
			
			super.onProgressUpdate(values);
		}
		
	}
	
//	void aa() {
////		bb();
//		bb(1);
//		bb(1, 2);
//		bb(1, 2, 3);
//		bb(1, 2, 3, 4);
//	}
//	void bb(int b, int... a) { // 
//		int[] arr = a;
//		int tot = 0;
//		for(int i = 0; i < arr.length; i++) {
//			tot += a[i];
//		}
//		
//	}
	
	
//	void bb() {}
//	void bb(int a) {}
//	void bb(int a, int b) {}
//	void bb(int a, int b, int c) {}
//	void bb(int a, int b, int c, int d) {}
//	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv = (TextView)findViewById(R.id.textView1);
		pBar = (ProgressBar)findViewById(R.id.progressBar1);
		findViewById(R.id.button1).setOnClickListener(bHandler);
	}
}
