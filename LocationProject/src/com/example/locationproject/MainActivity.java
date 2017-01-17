package com.example.locationproject;

import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static final String TAG = "Goni";

	EditText et1, et2;
	LocationManager lManager = null;
	int cnt = 0;

	@Override
	protected void onStart() {
		super.onStart();
		// 2번째 초, 3번째 10쓰면 10미터가 안바뀌면 반응하지마!, 4번째 리스너 연결해라
		lManager.requestLocationUpdates("gps", 1000, 1, listener);
	}

	@Override
	protected void onStop() {
		// 현재 동작하고 있는 Listener를 꺼라!
		lManager.removeUpdates(listener);
		super.onStop();
	}

	LocationListener listener = new LocationListener() {

		@Override
		// 가장 중요함!
		public void onLocationChanged(Location location) {
			cnt++;

			String s = String.format(
					"현재 위치 조회수 : %d, 정보 위도 : %f, 경도 : %f, 고도 : %f \n", cnt,
					location.getLatitude(), location.getLongitude(),
					location.getAltitude());

			et2.append(s);
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

	};

	View.OnClickListener bHandler = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button1:
				doGetLocationInfo();
				break;
			case R.id.button2:
				doMapView();
				break;
			}

		}
	};

	public void doGetLocationInfo() {
		// Internet Permission 필수!!
		double lat = 37.5665;
		double lon = 126.9780;
		String locName = "대전역";

		Geocoder coder = null;

		try {
			coder = new Geocoder(this, Locale.KOREAN);
			// 3번째 최대 숫자 반환 Type은 List 제일 처음에 나오는 것이 정확도가 가장 좋은 놈
			// 굳이 반복문을 사용하지 않아도 좋다.
			// List<Address> list = coder.getFromLocation(lat, lon, 5);
			List<Address> list = coder.getFromLocationName(locName, 5);

			Address address = null;

			for (int i = 0; i < list.size(); i++) {
				address = list.get(i);
				// getCountryName지역명
				String s = String.format("주소 지역명 : %s, SubAdminArea : %s"
						+ "FeatureName : %s, getPhone : %s, 위도 : %f, 경도 : %f",
						address.getCountryName(), address.getSubAdminArea(),
						address.getFeatureName(), address.getPhone(),
						address.getLatitude(), address.getLongitude());

				if (i == 0) {
					et1.setText(s);
				}
				Log.v(TAG, s);
			}

		} catch (Exception e) {
			Log.v(TAG, "위치 정보 오류 : " + e);
		}

	}

	void doMapView() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		// 위/경도는 geo:으로 시작함 z ( 줌레벨 )
		String uriString = String.format("geo:%f,%f?z=17", 37.560030555,
				126.97535);
		Uri uri = Uri.parse(uriString);
		intent.setData(uri);
		startActivityForResult(intent, 999);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et1 = (EditText) findViewById(R.id.editText1);
		et2 = (EditText) findViewById(R.id.editText2);

		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);

		// getSystemService는 Context가 가지고 있다.
		lManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		// 마지막 위치를 얻어오는 것! LocationManager.GPS_PROVIDER = "gps" 라고 써도 무방하다.
		// 반환타입은 Location으로 나온다,
		Location location = lManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		if (location != null) {
			// 1번째 문자열, 2번째 여러개 쓸 수 있다.
			String s = String.format(
					"마지막 알게된 위치 정보 위도 : %f, 경도 : %f, 고도 : %f \n",
					location.getLatitude(), location.getLongitude(),
					location.getAltitude());
			Log.v(TAG, s);
			et2.append(s);
		}
	}
}
