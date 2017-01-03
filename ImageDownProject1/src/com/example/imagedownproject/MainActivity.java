package com.example.imagedownproject;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";

	ImageView img;
	int[] resId = { R.id.button1, R.id.button2, R.id.button3, R.id.button4 };
	View.OnClickListener bHandler = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button1:
				doChangeImageRes(R.drawable.daum);
				// drawable에 있는 이미지를 불러서 옴
				break;
			case R.id.button2:
				DoChangeImageSDCard("daum.png");
				// SDCard에 있는 이미지를 불러서 바꿔줌
				break;
			case R.id.button3:
				doChangeImageURL1(Comm.BASIC_URL + "152729032.png");
				break;
			case R.id.button4:
				doChangeImageURL2(Comm.BASIC_URL + "152729032.png");
				break;
			}
		}
	};

	protected void doChangeImageURL2(String imgURL) {
		Log.v(TAG, "down Test");
		File sdFile = Environment.getExternalStorageDirectory();

		int start = imgURL.lastIndexOf("/") + 1;
		// 오른쪽 끝에서부터 "/"를 찾아라!!
		int end = imgURL.lastIndexOf(".");
		// 오른쪽 끝에서부터 "."을 찾아라!!
		String fName = imgURL.substring(start, end);
		// 152729032 이만큼만 가져옴!
		// http://icon.daumcdn.net/w/icon/1312/19/152729032.png

		Log.v(TAG, "fName : " + fName);

		File imgFile = new File(sdFile, fName);
		if (imgFile.exists()) {
			// 이미지가 존재하면!
			Log.v(TAG, "이미지가 존재 함!");
			Bitmap bm = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
			// decode 뒤에 이미지 파일이 저장되어 있는 경로를 설정해주고
			// BitmapFactory 가 비트맵으로 만들어서 던저준다.
			img.setImageBitmap(bm);
		} else {
			// 이미지가 존재하지 않으면
			Log.v(TAG, "파일 다운로드 후 저장하고 이미지 보여주기");
			new DownImageTask1().execute(imgURL, imgFile);
		}
	}

	protected void doChangeImageURL1(String imgURL) {// 인터넷에서 이미지 받아와서 체인지
		Log.v(TAG, "down Test");

		new DownImageTask().execute(imgURL);
	}

	class DownImageTask1 extends AsyncTask<Object, Void, Bitmap> {
		// String과 File을 받기 위해 Object로 받음
		@Override
		protected Bitmap doInBackground(Object... params) {
			// 여기 작업이 끝나면 onPost로 간다.
			Bitmap bitmap = null;

			String url = params[0].toString();

			File imgFile = (File) params[1];

			HttpClient clinet = null;
			HttpGet request = null;
			// get 방식의 접근 객체
			HttpResponse response = null;

			InputStream is = null;
			DataInputStream dis = null;
			// 읽어드리는데 필터를 가진 다양한 메소드를 지원하는 놈
			int code = 0;
			int oneByte = 0;
			ByteArrayOutputStream baos = null;
			// 바이트 배열의 출력 용 출력은 일단 메모리에 한다/

			try {
				clinet = NetManager.getHttpClient();
				// 클라이언트를 엮고
				request = NetManager.getGet(url);
				// 내가 접속하고자 하는 주소를 넣어 줘
				response = clinet.execute(request);
				code = response.getStatusLine().getStatusCode();
				// code를 가져온다.
				Log.v(TAG, "code : " + code);
				switch (code) {
				case 200:
					is = response.getEntity().getContent();
					// getEntity입출력을 모두 관리하는 얘
					// getContentLength() 나에게 보내는 byte의 길이

					byte[] imgArr = null;
					/* 방법 2 */
					int size = (int) response.getEntity().getContentLength();
					// 나에게 보내는 Data의 길이 !! 그 길이를 받아서!
					if (size == -1) {
						baos = new ByteArrayOutputStream();
						while ((oneByte = is.read()) != -1) { // 1바이트씩 읽어와야 돼
							// is.read는 1바이트씩 보내줘
							baos.write(oneByte);
							// 차곡차곡 쓸 수 있다 끝가지 쭉 ~~
							imgArr = baos.toByteArray();
						}
					} else {
						Log.v(TAG, "size : " + size);
						imgArr = new byte[size];

						dis = new DataInputStream(is);
						dis.readFully(imgArr, 0, size);
						// 꽉 찰때까지 읽어줘!
						// 1번째 바이트 배열에 !! 2번째 어디서부터!? 3번째 얼마만큼!!?
					}

					/* 방법1 1바이트씩 읽는 방법은 잘 쓰이지 않아. */
					// baos = new ByteArrayOutputStream();
					// while( (oneByte = is.read()) != -1 ){ // 1바이트씩 읽어와야 돼
					// // is.read는 1바이트씩 보내줘
					// baos.write(oneByte);
					// // 차곡차곡 쓸 수 있다 끝가지 쭉 ~~
					// }
					// baos.tobyteArray는 자기가 가지고 있는 바이트를 전부 넘겨 줘
					// 이미지를 만들 때는 byte[] 이 필요함!!

					bitmap = BitmapFactory.decodeByteArray(imgArr, 0,
							imgArr.length);
					// 1번째 Byte[] 이고 3번은 Byte[]의 길이를 넣어주면 돼

					bitmap.compress(CompressFormat.PNG, 100,
							new FileOutputStream(imgFile));
					// 이미지를 저장할 수 있게 해주는 것 File에 Save해줄 수 있는 것
					// 혹은 인터넷으로 보낼 수 있음 또한 png->jpg로 바꿀 수도 있음
					// 블루투스를 이용하면 상대방에게 블루투스 정보를 줄 수 있다.
					// 1번은 포맷을 바꿔주고 2번째는 해상도를 떨굴 수 있따. 즉 메모리를 좀 아낌
					// 3번째는 저장되는 경로야! 
					break;
				}
			} catch (IOException e) {
				Log.v(TAG, "image down fail : " + e);
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {

					}
				}

			}

			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) { // 끝나고 실행 되는 메서드

			if (result != null) {
				img.setImageBitmap(result);
			} else {
				Toast.makeText(MainActivity.this, "이미지 다운 실패",
						Toast.LENGTH_SHORT).show();
			}

			super.onPostExecute(result);
		}
		// 1번째 URL이 넘어 오는 걸로 세팅 2번째는 없을 경우 대문자로 Void 3번 결론적으로 체인지 해야 되기 때문에
		// Bitmap으로 받아와라
		// 1번째 다운로드 받을 부분 3번째 이미지를 바꿀 부분

	}

	class DownImageTask extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			// 여기 작업이 끝나면 onPost로 간다.
			Bitmap bitmap = null;

			String url = params[0];

			HttpClient clinet = null;
			HttpGet request = null;
			// get 방식의 접근 객체
			HttpResponse response = null;

			InputStream is = null;
			DataInputStream dis = null;
			// 읽어드리는데 필터를 가진 다양한 메소드를 지원하는 놈
			int code = 0;
			int oneByte = 0;
			ByteArrayOutputStream baos = null;
			// 바이트 배열의 출력 용 출력은 일단 메모리에 한다/

			try {
				clinet = NetManager.getHttpClient();
				// 클라이언트를 엮고
				request = NetManager.getGet(url);
				// 내가 접속하고자 하는 주소를 넣어 줘
				response = clinet.execute(request);
				code = response.getStatusLine().getStatusCode();
				// code를 가져온다.
				Log.v(TAG, "code : " + code);
				switch (code) {
				case 200:
					is = response.getEntity().getContent();
					// getEntity입출력을 모두 관리하는 얘
					// getContentLength() 나에게 보내는 byte의 길이

					byte[] imgArr = null;
					/* 방법 2 */
					int size = (int) response.getEntity().getContentLength();
					// 나에게 보내는 Data의 길이 !! 그 길이를 받아서!
					if (size == -1) {
						baos = new ByteArrayOutputStream();
						while ((oneByte = is.read()) != -1) { // 1바이트씩 읽어와야 돼
							// is.read는 1바이트씩 보내줘
							baos.write(oneByte);
							// 차곡차곡 쓸 수 있다 끝가지 쭉 ~~
							imgArr = baos.toByteArray();
						}
					} else {
						Log.v(TAG, "size : " + size);
						imgArr = new byte[size];

						dis = new DataInputStream(is);
						dis.readFully(imgArr, 0, size);
						// 꽉 찰때까지 읽어줘!
						// 1번째 바이트 배열에 !! 2번째 어디서부터!? 3번째 얼마만큼!!?
					}

					/* 방법1 1바이트씩 읽는 방법은 잘 쓰이지 않아. */
					// baos = new ByteArrayOutputStream();
					// while( (oneByte = is.read()) != -1 ){ // 1바이트씩 읽어와야 돼
					// // is.read는 1바이트씩 보내줘
					// baos.write(oneByte);
					// // 차곡차곡 쓸 수 있다 끝가지 쭉 ~~
					// }
					// baos.tobyteArray는 자기가 가지고 있는 바이트를 전부 넘겨 줘
					// 이미지를 만들 때는 byte[] 이 필요함!!

					bitmap = BitmapFactory.decodeByteArray(imgArr, 0,
							imgArr.length);
					// 1번째 Byte[] 이고 3번은 Byte[]의 길이를 넣어주면 돼
					break;
				}
			} catch (IOException e) {
				Log.v(TAG, "image down fail : " + e);
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {

					}
				}

			}

			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) { // 끝나고 실행 되는 메서드

			if (result != null) {
				img.setImageBitmap(result);
			} else {
				Toast.makeText(MainActivity.this, "이미지 다운 실패",
						Toast.LENGTH_SHORT).show();
			}

			super.onPostExecute(result);
		}
		// 1번째 URL이 넘어 오는 걸로 세팅 2번째는 없을 경우 대문자로 Void 3번 결론적으로 체인지 해야 되기 때문에
		// Bitmap으로 받아와라
		// 1번째 다운로드 받을 부분 3번째 이미지를 바꿀 부분

	}

	void DoChangeImageSDCard(String fName) { // SDCard에 있는 이미지 체인지
		File sdFile = Environment.getExternalStorageDirectory();
		File imgFile = new File(sdFile, fName);

		// imgFile.delete(); // sdFile 경로에 있는 fName의 파일을 삭제
		// 퍼미션이 필요해 쓰기 권한을 얻어야 하잖아

		Bitmap bm = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
		// decode 뒤에 이미지 파일이 저장되어 있는 경로를 설정해주고
		// BitmapFactory 가 비트맵으로 만들어서 던저준다.
		img.setImageBitmap(bm);
	}

	void doChangeImageRes(int imgRes) { // Drawbla에 있는 이미지 체인지
		img.setImageResource(imgRes);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		img = (ImageView) findViewById(R.id.imageView1);
		for (int id : resId) {
			findViewById(id).setOnClickListener(bHandler);
		}
	}
}
