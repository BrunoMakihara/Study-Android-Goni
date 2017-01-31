package com.example.melonjsonproject.parser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.melonjsonproject.item.Artist;
import com.example.melonjsonproject.item.Melon;
import com.example.melonjsonproject.item.Song;

public class MelonJSONParser {
	private static final String TAG = "Goni";

	// 귀찮아서 생성하지 않고 . 찍어서 사용해라
	public static Melon parse(JSONObject object) {

		Melon melon = new Melon();
		try {
			// 중괄호가 있으면 제이슨 오브젝트다
			// object.getJSONObject("melon");는 객채를 만들어 줌 1번째 인자는 
			// Object 문자열 처음{부터 마지막}까지의 문자를 넣어주면 돼
			JSONObject jMelon = object.getJSONObject("melon");
			Log.v(TAG, "melon : ");

			// data를 받아오는 것
			melon.setMenuId(jMelon.getInt("menuId"));
			melon.setCount(jMelon.getInt("count"));
			melon.setPage(jMelon.getInt("page"));
			melon.setTotalPages(jMelon.getInt("totalPages"));
			melon.setRankDay(jMelon.getString("rankDay"));
			melon.setRankHour(jMelon.getString("rankHour"));

			// 배열을 뽑아내는 것
			JSONArray jSongs = jMelon.getJSONObject("songs").getJSONArray(
					"song");
			// 이 것도 가능한듯 
			// JSONArray jsongs = jMelon.getJSONArray("songs"); 
			// song을 뽑아내기 위한 객체들
			Song song;
			ArrayList<Song> songList = melon.getSongs();
			JSONObject sTemp;

			for (int i = 0; i < jSongs.length(); i++) {
				song = new Song();
				sTemp = jSongs.getJSONObject(i);
				song.setSongId(sTemp.getInt("songId"));
				song.setSongName(sTemp.getString("songName"));

				// Artists배열을 뽑아내는 것
				JSONArray jArtists = sTemp.getJSONObject("artists")
						.getJSONArray("artist");
				// artist를 뽑아내기 위한 객체들
				Artist artist = null;
				ArrayList<Artist> artistList = song.getArtists();
				JSONObject aTemp = null;

				for (int j = 0; j < jArtists.length(); j++) {
					artist = new Artist();
					aTemp = jArtists.getJSONObject(j);
					artist.setArtistId(aTemp.getInt("artistId"));
					artist.setArtistName(aTemp.getString("artistName"));

					// 최종적으로 add
					artistList.add(artist);
				}
				song.setAlbumId(sTemp.getInt("albumId"));
				song.setAlbumName(sTemp.getString("albumName"));
				song.setCurrentRank(sTemp.getInt("currentRank"));
				song.setPastRank(sTemp.getInt("pastRank"));
				song.setPlayTime(sTemp.getInt("playTime"));
				song.setIssueDate(sTemp.getString("issueDate"));
				song.setTitleSong(sTemp.getBoolean("isTitleSong"));
				song.setHitSong(sTemp.getBoolean("isHitSong"));
				song.setAdult(sTemp.getBoolean("isAdult"));
				song.setFree(sTemp.getBoolean("isFree"));

				// 최종적으로 add
				songList.add(song);
			}
			Log.v(TAG, "json parsing success");

		} catch (JSONException e) {
			Log.v(TAG, "json parser error : " + e);
		}

		return melon;
	}

	// {
	// "melon":{
	// "menuId":54020101,
	// "count":10,
	// "page":1,
	// "totalPages":10,
	// "rankDay":"20140408",
	// "rankHour":"16",
	// "songs":{
	// "song":[
	// {
	// "songId":4583482,
	// "songName":"200%",
	// "artists":{
	// "artist":[
	// {
	// "artistId":712452,
	// "artistName":"악동뮤지션 (AKMU)"
	// }
	// ]
	// },
	// "albumId":2249127,
	// "albumName":"PLAY",
	// "currentRank":1,
	// "pastRank":1,
	// "playTime":193,
	// "issueDate":"20140407",
	// "isTitleSong":"true",
	// "isHitSong":"false",
	// "isAdult":"false",
	// "isFree":"false"
	// }
}
