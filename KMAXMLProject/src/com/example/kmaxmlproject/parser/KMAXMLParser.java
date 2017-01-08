package com.example.kmaxmlproject.parser;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.util.Log;

import com.example.kmaxmlproject.item.Weather;

public class KMAXMLParser {
	private static final String TAG = "Goni";

	public static Weather parse(InputStream is) {
		Weather weather = null;

		// parser를 만들기 위해 필요한 것
		SAXParserFactory sFactory = null;
		// 실제로 필요한건 아래 실제로 파싱하는 객채가 parser
		SAXParser parser = null;
		// handler에서 파싱을 해 ! 그래서 객체를 여러개를 만들어줘야 해
		KMAHandler handler = new KMAHandler();
		try {
			// 얘는 생성자가 없으므로 이렇게 생성해라.
			sFactory = SAXParserFactory.newInstance();
			// 자바 문법이다.
			parser = sFactory.newSAXParser();
			// xml을 자바객체로 파싱해주는 것
			// 1번째 InputStream (다른 것도 가능),
			// 2번째 DefaultHandler를 상속 받은 객체만 넣을 수 있다. (.java를 생성해)
			parser.parse(is, handler);

			weather = handler.getData();
			Log.v(TAG, "parser1 success : " + weather.list.size());
		} catch (Exception e) {
			Log.v(TAG, "parser1 error : " + e);
		}

		return weather;
	}
}
