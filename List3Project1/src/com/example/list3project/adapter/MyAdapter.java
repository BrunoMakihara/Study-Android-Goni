package com.example.list3project.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.list3project.R;
import com.example.list3project.model.MyItem;

public class MyAdapter extends BaseAdapter {
	private static final String TAG = "MianActivity";

	Context context;
	int layout;
	ArrayList<MyItem> data; // 아래 함수에서 쓰기 위해서 맴버변수를 접근 가능하게 하기 위해

	public MyAdapter(Context context, int layout, ArrayList<MyItem> data) {
		this.context = context;
		this.layout = layout;
		this.data = data;
		// 초기작업은 여기서 생성자는 1번만 돌아갈테니까.
	}

	@Override
	public int getCount() { // 화면에 전체로 출력 된다. 100개면 100개 1000개면 1000개
		// ArrayList 개수만큼 보여주면 된다

		return data.size();
	}

	@Override
	public Object getItem(int position) { // 현재 선택된 위치에서 반환 받을 위치
		// TODO Auto-generated method stub
		return data.get(position); // 반환타입이 Object니까 받는 쪽에서 형변환 해서 받아야 해
	}

	@Override
	public long getItemId(int position) { // 아이템을 구분을 하기 위한 것
		// TODO Auto-generated method stub
		return position;
	}

	// 여기까지 3가지 함수는 1번바께 호출 되지 않아요.

	class viewHolder { // 접근할 맴버변수를 여기다 둬
		// find가 시간이 오래 걸리기 때문에 ! 시간을 줄이기 위해
		ImageView img;
		TextView tvTitle;
		TextView tvPrice;
		Button btn;
	}

	@Override
	public View getView(int position, View cView, ViewGroup parent) { // 이대로 고쳐야
																		// 돼
		// getView라는 것은 행이 보여질 때마다 호출 된다.
		// 보여지는 것 까지만 리턴되고 만약 더 아래를 보일려고 하면 리턴 되서 그 때
		// 다시 만들어 진다. 즉 알고리즘을 잘못 짜면 멈춰있어
		// 다시 내려도 다시 보여지는 것 만큼 그때마다 리턴이 된다. 화면에 보이는 것만 저장하고 보여지지 않는건 저장 안함
		// cView는 처음에는 null이고, 아래로 내릴 경우 사라지는 놈을 아래로 넣어줘!!
		// data만 바뀌잖아 그러니까 inflate 횟수를 최소한으로 시키기 위해 cView가 null 일 때만
		// infalte 하면 돼! 그럼 성능 향상에 도움이 되겠지? 기존 있던 cView로 칸을 쓰고 거기에 data를 채우면 되잖아

		Log.v(TAG, "getView position :" + position);
		Log.v(TAG, "getView cView :" + cView);

		// if(cView == null) {
		// Log.v(TAG, "getView cView  == null posion" + position);
		// cView = View.inflate(context, layout, null); // 이거를 넘겨주면 돼
		// }
		// // View view = View.inflate(context, layout, null); // 이거를 넘겨주면 돼
		// MyItem item = data.get(position); // 데이터는 매번 바뀌어 있다.
		//
		// ImageView img = (ImageView)cView.findViewById(R.id.imageView1);
		// img.setImageResource(item.getImage());
		//
		// TextView tvTitle = (TextView)cView.findViewById(R.id.textView1);
		// tvTitle.setText(item.getTitle());
		//
		// if(position % 3 == 0) { // 스타일을 3의 배수 때만 바꿔라.
		// // 이러면 재활용이 안된다. 이렇게 되면 올리다 내리다 하면 다 빨간색으로 바뀌어 버려
		// // 쉽게 생각하면 cView가 재활용이 되기 때문에 문제가 되는거야!
		// tvTitle.setTextColor(Color.RED);
		// } else { // else를 이용해서 매번 Style을 검은색으로 바꿔줘야 해
		// tvTitle.setTextColor(Color.BLACK);
		// }
		//
		// TextView tvPrice = (TextView)cView.findViewById(R.id.textView2);
		// tvPrice.setText(item.getPrice() + ""); // int로 반환 되기 때문에 문자열로 바꿔줘

		/************************* ViewHolder로 묶고나서 하는 방법 ************************/
		final MyItem item = data.get(position); // 데이터는 매번 바뀌어 있다.
		// final 을 붙히면 로컬 변수여도 다른 메소드에서 참조 가능 해
		// final 을 붙히면 못 바꾸는데 즉 Myitem이 가르키는 주소는 못 바꾸지만
		// Myitem이 가르키는 놈의 Title, Price는 변경할 수 있다.

		viewHolder holder = null;
		if (cView == null) { // 여기는 플리킹 할 때 자주 되지 않아 즉 3~4번바께 실행이 안돼
			// 그러니까 속도가 좋아
			cView = View.inflate(context, layout, null); // 이거를 넘겨주면 돼
			holder = new viewHolder();
			holder.img = (ImageView) cView.findViewById(R.id.imageView1);
			holder.tvTitle = (TextView) cView.findViewById(R.id.textView1);
			holder.tvPrice = (TextView) cView.findViewById(R.id.textView2);
			holder.btn = (Button) cView.findViewById(R.id.button1); // 버튼이 1개기
																	// 때문에
			// onClickListner 만들어서 쓰면 맨 처음만 클릭 될꺼야 모든 클릭 되도

			cView.setTag(holder); // tag는 모든 View가 가지고 있어요!! 인자 값은 Object니까 아무거나
									// 넘길 수 있따
		} else {
			holder = (viewHolder) cView.getTag();
		}

		holder.img.setImageResource(item.getImage()); // 이미지를 바꿔줌
		holder.tvTitle.setText(item.getTitle()); // 타이틀 바꿔줌

		if (position % 3 == 0) { // 스타일을 3의 배수 때만 바꿔라.
			// 이러면 재활용이 안된다. 이렇게 되면 올리다 내리다 하면 다 빨간색으로 바뀌어 버려
			// 쉽게 생각하면 cView가 재활용이 되기 때문에 문제가 되는거야!
			holder.tvTitle.setTextColor(Color.RED);
		} else { // else를 이용해서 매번 Style을 검은색으로 바꿔줘야 해
			holder.tvTitle.setTextColor(Color.BLACK);
		}

		holder.tvPrice.setText(item.getPrice() + ""); // 가격 바꿔줌
		holder.btn.setOnClickListener(new View.OnClickListener() { // 이렇게 해야 매번
					// 객채를 새로 생성해서 그 객체 버튼에 연결해!!
					@Override
					public void onClick(View v) {
						// item.getTitle() 이대로 쓰면 로컬변수야 지금 위에보면
						// 메소드 안에 로컬변수기 때문에 다른 메소드에서 접근 할 수 없어.
						// 그걸 해결하려면 Final을 붙히면 접근 가능해져
						// context같은 경우는 맴버변수로 선언되어 있잖아 그래서 사용 가능해
//						Toast.makeText(context, item.getTitle(),
//								Toast.LENGTH_SHORT).show();
						Toast.makeText(context, item.getTitle(),
								Toast.LENGTH_SHORT).show();
					}
				});

		return cView;
		// cView로 바꿔야 돼 null을 View가 한줄을 보여주는 것인데 지금 null이라서 보여지지 않을 꺼야
	}

}
