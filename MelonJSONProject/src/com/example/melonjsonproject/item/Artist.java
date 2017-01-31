package com.example.melonjsonproject.item;

import java.io.Serializable;

public class Artist implements Serializable {

	private static final long serialVersionUID = -6630160821888461799L;
	
	private int artistId;
	private String artistName;
	@Override
	public String toString() {
		return "Artist [artistId=" + artistId + ", artistName=" + artistName
				+ "]";
	}
	public int getArtistId() {
		return artistId;
	}
	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	
//
//	{
//	    "melon":{
//	        "menuId":54020101,
//	        "count":10,
//	        "page":1,
//	        "totalPages":10,
//	        "rankDay":"20140408",
//	        "rankHour":"16",
//	        "songs":{
//	            "song":[
//	                {
//	                    "songId":4583482,
//	                    "songName":"200%",
//	                    "artists":{
//	                        "artist":[
//	                            {
//	                                "artistId":712452,
//	                                "artistName":"악동뮤지션 (AKMU)"
//	                            }
//	                        ]
//	                    },
//	                    "albumId":2249127,
//	                    "albumName":"PLAY",
//	                    "currentRank":1,
//	                    "pastRank":1,
//	                    "playTime":193,
//	                    "issueDate":"20140407",
//	                    "isTitleSong":"true",
//	                    "isHitSong":"false",
//	                    "isAdult":"false",
//	                    "isFree":"false"
//	                }
}
