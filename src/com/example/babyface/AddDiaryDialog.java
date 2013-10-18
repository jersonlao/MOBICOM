package com.example.babyface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.view.View.OnClickListener;

public class AddDiaryDialog extends Dialog {

	private Activity c;
	private Button exit;
	private Calendar cal;
	private EditText date;
	
	

	public AddDiaryDialog(Activity a) {
		super(a);
		this.c = a;
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	    getWindow().setFlags(LayoutParams.FLAG_NOT_TOUCH_MODAL, LayoutParams.FLAG_NOT_TOUCH_MODAL);
	    getWindow().setFlags(LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.new_diary_dialog);
		cal = Calendar.getInstance();
		exit = (Button) findViewById(R.id.dialogExitButton);
		date = (EditText) findViewById(R.id.dateEditText);
		setListeners();
		
	
	}
	
	public void setListeners(){
		exit.setOnClickListener(new View.OnClickListener(){

			
			@Override
			public void onClick(View v) {
				dismiss();
				
			}
			
			
		});
		
		date.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new DatePickerDialog(AddDiaryDialog.this.getContext(),
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
	  public boolean onTouchEvent(MotionEvent event) {
	    
	    if (MotionEvent.ACTION_OUTSIDE == event.getAction()) {
	      //do nothing
	      return true;
	    }
	    return super.onTouchEvent(event);
	  }

}
