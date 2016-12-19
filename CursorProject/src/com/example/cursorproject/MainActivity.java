package com.example.cursorproject;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.example.cursorproject.adapter.MemberAdapter;
import com.example.cursorproject.helper.MemberHelper;
import com.example.cursorproject.item.AMember;

public class MainActivity extends Activity {
	private static final String TAG = "Goni";

	// adapter = simpleCursorAdapter, data = cursor, xml = android.R, view =
	// list
	ListView list;

	// support.v4사용하지 마라! 1번째
	// SimpleCursorAdapter adapter = null;
	// 2번째
	// MemberCursorAdapter adapter = null;
	// 3번째
	MemberAdapter adapter = null;
	ArrayList<AMember> data = new ArrayList<AMember>();

	// 아래 3개는 SQLite 사용하면 몰려다니는 애들임
	Cursor c;
	MemberHelper helper;
	SQLiteDatabase db;

	// db에 접근하는 것
	class JobThread extends Thread {
		public void run() {
			// 1번째 Context 2번째 database명 ( Table 명 아니다!! )
			// 3번째 null 4번째 version명
			helper = new MemberHelper(MainActivity.this, "mokwon.db", null, 1);
			db = helper.getReadableDatabase();

			c = db.query("amember", null, null, null, null, null, null);

			AMember object = null;

			// data를 클리어 해야 한다.
			data.clear();

			while (c.moveToNext()) {
				object = new AMember();
				object._id = c.getInt(0);
				object.fname = c.getString(1);
				object.lname = c.getString(2);
				object.tel = c.getString(3);
				object.address = c.getString(4);
				object.age = c.getInt(5);
				object.bigo = c.getInt(6);

				data.add(object);
			}

			c.close();
			db.close();

			// 변경이 됐다고 알려줘야 함 UI변경( 그런데 Thread에서는 UI변경이 안돼)
			// adapter.notifyDataSetChanged();

			// 그래서 아래와 같은 방법 Thread에서도 UI를 변경할 수 있게 만들어 줌
			// 안드로이드 문서에서는 Handler를 사용하는 것 보단 이 것이 더 빠르다.
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					adapter.notifyDataSetChanged();
				}
			});
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		list = (ListView) findViewById(R.id.listView1);

		/** 3번째를 Thread로 다 빼서 할 작업은 */
		new JobThread().start();
		adapter = new MemberAdapter(this, R.layout.item1, data);

		/** 3번째 방법 가장 좋고 다 Thread로 옮겼어! */
		// 1번째 Context 2번째 database명 ( Table 명 아니다!! )
		// 3번째 null 4번째 version명
		// helper = new MemberHelper(this, "mokwon.db", null, 1);
		// db = helper.getReadableDatabase();
		//
		// c = db.query("amember", null, null, null, null, null, null);
		// adapter = new MemberAdapter(this, R.layout.item1, data);

		// startManagingCursor(c);

		/** 2번 방법 */
		// Cursor가 ArrayList역할을 한다.
		// c = db.query("amember", null, null, null, null, null, null);

		// close 하면 안돼!
		// adapter = new MemberCursorAdapter(this, c, R.layout.item1);
		// 커서를 닫으면 출력이 안돼 계속 열어놔야 한다.
		// c.close();
		// 그래서 아래를 써서 다 사용하면 닫아주세요. 라고 요청하는 것
		// startManagingCursor(c);

		/** 1번방법 */
		// 1번째 Context 2번째 한줄에 보여줄 XML 3번째 커서 4번쨰 new String[]{}배열! 5번째 new
		// int[]{} 배열!
		// 4번째가 1개면 5번째도 1개여야하고 2개면 2개 앞에와 뒤의 개수는 같아야 한다.
		// adapter = new SimpleCursorAdapter(this, R.layout.item, c, new
		// String[] {
		// "fname", "address", "bigo" }, new int[] { R.id.textView1,
		// R.id.textView2, R.id.textView3 });
		// 커서를 닫으면 출력이 안돼 계속 열어놔야 한다.
		// c.close();
		// 그래서 아래를 써서 다 사용하면 닫아주세요. 라고 요청하는 것
		// startManagingCursor(c);

		// list에 연결된 adpater는 얘다
		list.setAdapter(adapter);
	}
}
