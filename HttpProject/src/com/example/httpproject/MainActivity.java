package com.example.httpproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ResponseCache;
import java.net.URL;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Entity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static final String TAG = "Goni";
	EditText et1 = null;
	boolean onAir = false;
	ProgressDialog pDialog = null;

	View.OnClickListener bHandler = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button1:
				// 네트워크는 느리기 때문에 항상 Thread를 사용해라.
				pDialog = ProgressDialog.show(MainActivity.this, "다운로드",
						"Downloading...");
				// apach 사용하기!
				new DownThread1().start();
				// URL Connection 이용하기
				// new DownThread().start();

				break;
			}

		}
	};

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// Super 지워 하는 일 없어!
			if (pDialog != null) {
				pDialog.cancel();
			}
			switch (msg.what) {
			// 정상
			case 999:
				et1.setText(msg.obj.toString());
				break;
			// code 에러
			case 888:
				et1.setText(msg.obj.toString() + msg.arg1);
				break;
			// Exception 에러
			case 777:
				et1.setText(msg.obj.toString());
				break;
			}
		}

	};

	// URL Connection 이용하기
	public class DownThread extends Thread {
		public void run() {
			/** 네트워크 접근하는 방법은 2가지 그 중 1번째 전통적 네트워크 접속 방법 */
			String webURL = "http://www.google.com";
			URL url = null;
			// 얘로 http를 붙을꺼야 개념은 URL의 자식이 HTTP야
			HttpURLConnection connection = null;

			int code;
			Message msg = null;

			// Byte단위로 읽어진다.
			InputStream is;
			// 한줄씩 읽게 해준다.
			BufferedReader br = null;
			// 반복을 읽을려면 !
			String s;
			// 문자열얼 Buffer처럼 관리하는 아이입니다.
			StringBuilder sb = new StringBuilder();
			try {
				msg = handler.obtainMessage();
				// try catch로 묶어라.
				url = new URL(webURL);

				// 반환 타입은 URL이고 우리는 http로 붙을꺼다.
				// try catch로 묶어라. (catch만 써줘도 되네?)
				// 이 상태에서 접속 객체가 만들어지고 실제로 접속이 된다.
				connection = (HttpURLConnection) url.openConnection();

				// 웹서버 Code를 알려줌
				// 100 - > 버퍼링 중 (아직 보내는 중 )
				// 200 - > OK
				// 300 - > a.html로 접속을 했는데 서버에서 b.html로 접속 해! 그래서 다시 b.html로 붙음
				// ( 리다이렉션)
				// 400 - > Client Error ( 웹주소를 잘못 친거나 등등... )
				// 500 - > Server Error ( Null, 배열, 등등... )
				code = connection.getResponseCode();

				// Thread 내에서는 UI체인지가 불가능하다.
				switch (code) {
				// HttpURLConnection.HTTP_OK = 200 (이게 200이다)
				case HttpURLConnection.HTTP_OK:
					is = connection.getInputStream();
					// InputStreamReader로 읽으면 글자단위로 읽는다.
					// UTF-8 / EUC-KR 다르면 한글이 깨지니까 그럴 떄 2번째 인자에 추가해줘
					br = new BufferedReader(new InputStreamReader(is, "euc-kr"));
					// br = new BufferedReader(new InputStreamReader(is));

					// br은 한줄씩 읽어오는데 읽어올 줄이 많으니까 끝날 때 까지 무한 반복
					// sb에 계속 s를 넣어라.
					while ((s = br.readLine()) != null) {
						sb.append(s).append('\n');
					}

					msg.what = 999;
					msg.obj = sb.toString();
					break;
				default:
					msg.what = 888;
					msg.obj = "Code 에러 : ";
					msg.arg1 = code;
					break;
				}
			} catch (MalformedURLException e) {
				Log.v(TAG, "웹 주소 양식이 틀렸습니다.");
			} catch (IOException e) {
				Log.v(TAG, "연결 에러" + e);
				msg.what = 777;
				msg.obj = "Exception 에러" + e;
			} finally {

			}
			handler.sendMessage(msg);
		}
	}

	// apach 사용하기!
	public class DownThread1 extends Thread {
		public void run() {
			/** 네트워크 접근하는 방법은 2가지 그 중 2번째 아파치 접속 방법 */
			String webURL = "http://m.naver.com";

			HttpClient client;
			// Server로 전달하는 방식은 총 7가지 중 Get, Post를 자주 사용한다
			// 서버로 요청보낸다.
			HttpGet request;
			// 서버에서 응답받는 객체
			HttpResponse reponse;

			int code;
			Message msg = null;

			// Byte단위로 읽어진다.
			InputStream is;
			// 한줄씩 읽게 해준다.
			BufferedReader br = null;
			// 반복을 읽을려면 !
			String s;
			// 문자열얼 Buffer처럼 관리하는 아이입니다.
			StringBuilder sb = new StringBuilder();
			try {
				msg = handler.obtainMessage();

				// NetManager 사용 10초마다 타임아웃 발생하는 것
				client = NetManager.getHttpClient();

				// clinent를 생성 ( 아파치 )
				// client = new DefaultHttpClient();

				// NetManager 사용
				request = NetManager.getGet(webURL);

				// 응답받는 객체를 생성 접속하고자 하는 URL을 넣어줘 ( 아파치 )
				// request = new HttpGet(webURL);

				// execute 하는 순간 접속이 이루어진다.
				// 보낸 모든 것은 response를 통해 관리가 된다. 모든 응답코드는 reponse에 있다.
				reponse = client.execute(request);

				// getStatusLine 모든 상태를 관리해 거기서 .getStatusCode 하면 코드를 얻는다.
				code = reponse.getStatusLine().getStatusCode();

				// Thread 내에서는 UI체인지가 불가능하다.
				switch (code) {
				// HttpURLConnection.HTTP_OK = 200 (이게 200이다)
				case HttpURLConnection.HTTP_OK:
					// 반복문을 안사용해도 문자열에 알아서 다 넣어준다.
					s = EntityUtils.toString(reponse.getEntity());

					/** 1번 방법 */
					// 이러면 inputstream을 넘겨준다.
					// is = reponse.getEntity().getContent();

					// InputStreamReader로 읽으면 글자단위로 읽는다.
					// UTF-8 / EUC-KR 다르면 한글이 깨지니까 그럴 떄 2번째 인자에 추가해줘
					// br = new BufferedReader(new InputStreamReader(is));
					// br = new BufferedReader(new InputStreamReader(is));

					// br은 한줄씩 읽어오는데 읽어올 줄이 많으니까 끝날 때 까지 무한 반복
					// sb에 계속 s를 넣어라.
					// while ((s = br.readLine()) != null) {
					// sb.append(s).append('\n');
					// }

					msg.what = 999;
					msg.obj = s;
					// 1번 방법
					// msg.obj = sb.toString();
					break;
				default:
					msg.what = 888;
					msg.obj = "Code 에러 : ";
					msg.arg1 = code;
					break;
				}
			} catch (MalformedURLException e) {
				Log.v(TAG, "웹 주소 양식이 틀렸습니다.");
			} catch (IOException e) {
				Log.v(TAG, "연결 에러" + e);
				msg.what = 777;
				msg.obj = "Exception 에러" + e;
			} finally {

			}
			handler.sendMessage(msg);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et1 = (EditText) findViewById(R.id.editText1);
		findViewById(R.id.button1).setOnClickListener(bHandler);
	}
}
