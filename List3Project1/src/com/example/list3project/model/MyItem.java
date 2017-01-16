package com.example.list3project.model;

import java.io.Serializable;

public class MyItem implements Serializable {

	private static final long serialVersionUID = 243329627209873728L;

	private String title;
	private int image; // int 인 이유 R.java꺼 그냥 쓸려고
	private int price;
	
	public MyItem () {}
	public MyItem (int image, String title, int price) {
		this.image = image;
		this.title = title;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() { // 는 이상한 숫자가 나오니까... 이렇게 오버라이딩 해주면 나중에 확인 해 볼 수
								// 있어
		return "MyItem [title=" + title + ", image=" + image + ", price="
				+ price + "]";
	}

}
