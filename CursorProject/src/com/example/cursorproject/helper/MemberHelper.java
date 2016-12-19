package com.example.cursorproject.helper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MemberHelper extends SQLiteOpenHelper {
	private static final String TAG = "Goni";

	// 첫번째 생성자를 생성한다. 1번째 Context
	// 2번째 DB 네임 3번째 아직 지원 안되 null 해라. 4번째 version 처음에는 1 2 3 4 이런식으로
	// 버전이 바뀔 경우 자동적으로 호출되는 것은 onUpgrade()다.
	public MemberHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);

	}

	// 어플을 깔고나면 평생에 한번만 실행된다. 여기다 테이블 생성을 넣어두면 한번만 생성된다.
	public void onCreate(SQLiteDatabase db) {
		Log.v(TAG, "db onCreate");

		// Columns는 _id 부터 0번째 fanme 은 1번째 등등 증가한다!
		String sql = "CREATE TABLE amember("
				+ "_id integer primary key autoincrement,"
				+ "fname text not null," + "lname text," + "tel text,"
				+ "address text," + "age integer," + "bigo integer);";

		Log.v(TAG, "sql : " + sql);

		try {
			// 명령문을 실행시키는 것
			// 이건 SQLException이 발생 함
			db.execSQL(sql);
		} catch (SQLException e) {
			Log.v(TAG, "create table error : " + e);
			return;
		}

		try {
			db.execSQL("insert into amember(fname, lname, tel, address, age, bigo) values('kim', 'Sung-Gon', '010-1111-1111', '서울', 11, 0);");
			db.execSQL("insert into amember(fname, lname, tel, address, age, bigo) values('sim', 'ff', '010-1111-1111', '대전', 22, 0);");
			db.execSQL("insert into amember(fname, lname, tel, address, age, bigo) values('hong', 'ee', '010-1111-1111', '대구', 33, 0);");
			db.execSQL("insert into amember(fname, lname, tel, address, age, bigo) values('gak', 'dd', '010-1111-1111', '부산', 44, 0);");
			db.execSQL("insert into amember(fname, lname, tel, address, age, bigo) values('lee', 'cc', '010-1111-1111', '찍고', 55, 0);");
			db.execSQL("insert into amember(fname, lname, tel, address, age, bigo) values('chang', 'bb', '010-1111-1111', '아하', 66, 0);");
			db.execSQL("insert into amember(fname, lname, tel, address, age, bigo) values('dong', 'aa', '010-1111-1111', '미국', 77, 0);");

			Log.v(TAG, "초기화 데이터 입력 성공");
		} catch (SQLException e) {
			Log.v(TAG, "insert error : " + e);
		}

	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		Log.v(TAG, "db onOpen");
		super.onOpen(db);

	}

	@Override
	// 인자에
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.v(TAG, "db onUpgrade oldVersion : " + oldVersion);
		Log.v(TAG, "db onUpgrade newVersion : " + newVersion);

	}

}
