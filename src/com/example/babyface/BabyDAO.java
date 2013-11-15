package com.example.babyface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BabyDAO extends SQLiteOpenHelper {
	
	final static String DATABASE_NAME = "diary.db"; // filename of db
	final static int DATABASE_VERSION = 1;
	
	public static final String BABY_TABLE_NAME = "Baby";
	public static final String COLUMN_ID = "baby_id";
	public static final String COLUMN_BABY_NAME = "baby_name";
	public static final String COLUMN_BABY_DOB = "baby_dob";
	public static final String COLUMN_BABY_POB = "baby_pob";
	public static final String COLUMN_GENDER_ID = "gender_id";
	public static final String COLUMN_PROFILE_IMAGE = "profile_image";
	
	
	
	public static final String CREATE_DIARY_TABLE =  "CREATE TABLE" 
												+ BABY_TABLE_NAME + "( " 
												+ COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " 
												+ COLUMN_BABY_NAME + " TEXT NOT NULL, " 
												+ COLUMN_BABY_DOB + "DATE, " 
												+ COLUMN_BABY_POB + "TEXT, " 
												+ COLUMN_GENDER_ID + " INTEGER, " 
												+ COLUMN_PROFILE_IMAGE + "TEXT, " 
												+ "FOREIGN KEY (gender_id) REFERENCES Gender(gender_id)"+");";
	
	
	public BabyDAO(Context context){ //calling a constructor requires calling a context
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
		db.execSQL("DROP_TABLE_IF_EXISTS " + BABY_TABLE_NAME);
		onCreate(db);
	}
	
	public long addBaby(Baby baby){
		SQLiteDatabase db = this.getWritableDatabase();
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_BABY_NAME, baby.getName());
		cv.put(COLUMN_BABY_DOB, parser.format(baby.getDob()));
		cv.put(COLUMN_BABY_POB, baby.getPob());
		cv.put(COLUMN_GENDER_ID, baby.getGender());
		cv.put(COLUMN_PROFILE_IMAGE, baby.getImage_path());
		
		long res = db.insert(BABY_TABLE_NAME, null, cv);
		db.close();
		return res;
		
	}
	
	public List<Baby> getBabies(){
		SQLiteDatabase db = this.getWritableDatabase();
		List<Baby> babies = new ArrayList<Baby>();
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		Cursor cursor = db.rawQuery("SELECT * FROM" + BABY_TABLE_NAME, null);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()){
			
			
			try {
				Baby b = new Baby();
				b.setId(cursor.getInt(1));
				b.setName(cursor.getString(2));
				b.setDob(parser.parse(cursor.getString(3)));
				b.setPob(cursor.getString(4));
				b.setGender(cursor.getInt(5));
				b.setImage_path(cursor.getString(6));
				babies.add(b);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		return babies;
		
	}

}
