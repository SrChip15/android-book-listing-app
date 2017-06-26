package com.example.android.books;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	private EditText mUserSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Initialize activity on main thread.
		// Bundle holds previous state when re-initialized
		super.onCreate(savedInstanceState);

		// Inflate the activity's UI
		setContentView(R.layout.activity_main);

		// Get a reference to the user input edit text view
		mUserSearch = (EditText) findViewById(R.id.user_input_text);

		// Get a reference to the {@link ImageButton} to implement button click via keyboard
		final ImageButton search = (ImageButton) findViewById(R.id.search_button);

		// Set the an {@link OnEditorActionListener} on the editable text view
		// Implement search button click when user presses the done button on the keyboard
		mUserSearch.setOnEditorActionListener(new EditText.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// Check whether the done button is pressed on the keyboard
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					// User has finished entering text
					// Perform the search button click programmatically
					search.performClick();
					// Return true on successfully handling the action
					return true;
				}

				// Do not perform any task when user is actually entering text
				// in the editable text view
				return false;
			}
		});
	}

	public void searchFor(View view) {
		EditText userInput = (EditText) findViewById(R.id.user_input_text);
		String input = userInput.getText().toString();

		if (!input.isEmpty()) {
			// On click display list of books matching search criteria
			// Build intent to go to the {@link QueryResultsActivity} activity
			Intent results = new Intent(MainActivity.this, QueryResultsActivity.class);

			// Pass the search term to {@link QueryResultsActivity} to be used while creating the url
			results.putExtra("topic", mUserSearch.getText().toString().toLowerCase());

			// Pass on the control to the new activity and start the activity
			startActivity(results);
		} else {
			// User has not entered any search text
			// Notify user to enter text via toast
			Toast.makeText(MainActivity.this, "Enter text before proceeding", Toast.LENGTH_SHORT).show();
		}
	}
}
