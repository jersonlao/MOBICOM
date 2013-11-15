package com.example.babyface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.text.InputType;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;

public class AddDiaryActivity extends Activity {
	
	private Calendar cal;
	private EditText date;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.add_diary);
		cal = Calendar.getInstance();
		date = (EditText)findViewById(R.id.dobEditText);
		setListeners();
	}
	
	public void setListeners(){
		
		
		date.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new DatePickerDialog(AddDiaryActivity.this,
						 dateListener,
						 cal.get(Calendar.YEAR),
						 cal.get(Calendar.MONTH),
						 cal.get(Calendar.DAY_OF_MONTH)).show();
				
			}
		});
		
		date.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				int inType = date.getInputType(); // backup the input type
				date.setInputType(InputType.TYPE_NULL); // disable soft input
				date.onTouchEvent(arg1); // call native handler
				date.setInputType(inType); // restore input type
				return true;
			}
			
			
		});
	
	}
	
	DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener(){
		@Override
		public void onDateSet(DatePicker view, int year, int month, int dayOfMonth){
			
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			
			SimpleDateFormat format = new SimpleDateFormat("MM-dd-yy", Locale.US);
			date.setText(format.format(cal.getTime()));
		}
		
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_diary, menu);
		return true;
	}

}
