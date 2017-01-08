package com.example.kmaxmlproject;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.kmaxmlproject.adapter.KMAAdapter;
import com.example.kmaxmlproject.item.Local;
import com.example.kmaxmlproject.item.Weather;
import com.example.kmaxmlproject.parser.KMAXMLParser;

public class MainActivity extends Activity {
	private static final String TAG = "Goni";

	EditText et1;

	// ListView를 위함
	ListView list;
	ArrayList<Local> data = new ArrayList<Local>();
	KMAAdapter adapter = null;
	Weather weather;

	static String KMA_URL = "http://www.kma.go.kr/XML/weather/sfc_web_map.xml";

	View.OnClickListener bHandler = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			doKMAXMLParsing();
		}
	};

	public void doKMAXMLParsing() {
		new JobTask().execute();
	}

	class JobTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// 네트워크 접속하는 객체
			HttpClient client = null;
			HttpGet request = null;
			HttpResponse response = null;
			int code = 0;

			InputStream is = null;

			try {
				client = NetManager.getHttpClient();
				request = NetManager.getGet(KMA_URL);
				// 실제로 접속이 이루어짐
				response = client.execute(request);
				// 응답 코드를 뽑아서
				code = response.getStatusLine().getStatusCode();
				switch (code) {
				// 정상코드 일 때
				case 200:
					// 입력용 객체를 뽑아서
					is = response.getEntity().getContent();

					// 읽어드리는 객체를 넘겨서 Parser에서 Parsing 하고나서 받아오면 됨
					weather = KMAXMLParser.parse(is);
					data = weather.list;
					// 이게 없으면 adapter가 가르키는 애가 다르기 때문에 ListView에 나오지 않을 것
					adapter.setData(data);
					
					for (Local i : data) {
						Log.v(TAG, i.toString());
					}

					break;
				}
			} catch (Exception e) {

			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// Super 지워 하는 일 없어
			et1.setText(weather.toString());
			adapter.notifyDataSetChanged();
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et1 = (EditText) findViewById(R.id.editText1);
		list = (ListView) findViewById(R.id.listView1);

		findViewById(R.id.button1).setOnClickListener(bHandler);

		adapter = new KMAAdapter(this, R.layout.item, data);

		list.setAdapter(adapter);
	}
}
