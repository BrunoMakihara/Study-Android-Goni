package com.example.aqueryproject;

import com.androidquery.AQuery;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragment extends Fragment {

	Activity activity;
	AQuery aq = null;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag1, container, false);

		// context를 주고 생성자의 그 View를 준다.
		aq = new AQuery(activity, view);

		return view;
	}

	@Override
	public void onDetach() {
		if (activity != null) {
			activity = null;
		}
		super.onDetach();
	}

}
