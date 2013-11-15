package com.example.babyface;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class FrameDAO extends SQLiteOpenHelper {
	
	final static String DATABASE_NAME = "diary.db"; // filename of db
	final static int DATABASE_VERSION = 1;
	
	public static final String FRAME_TABLE_NAME = "Frame";
	public static final String COLUMN_ID = "frame_id";
	public static final String COLUMN_FRAME = "frame";
	
	
	public static final String CREATE_FRAME_TABLE =  "CREATE TABLE" 
													+ FRAME_TABLE_NAME + "( " 
													+ COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " 
													+ COLUMN_FRAME + " TEXT UNIQUE " + ");";
	
	public FrameDAO(Context context){ //calling a constructor requires calling a context
								// context -> activity
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_FRAME_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP_TABLE_IF_EXISTS " + FRAME_TABLE_NAME);
		onCreate(db);
	}
	
	
	public Frame getFrame(int frame_id){
		SQLiteDatabase db = this.getWritableDatabase();
		List<Frame> frame = new ArrayList<Frame>();
		
		Cursor cursor = db.rawQuery("SELECT * FROM" + FRAME_TABLE_NAME + "WHERE frame_id =" + frame_id, null);
		cursor.moveToFirst();
		Frame f = new Frame();
		while(!cursor.isAfterLast()){
			
			
			f.setId(cursor.getInt(1));
			f.setFrame(cursor.getString(2));
			
			
			
		}
		return f;
		
	}

}
