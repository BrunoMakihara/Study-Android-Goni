package com.example.acceleratorproject;

import java.io.Serializable;

public class AccelValue implements Serializable {

	private static final long serialVersionUID = -1101370607906715957L;
	private float x;
	private float y;
	private float z;
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getZ() {
		return z;
	}
	public void setZ(float z) {
		this.z = z;
	}
	@Override
	public String toString() {
		return "AccelValue [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	
	

}
