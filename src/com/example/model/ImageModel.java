package com.example.model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.babyface.BabyFaceDAO;

public class ImageModel {
	private	BabyFaceDAO db;
	private SQLiteDatabase sqldb;
	private ContentValues contentVal;
	
		public ImageModel(BabyFaceDAO db){
			this.db = db;
			contentVal=new ContentValues();;
		}
		
}
