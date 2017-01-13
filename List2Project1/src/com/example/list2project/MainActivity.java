package com.example.list2project;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	EditText et1;

	ListView list1; // 리스트 뷰를 사용하기 위한 3가지 임 실 data, data를 관리할 툴, 보여줄 툴
	ArrayList<String> data = new ArrayList<String>();
	ArrayAdapter<String> adapter = null;

	View.OnClickListener bHandler = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button1:
				doAddData();
				break;
			case R.id.button2:
				doRemoveData();
				break;
			}
		}
	};

	AdapterView.OnItemClickListener iHandler = new AdapterView.OnItemClickListener() {
		// 아이템 클릭 이벤트 처리 ~

		@Override
		public void onItemClick(AdapterView<?> adapter, View parent,
				int position, long id) {
			// 3번째는 positon은 위치를 알려줘

			String text = data.get(position);
			// data는 adapter가 아니고 data에 있다.
			Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();

		}

	};

	void doAddData() { // ListView에 Data를 추가하기 위함 (버튼 1번의 동작)
		// 로직!
		String str = et1.getText().toString();
		data.add(str);

		// 화면 변화
		adapter.notifyDataSetChanged(); // UI 변경 명령어야 까먹으면 안 돼!!!
		// Thread는 UI 변경이 안되므로 이것은 Thread에서 사용하면 안됌ㅋ
	}

	void doRemoveData() {
		int idx = list1.getCheckedItemPosition();
		if (idx != ListView.INVALID_POSITION) { // INVALID_POSITION의 값은 -1 이다.
			data.remove(idx);
			list1.clearChoices(); // 체크를 다 해제 시켜준다.
			// Remove는 위 Data가 삭제 될 경우 위로 당겨진다.
			// 실제data가 3개인데 3번째에 Check 가 되어있을 때 2번 Remove를 했다.
			// 그럼 체크는 3번을 가르키는데 data는 없으니까 프로그램이 죽어버린다.

			adapter.notifyDataSetChanged(); // UI 변경 명령어야 까먹으면 안 돼!!!
			// Thread는 UI 변경이 안되므로 이것은 Thread에서 사용하면 안됌ㅋ
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et1 = (EditText) findViewById(R.id.editText1);
		list1 = (ListView) findViewById(R.id.listView1);

		adapter = new ArrayAdapter<String>(MainActivity.this,
				android.R.layout.simple_list_item_single_choice, data);
		// 1번째는 Context, 2번째는 한줄에 보여줘야 할 XML, 3번째는 실제 data가 필요하다.

		list1.setAdapter(adapter);
		list1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		list1.setOnItemClickListener(iHandler); // 이벤트 처리 !!

		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);

	}
}
