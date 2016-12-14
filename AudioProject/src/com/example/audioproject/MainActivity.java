package com.example.audioproject;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
	private static final String TAG = "Goni";

	MediaPlayer player = null;
	int duration = 0;

	MediaPlayer.OnSeekCompleteListener sListener = new MediaPlayer.OnSeekCompleteListener() {

		@Override
		public void onSeekComplete(MediaPlayer mp) {

			try {
				player.start();
			} catch (IllegalStateException e) {
				Log.v(TAG, "seek play error : " + e);
			}
		}
	};

	MediaPlayer.OnPreparedListener pListener = new MediaPlayer.OnPreparedListener() {

		@Override
		public void onPrepared(MediaPlayer mp) {

			// Exception 발생
			try {
				player.start();
			} catch (IllegalStateException e) {
				Log.v(TAG, "Internet play error : " + e);
			}
		}
	};

	View.OnClickListener bHandler = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button1:
				doPlayRaw(R.raw.dingdong);
				break;
			case R.id.button2:
				doPlaySDCard("music.mp3");
				break;
			case R.id.button3:
				doPlayURL("http://sites.google.com/site/ubiaccessmobile/sample_audio.amr");
				break;
			case R.id.button4:
				doPause();
				break;
			case R.id.button5:
				doReStart();
				break;
			}
		}
	};

	public void doReStart() {
		if (player != null) {
			player.setOnSeekCompleteListener(sListener);
			// 앞으로 좀 옮길려고 ( 동영상 재생 )
			if (duration >= 2000) {
				duration -= 2000;
			}
			// 현재를 그 위치로 옮겨주세요.
			player.seekTo(duration);
			player.start();
		}
	}

	public void doPause() {
		if (player != null) {
			if (player.isPlaying()) {
				// 현재 재생되는 위치를 가져온다.
				duration = player.getDuration();
				// player를 멈춘다.
				player.pause();
			} // player.isPlaying() end if
		}
	}

	void initPlayer() {
		if (player == null) {
			player = new MediaPlayer();

			player.setWakeMode(this, PowerManager.PARTIAL_WAKE_LOCK);
		} else {
			if (player.isPlaying()) {
				// 노래를 중지 시키고
				player.stop();
			}
			// 메모리를 초기상태로 돌아간다.
			player.reset();
			// 완벽하게 끝내게 가비지 콜렉터 ( 메모리 아웃 시키기 )
			// 안드로이드에서 멀티미디어의 메모리를 관리를 잘 못하기 때문에 우리가 직접 관리를 해줘야 한다.
			System.gc();
		}
	}

	// 인터넷에서 노래 듣기
	public void doPlayURL(String url) {
		Uri uri = Uri.parse(url);

		// 초기화
		initPlayer();

		// Exception을 발생시킨다. IOException으로 잡혀
		try {
			// 1번째 Context, Uri로 입력하면 된다.
			player.setDataSource(this, uri);

			// onPreparedListener에 등록
			player.setOnPreparedListener(pListener);

			// 로딩만 되면 바로 실행시켜
			// 준비 안되서 바로 실행시키면 안돼!
			// 그래서 onPreparedListener 에서 실행시킨다.
			player.prepareAsync();

			// 음원을 실행할 준비가 되어있다.
			// 재생할 만큼의 Data를 준비하고 아래로 떨어진다. 그래서 인터넷 같은 경우 느려서
			// 버튼이 돌아오지 않는다.
			// player.prepare();
			// 스타트!
			player.start();
		} catch (IOException e) {
			Log.v(TAG, "play error : " + e);
		}

	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	// 현재 Activity에서만 재생되게 하려고 하면 사용
	protected void onStop() {
		if (player != null) {
			// 노래가 재생 중이면 종료해라
			if (player.isPlaying()) {
				// 노래를 중지 시키고
				player.stop();
			}
			// 메모리 아웃을 시킨다.
			player.release();
			// 이변수를 접근하지 않게 하겠습니다.
			player = null;
			// 완벽하게 끝내게 가비지 콜렉터 ( 메모리 아웃 시키기 )
			System.gc();
		}
		super.onStop();
	}

	// SDCard에서 노래 재생
	// 경로를 찾아가서 미디어 Data를 재생하는 것
	public void doPlaySDCard(String fName) {
		initPlayer();

		// 외장 메모리의 경로를 가져오기
		File sdFile = Environment.getExternalStorageDirectory();
		File f = new File(sdFile, fName);
		// 경로를 찾아오기
		String path = f.getAbsolutePath();
		Log.v(TAG, "Path : " + path);

		// 이것도 Intent에서 불러오는 것 처럼 해야해 ( OnPrepared를 사용 )
		// Exception을 발생시킨다. IOException으로 잡혀
		try {
			// 내부적으로 Thread가 있어서 Activity가 꺼도 나온다.
			// DataSource에 의해 경로를 연결! 아직 음원을 실행할 준비가 안돼있다.
			player.setDataSource(path);
			// 음원을 실행할 준비가 되어있다.
			player.prepare();
			// 스타트!
			player.start();
		} catch (IOException e) {
			Log.v(TAG, "play error : " + e);
		}
	}

	public void doPlayRaw(int resId) {
		// 음원을 재생할 때 쓰는 것
		// create 는 만들고 DataSource까지 연결하는 것 한꺼번에 함 R.java에 있는 효과음만 사용
		MediaPlayer player = MediaPlayer.create(this, resId);

		player.start();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);
		findViewById(R.id.button3).setOnClickListener(bHandler);
		findViewById(R.id.button4).setOnClickListener(bHandler);
		findViewById(R.id.button5).setOnClickListener(bHandler);
	}

}
