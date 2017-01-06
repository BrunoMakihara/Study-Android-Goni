package com.example.intentproject;

import java.io.File;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "Goni";

	int[] resId = { R.id.button1, R.id.button2, R.id.button3, R.id.button4 };

	View.OnClickListener bHandler = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = null;
			switch (v.getId()) {
			case R.id.button1:
				// View로 그림이나 인터넷창을 보여주세요! 어떠한 방법이든 상관 없습니다.
				intent = new Intent(Intent.ACTION_VIEW,
						Uri.parse("http://m.google.com"));
				startActivity(intent);
				break;
			case R.id.button2:
				// intent = new Intent(Intent.ACTION_DIAL,
				// Uri.parse("tel:010-1111-1111"));
				// startActivityForResult(intent, 0);
				intent = new Intent(Intent.ACTION_CALL,
						Uri.parse("tel:010-1111-1111"));
				startActivityForResult(intent, 0);
				break;
			case R.id.button3:
				intent = new Intent(Intent.ACTION_VIEW);
				// File의 경로를 지정해줘야 함
				File f = new File("/mnt/sdcard/Daum.png");
				// 파일의 경로를 여기 지정함
				Uri data = Uri.fromFile(f);
				// data의 경로와 타입을 다 적어줌 (jpeg, gif등을 적어줘) Type이 있으면 더 정확해
				intent.setDataAndType(data, "image/png");
				startActivity(intent);
				break;
			case R.id.button4:
				intent = new Intent(Intent.ACTION_MAIN);
				// 에러가 떨어질 수 있으니까 꼭 try catch로 묶어라
				try {
					// 전재조건은 Package명과 시작하는 액티비티 이름을 알아야한다.
					// 1번째 Package명 2번째는 Package명을 포함한 클래스명을 다 적어줘야 한다.
					intent.setComponent(new ComponentName(
							"com.example.activityproject",
							"com.example.activityproject.MainActivity"));
					startActivity(intent);
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "경로가 올바르지 않습니다.",
							Toast.LENGTH_SHORT).show();
				}
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		for (int id : resId) {
			findViewById(id).setOnClickListener(bHandler);
		}
	}
}
