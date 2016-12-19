package com.example.cursorproject.item;

public class AMember {
	// 테이블의 Record를 관리하게 됨

	public int _id;
	public String fname;
	public String lname;
	public String tel;
	public String address;
	public int age;
	public int bigo;

	@Override
	public String toString() {
		return "AMember [_id=" + _id + ", fname=" + fname + ", lname=" + lname
				+ ", tel=" + tel + ", address=" + address + ", age=" + age
				+ ", bigo=" + bigo + "]";
	}

}
