package com.example.downimageproject;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private static final String TAG = "Goni";
	ImageView img;

	View.OnClickListener bHandler = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button1:
				// 방법2
				Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
						R.drawable.icon01);
				img.setImageBitmap(bitmap);

				// 방법 1
				// img.setImageResource(R.drawable.icon01);
				break;
			case R.id.button2:
				doReadSDCardImage("icon02.png");
				break;
			case R.id.button3:
				doReadURLImage1("https://www.google.co.kr/images/srpr/logo11w.png");
				break;
			case R.id.button4:
				doReadURLImage2("https://www.google.co.kr/images/srpr/logo11w.png");
				break;
			}
		}
	};

	// 이미지가 저장되어있나 판단 후 이미지 없으면 인터넷에서 불러오고 저장하고 보여주기
	// 이미지가 있으면 sdCard 이미지 보여주기
	public void doReadURLImage2(String imgURL) {
		// 확장자가 있으면 갤러리에서 자꾸 나온다. 그래서 확장자 빼고 저장하는 방법
		// 앞에는 포함 뒤에는 불포함!
		String fName = imgURL.substring(imgURL.lastIndexOf("/") + 1,
				imgURL.lastIndexOf("."));

		Log.v(TAG, "파일명 : " + fName);

		File f = new File(Environment.getExternalStorageDirectory(), fName);
		if (f.exists()) {
			doReadSDCardImage(fName);
		} else {
			new DownImageTask1().execute(imgURL, f);
		}

		// lastIndexOf 가장 마지막에서 가까운 "/"를 찾아준다!
		// 거기서 +1을 해주면 "/" 다음 자리부터 마지막까지
		// String fName = imgURL.substring(imgURL.lastIndexOf("/") + 1);
		// new DownImageTask().execute(imgURL);
	}

	class DownImageTask1 extends AsyncTask<Object, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(Object... params) {
			String url = params[0].toString();
			File saveName = (File) params[1];

			Bitmap bitmap = null;

			HttpClient client = null;
			HttpGet request = null;
			HttpResponse response = null;
			int code = 0;

			try {
				client = NetManager.getHttpClient();
				request = NetManager.getGet(url);
				response = client.execute(request);
				code = response.getStatusLine().getStatusCode();

				switch (code) {
				case 200:
					byte[] imgData = EntityUtils.toByteArray(response
							.getEntity());
					Log.v(TAG, "readData size : " + imgData.length);

					// 1번째 바이트 배열, 2번째 0, 3번째 바이트 배열의 길이
					bitmap = BitmapFactory.decodeByteArray(imgData, 0,
							imgData.length);

					// 비트맵을 저장하는 방법 비트맵을 만들었다는 전제조건 하에 
					// 1번째 이미지의 확장자명을 설정, 2번째 해상도(퀄리티?)를 가르쳐줌 100이 리얼 사이즈
					// 100을 주면 용량을 커도 해상도가 좋다
					// 3번째 출력할 File의 경로와 이름을 넣어라.
					bitmap.compress(Bitmap.CompressFormat.PNG, 100,
							new FileOutputStream(saveName));
					break;
				}

			} catch (Exception e) {
				Log.v(TAG, "image loading error : " + e);
			} finally {

			}

			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// Super 지워 하는일 없어
			if (result != null) {
				img.setImageBitmap(result);
			}
		}

	}

	class DownImageTask extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			String url = params[0];
			Bitmap bitmap = null;

			HttpClient client = null;
			HttpGet request = null;
			HttpResponse response = null;
			int code = 0;

			try {
				client = NetManager.getHttpClient();
				request = NetManager.getGet(url);
				response = client.execute(request);
				code = response.getStatusLine().getStatusCode();

				switch (code) {
				case 200:
					byte[] imgData = EntityUtils.toByteArray(response
							.getEntity());
					Log.v(TAG, "readData size : " + imgData.length);

					// 1번째 바이트 배열, 2번째 0, 3번째 바이트 배열의 길이
					bitmap = BitmapFactory.decodeByteArray(imgData, 0,
							imgData.length);
					break;
				}

			} catch (Exception e) {
				Log.v(TAG, "image loading error : " + e);
			} finally {

			}

			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// Super 지워 하는일 없어
			if (result != null) {
				img.setImageBitmap(result);
			}
		}

	}

	public void doReadURLImage1(String imgURL) {
		new DownImageTask().execute(imgURL);
	}

	public void doReadSDCardImage(String fName) {
		// 외장 메모리의 File 관리자
		// /mnt/sdCard/icon02.png 이 경로를 접속하기 위한 객체화
		File sdFile = Environment.getExternalStorageDirectory();
		File f = new File(sdFile, fName);

		if (!f.exists()) {
			Log.v(TAG, fName + "파일이 존재하지 않습니다.");
			return;
		}
		// 안드로이드에서 사용하는 모든 Image는 BitMap이다.
		Bitmap bitmap = null;
		bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
		img.setImageBitmap(bitmap);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		img = (ImageView) findViewById(R.id.imageView1);

		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);
		findViewById(R.id.button3).setOnClickListener(bHandler);
		findViewById(R.id.button4).setOnClickListener(bHandler);
	}
}
