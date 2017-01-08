package com.example.kmaxmlproject.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.kmaxmlproject.R;
import com.example.kmaxmlproject.item.Local;

public class KMAAdapter extends BaseAdapter {

	Context context;
	int layout;
	ArrayList<Local> data;

	public ArrayList<Local> getData() {
		return data;
	}

	public void setData(ArrayList<Local> data) {
		this.data = data;
	}

	public KMAAdapter(Context context, int layout, ArrayList<Local> data) {
		this.context = context;
		this.layout = layout;
		this.data = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		// ArrayList에 개수만큼
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		// 하나씩 넘겨주는 것이 가장 좋다.
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub

		return position;
	}

	class ViewHolder {
		TextView tv1, tv2, tv3;
	}

	@Override
	public View getView(int position, View cView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (cView == null) {
			cView = View.inflate(context, R.layout.item, null);
			holder = new ViewHolder();
			holder.tv1 = (TextView) cView.findViewById(R.id.textView1);
			holder.tv2 = (TextView) cView.findViewById(R.id.textView2);
			holder.tv3 = (TextView) cView.findViewById(R.id.textView3);
			cView.setTag(holder);
		} else {
			holder = (ViewHolder) cView.getTag();
		}
		final Local local = data.get(position);
		holder.tv1.setText(local.name);
		holder.tv2.setText(local.ta);
		holder.tv3.setText(local.desc);

		return cView;
	}

}
