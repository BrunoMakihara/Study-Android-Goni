package com.example.melonjsonproject;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.melonjsonproject.item.Melon;
import com.example.melonjsonproject.parser.MelonJSONParser;

public class MainActivity extends Activity {
	private static final String TAG = "Goni";
	AQuery aq = null;
	Melon melon;

	public void getJSON() {

		String url = "http://apis.skplanetx.com/melon/charts/realtime?count=10&page=1&version=1&appKey=31c1e579-b9c2-3697-95d3-ed1ba8a82815&format=json";

		aq.ajax(url, JSONObject.class, new AjaxCallback<JSONObject>() {

			@Override
			public void callback(String url, JSONObject object,
					AjaxStatus status) {

				melon = MelonJSONParser.parse(object);
				Log.v(TAG, "melon : " + melon.toString());
			}

		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		aq = new AQuery(this);

		aq.id(R.id.button1).clicked(this, "getJSON");

	}
}
