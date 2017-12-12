package com.example.android.books;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	/** Editable {@link TextView} for user's search text */
	private EditText mUserSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Initialize activity on main thread.
		// Bundle holds previous state when re-initialized
		super.onCreate(savedInstanceState);

		// Inflate the activity's UI
		setContentView(R.layout.activity_main);

		// Setup UI to hide soft keyboard when clicked outside the {@link EditText}
		setupUI(findViewById(R.id.main_parent));

		// Get a reference to the user input edit text view
		mUserSearch = findViewById(R.id.user_input_edit_text_view);

		// Get a reference to the {@link ImageButton} to implement button click via keyboard
		final ImageButton search = findViewById(R.id.search_button);

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

	/**
	 * This method is called when the user hits the search button
	 * It connects the user's search text to the query methods
	 */
	public void searchFor(View view) {
		// Get a handle for the editable text view holding the user's search text
		EditText userInput = findViewById(R.id.user_input_edit_text_view);
		// Get the characters from the {@link EditText} view and convert it to string value
		String input = userInput.getText().toString();

		// Search filter for search text matching book titles
		RadioButton mTitleChecked = findViewById(R.id.title_radio);
		// Search filter for search text matching authors
		RadioButton mAuthorChecked = findViewById(R.id.author_radio);
		// Search filter for search text matching ISBN numbers
		RadioButton mIsbnChecked = findViewById(R.id.isbn_radio);

		if (!input.isEmpty()) {
			// On click display list of books matching search criteria
			// Build intent to go to the {@link QueryResultsActivity} activity
			Intent results = new Intent(MainActivity.this, QueryResultsActivity.class);

			// Get the user search text to {@link QueryResultsActivity}
			// to be used while creating the url
			results.putExtra("topic", mUserSearch.getText().toString().toLowerCase());

			// Pass search filter, if any
			if (mTitleChecked.isChecked()) {
				// User is searching for book titles that match the search text
				results.putExtra("title", "intitle=");
			} else if (mAuthorChecked.isChecked()) {
				// User is searching for authors that match the search text
				results.putExtra("author", "inauthor=");
			} else if (mIsbnChecked.isChecked()) {
				// User is specifically looking for the book with the provided isbn number
				results.putExtra("isbn", "isbn=");
			}

			// Pass on the control to the new activity and start the activity
			startActivity(results);

		} else {
			// User has not entered any search text
			// Notify user to enter text via toast

			Toast.makeText(
					MainActivity.this,
					getString(R.string.enter_text),
					Toast.LENGTH_SHORT)
					.show();
		}
	}

	/**
	 * Set up touch listeners on all parts of the UI besides the {@link EditText} so that the user
	 * can click out to hide the soft keypad and choose the necessary filter radio boxes befitting
	 * their need
	 */
	public void setupUI(View view) {
		// Set up touch listener for non-text box views to hide keyboard
		if (!(view instanceof EditText)) {
			view.setOnTouchListener(new View.OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// Hide keypad
					v.performClick();
					hideSoftKeyboard(MainActivity.this);
					return false;
				}
			});
		}

		//If a layout container, iterate over children and seed recursion
		if (view instanceof ViewGroup) {
			// Current view is a {@Link ViewGroup}
			// Traverse the {@link ViewGroup}, over each child
			for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
				// Get the current child view
				View innerView = ((ViewGroup) view).getChildAt(i);
				// Set up touch listeners on non-text box views
				setupUI(innerView);
			}
		}
	}

	/**
	 * This method hides the soft keypad that pops up when there are views that solicit user input
	 */
	public static void hideSoftKeyboard(Activity activity) {
		// Get the activity's input method service
		InputMethodManager inputMethodManager =
				(InputMethodManager) activity.getSystemService(
						Activity.INPUT_METHOD_SERVICE);

		// Hide the soft keypad
		inputMethodManager.hideSoftInputFromWindow(
				activity.getCurrentFocus().getWindowToken(), 0);
	}
}
