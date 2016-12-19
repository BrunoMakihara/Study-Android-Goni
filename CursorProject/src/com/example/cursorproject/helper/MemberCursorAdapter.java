package com.example.cursorproject.helper;

import com.example.cursorproject.R;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MemberCursorAdapter extends CursorAdapter {

	Context context;
	int layout;
	Cursor c;

	int[] imgRes = { R.drawable.icon01, R.drawable.icon02, R.drawable.icon03 };

	public MemberCursorAdapter(Context context, Cursor c, int Layout) {
		super(context, c);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.layout = Layout;
		this.c = c;
	}

	/** getView를 쪼개놓음 */
	@Override
	// View만 만들어준다.
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
		return View.inflate(context, layout, null);
	}

	@Override
	// View에다가 Data 채워넣기 1번째 view가 위에서 newView가 리턴한 View임!
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		TextView tv1 = (TextView) view.findViewById(R.id.textView1);
		// move하지마 알아서 움직임
		tv1.setText(cursor.getString(1) + " " + cursor.getString(2));

		TextView tv2 = (TextView) view.findViewById(R.id.textView2);
		tv2.setText(cursor.getString(4));

		int imgIdx = cursor.getInt(6);
		ImageView img = (ImageView) view.findViewById(R.id.imageView1);
		img.setImageResource(imgRes[imgIdx]);

	}

}
