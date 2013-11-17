package com.example.model;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.babyface.BabyFaceDAO;
import com.example.object.Diary;


public class DiaryModel {
	
private BabyFaceDAO db;
private SQLiteDatabase sqldb;
private  ContentValues contentVal;

	public DiaryModel(BabyFaceDAO db){
		this.db = db;
		contentVal=new ContentValues();;
	}
	
	
	public ArrayList<Diary> getAllDiary(){
		Cursor c;
		Diary event;
		ArrayList<Diary> diaryList = new ArrayList<Diary>();
		
		   
		sqldb = db.getReadableDatabase();
	
//          c = sqldb.query(DBHelper.TABLE, new String[] {
//                  DBHelper.Id_Col, DBHelper.Title_Col, DBHelper.Date_Col, DBHelper.Link_Col}, null, null, null, null, DBHelper.Id_Col + " ASC");
//          
//          																	
//      c.moveToFirst();
//	   while(!c.isAfterLast()){
//		   event = new Event();
//	        event.setId(c.getInt(c.getColumnIndex(DBHelper.Id_Col)));
//	        event.setTitle(c.getString(c.getColumnIndex(DBHelper.Title_Col)));
//	        event.setDate( c.getString(c.getColumnIndex(DBHelper.Date_Col))); 
//	        event.setLink(c.getString(c.getColumnIndex(DBHelper.Link_Col)));
//	        
//	        eventList.add(event);
//	        c.moveToNext();
		return diaryList;
		
	
	}
	
}
