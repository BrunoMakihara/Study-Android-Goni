package com.example.melonjsonproject.item;

import java.io.Serializable;
import java.util.ArrayList;

public class Melon implements Serializable {

	private static final long serialVersionUID = -6372915786053898613L;

	private int menuId;
	private int count;
	private int page;
	private int totalPages;
	private String rankDay;
	private String rankHour;
	private ArrayList<Song> songs = new ArrayList<Song>();
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public String getRankDay() {
		return rankDay;
	}
	public void setRankDay(String rankDay) {
		this.rankDay = rankDay;
	}
	public String getRankHour() {
		return rankHour;
	}
	public void setRankHour(String rankHour) {
		this.rankHour = rankHour;
	}
	public ArrayList<Song> getSongs() {
		return songs;
	}
	public void setSongs(ArrayList<Song> songs) {
		this.songs = songs;
	}
	@Override
	public String toString() {
		return "Melon [menuId=" + menuId + ", count=" + count + ", page="
				+ page + ", totalPages=" + totalPages + ", rankDay=" + rankDay
				+ ", rankHour=" + rankHour + ", songs=" + songs + "]";
	}
	
	
	
	
//	{
//    "melon":{
//        "menuId":54020101,
//        "count":10,
//        "page":1,
//        "totalPages":10,
//        "rankDay":"20140408",
//        "rankHour":"16",
//        "songs":{
//            "song":[
//                {
//                    "songId":4583482,
//                    "songName":"200%",
//                    "artists":{
//                        "artist":[
//                            {
//                                "artistId":712452,
//                                "artistName":"악동뮤지션 (AKMU)"
//                            }
//                        ]
//                    },
//                    "albumId":2249127,
//                    "albumName":"PLAY",
//                    "currentRank":1,
//                    "pastRank":1,
//                    "playTime":193,
//                    "issueDate":"20140407",
//                    "isTitleSong":"true",
//                    "isHitSong":"false",
//                    "isAdult":"false",
//                    "isFree":"false"
//                }
}
