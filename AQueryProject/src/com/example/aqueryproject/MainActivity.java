package com.example.aqueryproject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.ImageOptions;

public class MainActivity extends Activity {
	// AQuery를 쓰기 위해서 AQeury의 맴버변수로 추가하고 생성자 만들어서 사용하면 됨
	AQuery aq = null;
	int cnt = 0;

	public void setInit() {
		aq.id(R.id.editText1).text("초기화");
		aq.id(R.id.imageView1).image(R.drawable.ic_launcher);
	}

	public void doAdd() {
		cnt++;
		aq.id(R.id.editText1).text("cnt : " + cnt);
		aq.id(R.id.imageView1).image(
				"https://www.google.co.kr/images/srpr/logo11w.png");
	}

	public void setChangeImage() {
		// aq.id(R.id.imageView1).image(
		// "https://www.google.co.kr/images/srpr/logo11w.png");

		String url = "https://www.google.co.kr/images/srpr/logo11w.png";

		ImageOptions options = new ImageOptions();
		options.round = 15;

		aq.id(R.id.imageView1).image(url, options);
	}

	public void getNetData() {
		String url = "http://m.naver.com";
		// 얘는 이미지
		// String url = "https://www.google.co.kr/images/srpr/logo11w.png";

		// 1번째 접속하고자 하는 url 2번째 Return 받고자 하는 Type
		// 3번째 다 받아지면 그 때 이벤트 처리해야 함 <Type>에는 2번쨰 인자를 주면 됨
		// 3번째에서 재정의 해서 사용할 것
		aq.ajax(url, String.class, new AjaxCallback<String>() {

			@Override
			// callback Method를 오버라이딩 해
			// 2번째 object가 받은 Data
			public void callback(String url, String object, AjaxStatus status) {
				// Super지워 하는 일 없어
				aq.id(R.id.editText1).text(object);
			}

		});

		// 이미지를 줄 경우
		// aq.ajax(url, Bitmap.class, );
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// AQuery를 쓰기 위해서 AQeury의 맴버변수로 추가하고 생성자 만들어서 사용하면 됨
		aq = new AQuery(this);

		// 원래의 방법
		// EditText et1 = (EditText) findViewById(R.id.editText1);
		// et1.setText("Android");
		// et1.setTextSize(20);
		// et1.setTextColor(Color.RED);

		// .id()가 findViewById와 같은 것 set이 거의 다 빠져있어.
		aq.id(R.id.editText1).text("Android").textColor(Color.RED).textSize(20);

		// clicked 1번째 호출할 메소드가 있는 곳을 써줘 2번째 내가 호출해야 할 Method를 작성하고
		aq.id(R.id.button1).text("초기화").clicked(this, "setInit");

		// clicked 1번째 호출할 메소드가 있는 곳을 써줘 2번째 내가 호출해야 할 Method를 작성하고
		aq.id(R.id.button2).clicked(this, "doAdd");

		aq.id(R.id.button3).clicked(this, "setChangeImage");
		aq.id(R.id.button4).clicked(this, "getNetData");
	}
}
