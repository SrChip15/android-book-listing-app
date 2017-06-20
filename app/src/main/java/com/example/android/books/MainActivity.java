package com.example.android.books;

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
		// TODO: Get the user input and search via Google Books API
	}
}
