package com.example.android.books;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

	private EditText mUserSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Initialize activity on main thread.
		// Bundle holds previous state when re-initialized
		super.onCreate(savedInstanceState);

		// Inflate the activity's UI
		setContentView(R.layout.activity_main);

		mUserSearch = (EditText) findViewById(R.id.user_input_text);
	}

	public void searchFor(View view) {
		// On click display list of books matching search criteria
		// Build intent to go to the {@link QueryResultsActivity} activity
		Intent results = new Intent(MainActivity.this, QueryResultsActivity.class);

		// Pass the search term to {@link QueryResultsActivity} to be used while creating the url
		results.putExtra("topic", mUserSearch.getText().toString().toLowerCase());

		// Pass on the control to the new activity and start the activity
		startActivity(results);
	}
}
