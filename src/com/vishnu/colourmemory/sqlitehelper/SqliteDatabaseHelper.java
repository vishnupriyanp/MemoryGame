package com.vishnu.colourmemory.sqlitehelper;

/**
 * This class is for Data Base handling
 * 
 * @author Vishnupriyan P
 * 
 */

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vishnu.colourmemory.model.ScoreModel;

public class SqliteDatabaseHelper extends SQLiteOpenHelper {

	private static SQLiteDatabase memorygameDB;
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "memorygamedb";
	private static final String CONTENT_TABLE = "highscores";
	private static final String KEY_ID = "id";
	private static final String NAME = "name";
	private static final String SCORE = "score";
	private static final String RANK = "rank";
	private ArrayList<ScoreModel> dataList = new ArrayList<ScoreModel>();
	private final String CREATE_HIGHSCORE_TABLE = "CREATE TABLE " + CONTENT_TABLE
			+ "(" + KEY_ID + " INTEGER PRIMARY KEY," + NAME + " TEXT," + SCORE
			+ " TEXT," + RANK + " TEXT)";

	public SqliteDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_HIGHSCORE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		memorygameDB = this.getWritableDatabase();
	}

	public void setContentDB(SQLiteDatabase db) {
		memorygameDB = db;
	}

	public void addScores(String name, String score, String rank) {
		ContentValues cv = new ContentValues();
		cv.put(NAME, name);
		cv.put(SCORE, score);
		cv.put(RANK, rank);
		memorygameDB.insert(CONTENT_TABLE, null, cv);
	}

	public ArrayList<ScoreModel> getHighScores() {
		dataList.clear();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from " + CONTENT_TABLE
				+ " order by score desc", null);
		if (cursor.getCount() != 0) {
			if (cursor.moveToFirst()) {
				do {
					ScoreModel item = new ScoreModel();
					item.setName(cursor.getString(cursor.getColumnIndex("name")));
					item.setScore(cursor.getString(cursor
							.getColumnIndex("score")));
					item.setRank(cursor.getString(cursor.getColumnIndex("rank")));
					dataList.add(item);
				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return dataList;
	}
	
	public int getHighScore() {
		int score = 0;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("select score from " + CONTENT_TABLE
				+ " order by score desc LIMIT 1", null);
		if (cursor.getCount() != 0) {
			if (cursor.moveToFirst()) {
				do {
					score = (cursor.getInt(cursor.getColumnIndex("score")));				
				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return score;
	}
}
