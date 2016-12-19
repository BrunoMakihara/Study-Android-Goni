package com.example.cursorproject.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cursorproject.R;
import com.example.cursorproject.item.AMember;

public class MemberAdapter extends BaseAdapter {

	Context context;
	int res;
	ArrayList<AMember> data;

	int[] imgRes = { R.drawable.icon01, R.drawable.icon02, R.drawable.icon03 };

	// Context와 한줄에 보여줄 XML과 ArrayList를 받아야 한다.
	public MemberAdapter(Context context, int res, ArrayList<AMember> data) {
		this.context = context;
		this.res = res;
		this.data = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	class ViewHolder {
		TextView tv1, tv2;
		ImageView iv1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			// 1번째 Context, 2번째 resource, 3번째는 null
			convertView = View.inflate(context, res, null);
			holder = new ViewHolder();
			holder.tv1 = (TextView) convertView.findViewById(R.id.textView1);
			holder.tv2 = (TextView) convertView.findViewById(R.id.textView2);
			holder.iv1 = (ImageView) convertView.findViewById(R.id.imageView1);

			// 재활용할 때 사용하려고 TAG를 붙힌다.
			convertView.setTag(holder);
		} else {
			// 위에서 등록한 TAG로 뽑아온다.
			holder = (ViewHolder) convertView.getTag();
		} // convertView == null if end

		final AMember member = data.get(position);
		holder.tv1.setText(member.fname + " " + member.lname);
		holder.tv2.setText(member.address);
		holder.iv1.setImageResource(imgRes[member.bigo]);

		return convertView;
	}

}
