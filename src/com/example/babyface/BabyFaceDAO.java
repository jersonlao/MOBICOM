package com.example.babyface;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class BabyFaceDAO extends SQLiteOpenHelper{
	
	public static final String DBNAME="BabyFace";
	
	public static final String DIARY_TABLE="Diary";
	public static final String COLUMN_DIARY_ID = "diaryID";
	public static final String COLUMN_BABY_NAME = "babyName";
	public static final String COLUMN_BABY_DOB = "babyDOB";
	public static final String COLUMN_BABY_POB = "babyPOB";
	public static final String COLUMN_GENDER = "gender";
	public static final String COLUMN_DIARY_COLOR ="diaryColor";

	public static final String EVENT_TABLE = "Event";
	public static final String COLUMN_EVENT_ID = "eventID";
	public static final String COLUMN_EVENT_NAME = "eventName";
	public static final String COLUMN_EVENT_DESC = "eventDesc";
	
	public static final String IMAGE_TABLE = "Image";
	public static final String COLUMN_IMAGE_ID = "imageID";
	public static final String COLUMN_IMAGE_NAME = "imageName";
	public static final String COLUMN_IMAGE_PATH = "imagePath";
	
	
	
	public static final String CREATE_DIARY_TABLE =
	        "CREATE TABLE "+ DIARY_TABLE + " ("+
	        	COLUMN_DIARY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
	            + COLUMN_BABY_NAME + " text not null,"
	            + COLUMN_BABY_DOB + " int not null,"
	            + COLUMN_BABY_POB +" text not null,"
	            + COLUMN_GENDER + " int not null,"
	            + COLUMN_DIARY_COLOR +" int not null" 
	            +");";

	public static final String CREATE_EVENT_TABLE =
	        "CREATE TABLE "+ EVENT_TABLE + " (" 
	        	+COLUMN_EVENT_ID +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
	            + COLUMN_EVENT_NAME + " TEXT not null,"
	            + COLUMN_EVENT_DESC + " TEXT not null,"
	            + COLUMN_DIARY_ID + " INTEGER, " 
	            + "FOREIGN KEY (diaryID) REFERENCES Diary(diaryID)"
	            +");";
	
	
	public static final String CREATE_IMAGE_TABLE =
	        "CREATE TABLE "+ IMAGE_TABLE + " (" 
	        	+COLUMN_IMAGE_ID +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
	            + COLUMN_IMAGE_NAME + " TEXT not null,"
	            + COLUMN_IMAGE_PATH + " TEXT not null,"
	            + COLUMN_EVENT_ID + " INTEGER, " 
	            + "FOREIGN KEY ("+ COLUMN_EVENT_ID +") REFERENCES Event(eventID)"
	            +");";
	
	
	public BabyFaceDAO(Context context) {
		  super(context, DBNAME, null,1); 
		  
		  }

	public BabyFaceDAO(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}




	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_DIARY_TABLE);
		db.execSQL(CREATE_EVENT_TABLE);
		db.execSQL(CREATE_IMAGE_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		 db.execSQL("DROP TABLE IF EXISTS "+ IMAGE_TABLE);
		 db.execSQL("DROP TABLE IF EXISTS "+ EVENT_TABLE);
		 db.execSQL("DROP TABLE IF EXISTS "+ DIARY_TABLE);
		
	
		 
		 onCreate(db);
	}
	



}
