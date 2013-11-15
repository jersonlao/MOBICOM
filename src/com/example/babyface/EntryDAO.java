package com.example.babyface;




import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDAO extends SQLiteOpenHelper {
	
	final static String DATABASE_NAME = "diary.db"; // filename of db
	final static int DATABASE_VERSION = 1;
	
	public static final String ENTRY_TABLE_NAME = "Entry";
	public static final String COLUMN_ID = "entry_id";
	public static final String COLUMN_DIARY_ID = "diary_id";
	public static final String COLUMN_EVENT = "event";
	public static final String COLUMN_DESCRIPTION = "description";
	
	
	
	
	public static final String CREATE_DIARY_TABLE =  "CREATE TABLE" 
												+ ENTRY_TABLE_NAME + "( " 
												+ COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " 
												+ COLUMN_DIARY_ID + " INTEGER, " 
												+ COLUMN_EVENT + "TEXT, " 
												+ COLUMN_DESCRIPTION + "TEXT, " 
												+ "FOREIGN KEY (diary_id) REFERENCES Diary(diary_id)"+");";
	
	
	public EntryDAO(Context context){ //calling a constructor requires calling a context
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
		db.execSQL("DROP_TABLE_IF_EXISTS " + ENTRY_TABLE_NAME);
		onCreate(db);
	}
	
	public long addEntry(Entry entry){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_DIARY_ID, entry.getDiaryID());
		cv.put(COLUMN_EVENT, entry.getEvent());
		cv.put(COLUMN_DESCRIPTION, entry.getDescription());
		
		
		long res = db.insert(ENTRY_TABLE_NAME, null, cv);
		db.close();
		return res;
		
	}
	
	public List<Entry> getEntries(){
		SQLiteDatabase db = this.getWritableDatabase();
		List<Entry> entries = new ArrayList<Entry>();
		
		Cursor cursor = db.rawQuery("SELECT * FROM" + ENTRY_TABLE_NAME, null);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()){
			
			
			
				Entry e = new Entry();
				e.setDiaryID(cursor.getInt(1));
				e.setEvent(cursor.getString(2));
				e.setDescription(cursor.getString(3));
				entries.add(e);
			
			
			
			
		}
		return entries;
		
	}

}

