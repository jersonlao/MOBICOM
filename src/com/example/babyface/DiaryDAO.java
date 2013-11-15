package com.example.babyface;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DiaryDAO extends SQLiteOpenHelper {
	
	final static String DATABASE_NAME = "diary.db"; // filename of db
	final static int DATABASE_VERSION = 1;
	
	public static final String DIARY_TABLE_NAME = "Diary";
	public static final String COLUMN_ID = "diary_id";
	public static final String COLUMN_BABY_ID = "baby_id";
	public static final String COLUMN_FRAME_ID = "frame_id";
	
	
	
	public static final String CREATE_DIARY_TABLE =  "CREATE TABLE" 
												+ DIARY_TABLE_NAME + "( " 
												+ COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " 
												+ COLUMN_BABY_ID + " INTEGER NOT NULL, " 
												+ COLUMN_FRAME_ID + "INTEGER, " 
												+ "FOREIGN KEY (baby_id) REFERENCES Baby(baby_id), "
												+ "FOREIGN KEY (frame_id) REFERENCES Frame(frame_id)"+");";
	
	public DiaryDAO(Context context){ //calling a constructor requires calling a context
								// context -> activity
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_DIARY_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP_TABLE_IF_EXISTS " + DIARY_TABLE_NAME);
		onCreate(db);
	}
	
	public long addDiary(Diary diary){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_BABY_ID, diary.getBabyId());
		cv.put(COLUMN_FRAME_ID, diary.getFrameId());
		
		long res = db.insert(DIARY_TABLE_NAME, null, cv);
		db.close();
		return res;
		
	}
	
	public List<Diary> getDiaries(){
		SQLiteDatabase db = this.getWritableDatabase();
		List<Diary> diaries = new ArrayList<Diary>();
		
		Cursor cursor = db.rawQuery("SELECT * FROM" + DIARY_TABLE_NAME, null);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()){
			
			Diary d = new Diary();
			d.setBabyId(cursor.getInt(1));
			d.setFrameId(cursor.getInt(2));
			
			diaries.add(d);
			
		}
		return diaries;
		
	}

}
