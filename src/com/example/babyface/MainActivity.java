package com.example.babyface;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {
	
	Button exit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_main);
		
			 
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.add_view:
	    	Intent i = new Intent(this, AddDiaryActivity.class);
	    	startActivity(i);
	        return true;
	    case R.id.about_view:
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		getMenuInflater().inflate(R.menu.main_menu_actions, menu);
		return true;
	}
	
//	

}
