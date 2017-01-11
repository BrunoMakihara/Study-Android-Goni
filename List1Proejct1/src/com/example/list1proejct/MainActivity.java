package com.example.list1proejct;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) { // 최초에 한번만 Null 하려고 하는 것
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	ListView list; // 실 데이터를 담을 곳 1가지 필수요소
	ArrayList<String> data = new ArrayList<String>(); // 동적배열이야 ! 추가할수록 늘어나
	// 2가지 필수요소 실 데이터
	ArrayAdapter<String> adapter = null; // 필수요소 2가지를 관리하기 위한 Adaper 꼭 필요해!

	public class PlaceholderFragment extends Fragment { // 최초 실행 시 이거를 붙혀
		// Main Activity에서만 사용할 수 있어
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);

			list = (ListView) rootView.findViewById(R.id.listView1);
			data.add("android1");
			data.add("android2");
			data.add("android3");
			data.add("android4");
			data.add("android5");
			data.add("android6");
			data.add("android7");
			data.add("android8");
			data.add("android9");
			data.add("android10");

			adapter = new ArrayAdapter<String>(MainActivity.this,
					android.R.layout.simple_list_item_multiple_choice, data);
			// simple_list_item_multiple_choice << 이거는 여러개 눌리는 체크박스!?
			// simple_list_item_single_choice << 이거는 오른쪽에 라디오 버튼이 출력 돼
			// 필수요소 3가지가 필요해 !! 1번째 context, 2번째 한줄에 보여져야 할 Layout 모양!! 3번째 실
			// Data가 필요해!!

			// adapter = new ArrayAdapter<String>(MainActivity.this,
			// android.R.layout.simple_list_item_1, data);
			// // 필수요소 3가지가 필요해 !! 1번째 context, 2번째 한줄에 보여져야 할 Layout 모양!! 3번째 실
			// // Data가 필요해!!

			list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
			// 위가 체크박스일 경우 CHOICE_MODE_MULTIPLE 이걸 해줘야 해
			// 이걸 추가해야 라디오버튼이 눌려용~
			list.setAdapter(adapter);

			return rootView;
		}
	}

}
