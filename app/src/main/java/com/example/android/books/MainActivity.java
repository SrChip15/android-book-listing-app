package com.example.android.books;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Initialize activity on main thread.
		// Bundle holds previous state when re-initialized
		super.onCreate(savedInstanceState);

		// Inflate the activity's UI
		setContentView(R.layout.activity_main);
	}

	public void searchFor(View view) {
		// On click display list of books matching search criteria
		// Build intent to go to the {@link QueryResults} activity
		Intent results = new Intent(MainActivity.this, QueryResults.class);

		// Pass on the control to the new activity and start the activity
		startActivity(results);
	}
}
