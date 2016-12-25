package com.example.fragmentproject2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragment2 extends Fragment {

	Activity activity;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = activity;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 3번째 인자를 추가시켜서 false를 줘
		View view = inflater.inflate(R.layout.frag2, container, false);

		return view;
	}

	@Override
	public void onDetach() {
		// Fragment와 Activity의 연결을 끊어줘라.
		if (activity != null) {
			activity = null;
		}
		super.onDetach();
	}

}
