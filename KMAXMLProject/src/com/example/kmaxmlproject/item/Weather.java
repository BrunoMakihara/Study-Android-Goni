package com.example.kmaxmlproject.item;

import java.util.ArrayList;

public class Weather {

	public String year;
	public String month;
	public String day;
	public String hour;
	
	public ArrayList<Local> list = new ArrayList<Local>();

	@Override
	public String toString() {
		return "weather [year=" + year + ", month=" + month + ", day=" + day
				+ ", hour=" + hour + "]";
	}

}
