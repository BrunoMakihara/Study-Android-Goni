package com.example.acceleratorproject;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

public class AcceleratorView extends View {

	Context context = null;
	int width;	int height;
	int w10;
	ArrayList<AccelValue> arValue =
			new ArrayList<AccelValue>();
	final static int MAGX = 7;	final static int MAGY = 7;
	Paint p = null;
	Paint linePaint = new Paint();


	float textSize;
	void init(Context context){
		this.context = context;
		linePaint = new Paint();
		linePaint.setColor(0xffff0000);
		linePaint.setStrokeWidth(3);
		linePaint.setDither(true);
		linePaint.setAntiAlias(true);
		
		textSize = context.getResources().getDimension(R.dimen.textSize);
		p = new Paint();
		p.setTextSize(textSize);
		p.setStrokeWidth(3);
		p.setDither(true);
		p.setAntiAlias(true);
	}

	public AcceleratorView(Context context) {
		super(context);
		init(context);
	}

	public AcceleratorView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public AcceleratorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		width = w;
		height = h;
		w10 = width / 10;
	}
	public void setNewData(float[] values){
		AccelValue value = new AccelValue();
		value.setX(values[0]);
		value.setY(values[1]);
		value.setZ(values[2]);
		if(height < arValue.size() * MAGY){
			arValue.remove(0);
		}
		arValue.add(value);
		
		invalidate();
	}
	@Override
	protected void onDraw(Canvas canvas) {

		
		int i;
		int x, y;
		int oldx, oldy;
		int basex;

		canvas.drawColor(Color.YELLOW);
		// X 가속 그림 
		basex = oldx = w10 * 2;	
		oldy = 0;
		canvas.drawText("X", basex - textSize, textSize, p);
		canvas.drawLine(basex, 0, basex, height, linePaint);
		for (i = 0;i < arValue.size();i++) {
			x = (int)(basex + arValue.get(i).getX() * MAGX);
			y = i * MAGY;
			canvas.drawLine(oldx, oldy, x, y, p);
			oldx = x;
			oldy = y;
		}
		// Y 가속 그림
		basex = oldx = w10 * 5;
		oldy = 0;
		canvas.drawText("Y", basex - textSize, textSize, p);
		canvas.drawLine(basex, 0, basex, height, linePaint);
		for (i = 0;i < arValue.size();i++) {
			x = (int)(basex + arValue.get(i).getY() * MAGX);
			y = i * MAGY;
			canvas.drawLine(oldx, oldy, x, y, p);
			oldx = x;
			oldy = y;
		}

		// Z 가속 그림
		basex = oldx = w10 * 8;
		oldy = 0;
		canvas.drawText("Z", basex - textSize, textSize, p);
		canvas.drawLine(basex, 0, basex, height, linePaint);
		for (i = 0;i < arValue.size();i++) {
			x = (int)(basex + (arValue.get(i).getZ() -
					SensorManager.STANDARD_GRAVITY) * MAGX);
			y = i * MAGY;
			canvas.drawLine(oldx, oldy, x, y, p);
			oldx = x;
			oldy = y;
		}
	}    

}