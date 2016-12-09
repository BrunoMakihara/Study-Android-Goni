package com.example.acceleratorproject;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	SensorManager manager = null;
	AcceleratorView myView = null;
	SensorEventListener sListener = new SensorEventListener() {

		public void onSensorChanged(SensorEvent event) {
			float[] values = event.values;
			switch (event.sensor.getType()) {
			case Sensor.TYPE_ACCELEROMETER:

				myView.setNewData(values);

				break;
			}
		}

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub

		}
	};

	@Override
	protected void onPause() {
		manager.unregisterListener(sListener);
		super.onPause();
	}

	@Override
	protected void onResume() {
		manager.registerListener(sListener,
				manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_UI);
		super.onResume();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_main);
		myView = (AcceleratorView) findViewById(R.id.acceleratorView1);

		manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	}
}
