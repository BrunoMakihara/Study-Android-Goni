package com.example.kmaxmlproject.parser;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.kmaxmlproject.item.Local;
import com.example.kmaxmlproject.item.Weather;

public class KMAHandler extends DefaultHandler {
	// 전부다 Super 지워 하는 일 없어
	// 여기서 객체를 다 구분해 줘서 리턴해줘야해 말그대로 모든 데이터를 처음으로 정리해주는 곳이야!

	// null 포인터를 막기 위해 생성을 함
	private ArrayList<Local> data = null;
	Weather weather = null;
	Local local = null;

	// Tag Name으로 하나 만들어줘 characters Method에서는 localName이 없기 떄문에
	String tName;

	// 게터
	public Weather getData() {
		return weather;
	}

	// 세터
	public void setData(Weather weather) {
		this.weather = weather;
	}

	@Override
	// 엘리먼트가 시작되면 실행된다.
	// Attributes attributes는 여는 쪽에만 있기 때문에 End에는 없다.
	// localName -> 엘리먼트 이름이 들어와
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (localName.equals("current")) {
			// 여기서 생성을 시켜주면 좋다. 가장 큰 ArrayList를
			data = new ArrayList<Local>();
		} else if (localName.equals("weather")) {
			weather = new Weather();
			// 어트리뷰트를 받아 옴
			weather.year = attributes.getValue("year");
			weather.month = attributes.getValue("month");
			weather.day = attributes.getValue("day");
			weather.hour = attributes.getValue("hour");
			// data = weather.list;
		} else if (localName.equals("local")) {
			local = new Local();
			local.stn_id = attributes.getValue("stn_id");
			local.icon = attributes.getValue("icon");
			local.desc = attributes.getValue("desc");
			local.ta = attributes.getValue("ta");
			local.rn_hr1 = attributes.getValue("rn_hr1");
			local.sd_day = attributes.getValue("sd_day");
			// <local stn_id="115" icon="11" desc="눈"
			// ta="0.7" rn_hr1="0.1" sd_day="0.1">울릉도</local> 공백
		}

		tName = localName;
	}

	@Override
	// 엘리먼트가 종료되면 실행된다.
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (localName.equals("local")) {
			weather.list.add(local);
			// data.add(local);
		}

		// 여기서 엘리먼트가 끝나니까 공백을 무시하기 위해서
		// 공백을 읽으면 characters가 호출 되지만 tName이 ""이므로 아무작업을 하지 않을 것
		tName = "";

	}

	@Override
	// 문자를 읽으면 알아서 호출된다.
	// 공백을 읽으면 여기로 들어온다.
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String s = new String(ch, start, length);
		// 어디에 넣을 것인가?
		if (tName.equals("local")) {
			local.name = s;
		}
	}
}
