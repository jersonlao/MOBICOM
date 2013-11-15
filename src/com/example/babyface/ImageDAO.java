package com.example.babyface;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ImageDAO extends SQLiteOpenHelper {
	
	final static String DATABASE_NAME = "diary.db"; // filename of db
	final static int DATABASE_VERSION = 1;
	
	public static final String IMAGE_TABLE_NAME = "Image";
	public static final String COLUMN_ID = "image_id";
	public static final String COLUMN_ENTRY_ID = "entry_id";
	public static final String COLUMN_IMAGE_NAME = "image_name";
	public static final String COLUMN_IMAGE_PATH = "image_path";
	
	
	public static final String CREATE_IMAGE_TABLE =  "CREATE TABLE" 
													+ IMAGE_TABLE_NAME + "( " 
													+ COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " 
													+ COLUMN_ENTRY_ID + " INTEGER, "
													+ COLUMN_IMAGE_NAME + "TEXT, " 
													+ COLUMN_IMAGE_PATH + "TEXT, " +");";
	
	public ImageDAO(Context context){ //calling a constructor requires calling a context
								// context -> activity
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_IMAGE_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP_TABLE_IF_EXISTS " + CREATE_IMAGE_TABLE);
		onCreate(db);
	}
	
	
	public List<Image> getImages(int entry_id){
		SQLiteDatabase db = this.getWritableDatabase();
		List<Image> images = new ArrayList<Image>();
		
		Cursor cursor = db.rawQuery("SELECT * FROM" + IMAGE_TABLE_NAME + "WHERE entry_id =" + entry_id, null);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()){
			
			
			
				Image i = new Image();
				i.setImage_name(cursor.getString(3));
				i.setImage_path(cursor.getString(4));
				images.add(i);
			
			
		}
			return images;
	}

}
