package com.example.mapsampleproject;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends Activity {
	private static final String TAG = "Goni";

	FragmentManager fManager = null;
	MapFragment fragment1 = null;
	GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fManager = getFragmentManager();
		fragment1 = (MapFragment) fManager.findFragmentById(R.id.fragment1);

		map = fragment1.getMap();

		LatLng position = new LatLng(37.560030555, 126.97535);

		map.moveCamera(CameraUpdateFactory
				.newCameraPosition(new CameraPosition.Builder()
						.target(position).zoom(17).bearing(0) // 지도보기 돌려보기
						.tilt(50) // 기울여서보기 ( 옆에서 보는 것 )
						.build()));
		
		TextView title = new TextView(this);
		title.setText("서울역");
		title.setTextSize(40);
		title.setTextColor(Color.BLACK);
		title.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP);
		
		ViewGroup group = (ViewGroup) fragment1.getView();
		group.addView(title);
		
		LatLng seoul = new LatLng(37.55464, 126.970700);
		GroundOverlay mGroundOverlay = null;
		mGroundOverlay = map.addGroundOverlay(new Gro)
	}
}
