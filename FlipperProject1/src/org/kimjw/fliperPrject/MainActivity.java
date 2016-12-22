package org.kimjw.fliperPrject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {

	public static final String TAG = "MainActivity";
	private ViewFlipper m_viewFlipper;/*뷰플리퍼의 외부변수*/
	private int m_nPreTouchPosX = 0;

	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);

		//메인에서 전달받은 인텐트.
		Intent intent = this.getIntent();
		intent.putExtra("SOMETHING", "EXTRAS");

		m_viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);
		m_viewFlipper.setOnTouchListener(myTouchListener);

		
		Toast.makeText(MainActivity.this, "Please, flip pages from right to left", Toast.LENGTH_LONG).show();
	}

	private void MoveNextView()/*화면을 다음장으로 넘기는 메소드*/
	{
		m_viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.appear_from_right));/*오른쪽에서 들어와서*/
		m_viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.disappear_to_left));/*왼쪽으로 사라짐.*/
		m_viewFlipper.showNext();/*다음에 읽을 화면이 있으면 보여줌 */
		Log.v(TAG, "뷰플리퍼 다음장으로 넘기기");
	}

	private void MovePreviousView()/*화면을 이전장으로 넘기는 메소드*/
	{
		m_viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.appear_from_left));//왼쪽에서 들어와서
		m_viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.disappear_to_right));/*오른쪽으로 사라짐*/
		m_viewFlipper.showPrevious();/*이전에 읽을 화면이 있으면 보여줌*/
		Log.v(TAG, "뷰플리퍼 이전 장으로 넘기기");
	}


	View.OnTouchListener myTouchListener = new View.OnTouchListener() 
	{

		public boolean onTouch(View v, MotionEvent event) 
		{

			if (event.getAction() == MotionEvent.ACTION_DOWN) /*Touch이벤트 Action_down = 뷰를 클릭(터치)했을 때*/
			{
				m_nPreTouchPosX = (int)event.getX();
			}
			if (event.getAction() == MotionEvent.ACTION_UP) /*Action_UP = 터치가 끝나서 손가락을 뗐을 때*/
			{
				int nTouchPosX = (int)event.getX();
				if (nTouchPosX < m_nPreTouchPosX) 
				{
					MoveNextView();
				}
				else if (nTouchPosX > m_nPreTouchPosX) 
				{
					MovePreviousView();
				}
				m_nPreTouchPosX = nTouchPosX;
			}
			return true;
		}
	};
}
