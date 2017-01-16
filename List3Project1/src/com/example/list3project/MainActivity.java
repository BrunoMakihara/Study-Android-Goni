package com.example.list3project;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import com.example.list3project.adapter.MyAdapter;
import com.example.list3project.model.MyItem;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";

//	ListView list1; // 3가지가 필요해요. 일단 출력해줄 View, 2번째로 실제로 관리 될 Data
	GridView list1;
	ArrayList<MyItem> data = new ArrayList<MyItem>(); // 이렇게 해주면 null 안떠 data를
														// 안집어 넣어서
	MyItem item = null; // 이것도 자주 쓰여 여기 4개가 세트임 !!
	MyAdapter adapter = null;

	// baseAdapter를 상속받아서 내가 원하는 Adapter로 바꿔서 사용해줘야해

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		list1 = (GridView) findViewById(R.id.gridView1);

		adapter = new MyAdapter(this, R.layout.item, data); // context, 한줄에 보여줘야
															// 할 xml, 다룰 실 data
		
		item = new MyItem();			// 이게 원래 맞는 방법이다 !!
		item.setImage(R.drawable.icon01);
		item.setTitle("상품1");
		item.setPrice(3000);
		data.add(item);		// ArrayList에 추가 함
		
		item = new MyItem(R.drawable.icon02, "목원", 4000);		// 이렇게 쓰면 set안써 Myitem 가서 생성자 추가해
		data.add(item);		// ArrayList에 추가 함
		
		data.add(new MyItem(R.drawable.icon03, "대전", 1000));		// 만들면서 바로 만들어라!! 
		data.add(new MyItem(R.drawable.icon04, "서울", 2000));		// 만들면서 바로 만들어라!! 
		data.add(new MyItem(R.drawable.icon05, "대구", 3000));		// 만들면서 바로 만들어라!! 
		data.add(new MyItem(R.drawable.icon06, "김천", 4000));		// 만들면서 바로 만들어라!! 
		data.add(new MyItem(R.drawable.icon05, "영동", 5000));		// 만들면서 바로 만들어라!! 
		data.add(new MyItem(R.drawable.icon04, "분당", 6000));		// 만들면서 바로 만들어라!! 
		data.add(new MyItem(R.drawable.icon03, "광주", 7000));		// 만들면서 바로 만들어라!! 
		data.add(new MyItem(R.drawable.icon02, "강릉", 8000));		// 만들면서 바로 만들어라!! 
		data.add(new MyItem(R.drawable.icon01, "대천", 9000));		// 만들면서 바로 만들어라!! 
		data.add(new MyItem(R.drawable.icon02, "부산", 10000));		// 만들면서 바로 만들어라!! 
		data.add(new MyItem(R.drawable.icon03, "마산", 11000));		// 만들면서 바로 만들어라!! 

		adapter = new MyAdapter(this, R.layout.item, data);
		
		list1.setAdapter(adapter);
	}
}
