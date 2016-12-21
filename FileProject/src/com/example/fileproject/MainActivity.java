package com.example.fileproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "Goni";
	int[] resId = { R.id.button1, R.id.button2, R.id.button3, R.id.button4,
			R.id.button5, R.id.button6 };

	View.OnClickListener bHandler = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button1:
				// 내장 메모리에 파일 쓰기
				doWriteFile("aaa.txt", "android1 목원 1234");
				doWriteFile("bbb.txt", "android2 목원 1234");
				doWriteFile("ccc.txt", "android3 목원 1234");
				doWriteFile("ddd.txt", "android4 목원 1234");
				doWriteFile("eee.txt", "android5 목원 1234");
				break;
			case R.id.button2:
				// 내장 메모리의 파일 읽기
				doReadFile("ccc.txt");
				break;
			case R.id.button3:
				// 내장 메모리의 파일 리스트 보기
				doFileList();
				break;
			case R.id.button4:
				// 내장 메모리의 파일 삭제
				doDeleteFile("ddd.txt");
				break;
			case R.id.button5:
				// 외장 메모리에 파일 쓰기
				// 1번째 Folder 2번째 Folder 3번째 File 4번째 Data
				doWriteSDCard("aaa", "text", "sample.txt", "출력데이터  Mokwon");
				break;
			case R.id.button6:
				// 외장 메모리의 파일 읽기
				doReadSDCard("aaa", "text", "sample.txt");
				break;
			}
		}
	};

	public void doReadSDCard(String dir1, String dir2, String fName) {
		String state = Environment.getExternalStorageState();

		// 장치가 연결되어있지 않으면 !!
		if (state.equals(Environment.MEDIA_UNMOUNTED)) {
			Log.v(TAG, "외장메모리가 준비되지 않았음");
			return;
		} // state if end

		File sdFile = Environment.getExternalStorageDirectory();
		Log.v(TAG, "절대경로 : " + sdFile.getAbsolutePath());
		String sdPath = sdFile.getAbsolutePath();
		sdPath += "/" + dir1 + "/" + dir2 + "/" + fName;
		Log.v(TAG, sdPath);
		File f = new File(sdPath);

		if (!f.exists()) {
			Log.v(TAG, "존재하지 않는 파일 입니다");
			return;
		}

		// Byte 단위로 읽는다.
		FileInputStream fis = null;
		// 줄단위로 읽는다.
		BufferedReader br = null;

		// Context가 가지고 있다 만약 Adapter에서 사용하려면 Context. 해서 사용해라
		// Exception이 발생하니 try catch로 묶어라
		try {
			/** 내장 메모리 읽는 부분과 지금만 다름 */
			fis = new FileInputStream(f);

			// BufferedReader 줄단위로 읽는다. // InputStreamReader 한글자씩 읽는다.
			br = new BufferedReader(new InputStreamReader(fis));

			// br.readLind() 한줄씩 읽기.
			String msg = br.readLine();
			Log.v(TAG, "file read data : " + msg);
		} catch (Exception e) {
			Log.v(TAG, "file read error : " + e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					Log.v(TAG, "close error : " + e);
				}
			}
		}
	}

	// 외장 메모리에 파일 쓰기 IO는 느리다 Thread를 사용해라
	// 1번째 Folder 2번째 Folder 3번째 File 4번째 Data
	public void doWriteSDCard(String dir1, String dir2, String fName, String msg) {
		String state = Environment.getExternalStorageState();

		// 장치가 연결되어있지 않으면 !!
		if (state.equals(Environment.MEDIA_UNMOUNTED)) {
			Log.v(TAG, "외장메모리가 준비되지 않았음");
			return;
		} // state if end

		// 절대 경로를 얻기
		File sdFile = Environment.getExternalStorageDirectory();
		Log.v(TAG, "절대경로 : " + sdFile.getAbsolutePath());

		// Folder를 생성 함 Folder 관리가 가능 함
		File f1 = new File(sdFile, dir1);
		// Folder가 있는 또 만들면 Error가 떨어지니까 있냐? 라고 물어보는 것
		if (!f1.exists()) {
			f1.mkdir();
		}

		// Folder를 생성 함 Folder 관리가 가능 함
		File f2 = new File(f1, dir2);
		// Folder가 있는 또 만들면 Error가 떨어지니까 있냐? 라고 물어보는 것
		if (!f2.exists()) {
			f2.mkdir();
		}

		// File을 생성 함
		File f3 = new File(f2, fName);

		FileOutputStream fos = null;
		PrintWriter pw = null;
		// Context가 가지고 있다 만약 Adapter에서 사용하려면
		// Context.FileOutputStream fos = openFileOutput(name, mode) 라고 쓰면 대
		// Exception이 발생하니 try catch로 묶어라
		try {
			/**
			 * 내장 메모리랑 다른 부분은 여기만 다르니까 여기만 수정하면 돼 퍼미션이 들어가 있어야 해!!
			 */
			fos = new FileOutputStream(f3);
			// 글자 단위로 쓰게 만들어준다.
			pw = new PrintWriter(fos);
			// 한줄씩 쓰기!
			pw.println(msg);
			// 자바는 느려서 모아서 쓰려고 해서 이걸 해주면 좋다?
			pw.flush();

			Log.v(TAG, "sdFile write success");
		} catch (Exception e) {
			Log.v(TAG, "sdFile write error : " + e);
		} finally {
			pw.close();
		}

	}

	// 내장 메모리 파일 삭제
	void doDeleteFile(String fName) {
		// 삭제 성공 True, 삭제 실패 False로 나온다.
		boolean flag = deleteFile(fName);
		Log.v(TAG, flag ? "파일 삭제 성공" : "파일 삭제 실패");
	}

	// 내장 메모리 파일 리스트 보기
	public void doFileList() {
		// Context가 가지고 있다.
		String[] fList = fileList();
		Log.v(TAG, "======================");
		if (fList != null) {

			for (String fName : fList) {
				Log.v(TAG, "fName List : " + fName);
			}
		} else {
			Log.v(TAG, "내장메모리에 파일이 없습니다.");
		} // fList if end!
		Log.v(TAG, "======================");
	}

	// 자바의 IO는 느리다. Thread를 사용해야 한다.
	// 내장 메모리에서 파일 읽기
	public void doReadFile(String fName) {
		// 할게 없으면 Void(대문자)로 쓰면 돼
		new AsyncTask<String, Void, Void>() {// 이건 Thread야 UI변경이 안돼! (Toast 같은
												// 것)
			protected Void doInBackground(String... params) {
				String name = params[0];
				// Byte 단위로 읽는다.
				FileInputStream fis = null;
				// 줄단위로 읽는다.
				BufferedReader br = null;

				// Context가 가지고 있다 만약 Adapter에서 사용하려면 Context. 해서 사용해라
				// Exception이 발생하니 try catch로 묶어라
				try {
					// File을 읽기위해 가장 기본작업
					fis = openFileInput(name);

					// BufferedReader 줄단위로 읽는다. // InputStreamReader 한글자씩 읽는다.
					br = new BufferedReader(new InputStreamReader(fis));

					// br.readLind() 한줄씩 읽기.
					String msg = br.readLine();
					Log.v(TAG, "file read data : " + msg);
				} catch (Exception e) {
					Log.v(TAG, "file read error : " + e);
				} finally {
					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {
							Log.v(TAG, "close error : " + e);
						}
					}
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				Toast.makeText(MainActivity.this, "읽기 완료", Toast.LENGTH_SHORT)
						.show();
			}
		}.execute(fName);
	}

	// 자바의 IO는 느리다. Thread를 사용해야 한다.
	// 내장 메모리에 파일 쓰기
	public void doWriteFile(String fName, String msg) {
		FileOutputStream fos = null;
		PrintWriter pw = null;
		// Context가 가지고 있다 만약 Adapter에서 사용하려면
		// Context.FileOutputStream fos = openFileOutput(name, mode) 라고 쓰면 대
		// Exception이 발생하니 try catch로 묶어라
		try {
			// 내장메모리에 쓰는 것을 Return 해준다. File을 쓰기 위한 가장 기본 작업
			fos = openFileOutput(fName, MODE_PRIVATE);

			// 글자 단위로 쓰게 만들어준다.
			pw = new PrintWriter(fos);
			// 한줄씩 쓰기!
			pw.println(msg);
			// 자바는 느려서 모아서 쓰려고 해서 이걸 해주면 좋다?
			pw.flush();

			Log.v(TAG, "file write success");
		} catch (Exception e) {
			Log.v(TAG, "file write error : " + e);
		} finally {
			pw.close();
		}
	}

	void setUIInit() {
		for (int id : resId) {
			findViewById(id).setOnClickListener(bHandler);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setUIInit();

		// 1번째 이름 ( 프로젝트 이름을 많이 쓴다. ), 2번째 MODE_PRIVATE 가 0임
		SharedPreferences sp = getSharedPreferences("FileProject", MODE_PRIVATE);
		boolean flag = sp.getBoolean("first", true);
		if (flag) {
			doFirst();
		}
	}

	// 딱 1번만 실행되야 해!
	public void doFirst() {
		Log.v(TAG, "한번만 실행됨");
		SharedPreferences sp = getSharedPreferences("FileProject", MODE_PRIVATE);
		// Editor가 추가/수정/삭제하는 얘임
		SharedPreferences.Editor editor = sp.edit();
		editor.putBoolean("first", false);
		editor.commit();
	}
}
