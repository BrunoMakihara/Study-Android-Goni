package com.example.downimageproject;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class NetManager {
	static HttpClient httpclient = null;

	public static HttpClient getHttpClient() {
		// if(httpclient == null){
		// timeout 10초 설정
		HttpParams hp = new BasicHttpParams();
		// 접속시도 30초가 지난 후 익셉션을 던지는데
		// 이거는 10초에 던져주게 해
		HttpConnectionParams.setConnectionTimeout(hp, 10000);
		// 10초 이상동안 아무것도 아무것도 안왔다갔다 하면
		// Exception을 던진다.
		HttpConnectionParams.setSoTimeout(hp, 10000);

		httpclient = new DefaultHttpClient(hp);

		// }
		return httpclient;
	}

	public static HttpGet getGet(String url) {
		if (httpclient == null) {
			httpclient = getHttpClient();
		}
		HttpGet httpGet = new HttpGet(url);
		// 접속을 유지 하고 싶어요 ~
		httpGet.setHeader("Connection", "Keep-Alive");
		// 내가 접속할 때는 어떠한 방식으로 접속할꺼에요~
		httpGet.setHeader("Accept", "application/xml");
		// 리턴 타입
		httpGet.setHeader("Content-Type", "application/xml");
		// 난 누구에요 ~ 크롬브라우저로 익스프를러로 들어온건지 사파리로 들어온지 알 수 있게 한다. PC의 브라우저를 구분하는 것인데
		// 강제로 android를 넣어 버린거다.!
		httpGet.setHeader("User-Agent", "ANDROID");
		httpGet.setHeader("Pragma", "no-cache");
		httpGet.setHeader("Cache-Control", "no-cache,must-reval!idate");
		httpGet.setHeader("Expires", "0");
		return httpGet;
	}

	public static HttpPost getPost1(String url) {
		if (httpclient == null) {
			httpclient = getHttpClient();
		}
		HttpPost post = new HttpPost(url);
		post.setHeader("Connection", "Keep-Alive");
		post.setHeader("Accept", "application/xml");
		post.setHeader("Content-Type", "application/xml");
		post.setHeader("User-Agent", "ANDROID");
		return post;
	}

	public static HttpPost getPost(String url) {
		if (httpclient == null) {
			httpclient = getHttpClient();
		}
		HttpPost post = new HttpPost(url);
		post.setHeader("User-Agent", "ANDROID");
		return post;
	}
}
