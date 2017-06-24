package com.example.android.books;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QueryResultsActivity extends AppCompatActivity {

	//private static final String LOG_TAG = QueryResultsActivity.class.getSimpleName();

	/** URL for books data from the Google books API */
	private String REQUEST_URL =
			"https://www.googleapis.com/books/v1/volumes?q=";

	/** Adapter for the list of book titles */
	private BookAdapter mAdapter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		// Initialize activity on main thread.
		// Bundle holds previous state when re-initialized
		super.onCreate(savedInstanceState);

		// Inflate the activity's UI
		setContentView(R.layout.list_of_books);

		// Hook the recycler view
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

		// Set fixed size true and optimize recycler view performance
		// The data container has fixed number of attractions and not infinite list
		recyclerView.setHasFixedSize(true);

		// Connect the {@link RecyclerView} widget to the vertical linear layout
		recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

		// Initialize the adapter with the sample data
		mAdapter = new BookAdapter(this, new ArrayList<String>());

		// Attach adapter to the {@link RecyclerView} widget
		// so the widget can be populated in the UI
		recyclerView.setAdapter(mAdapter);

		// Get the search term from user input
		String searchForText = getIntent().getStringExtra("topic");

		// Build the url from user search
		REQUEST_URL += searchForText;

		// Start the AsyncTask to fetch books from Google Books API
		AsyncBooksTask asyncBooksTask = new AsyncBooksTask();
		/* URL for the search query */
		asyncBooksTask.execute(REQUEST_URL);
	}

	/**
	 * {@link AsyncTask} to perform the network request and its subsequent parsing on a separate
	 * background thread pool thereby not blocking the UI thread. Upon successfully downloading and
	 * parsing the information, the UI is updated with the new data list. Since, the application
	 * will not display progress of the network request and information parsing, Progress parameter
	 * will be Void while the Params will be {@link String} and
	 * the Result will be {@link List<String>}. Correspondingly, the class would only override the
	 * doInBackground() and onPostExecute() methods.
	 */
	private class AsyncBooksTask extends AsyncTask<String, Void, List<String>> {

		/**
		 * This method performs the network request and parsing on a pool of background threads
		 * and does not overload the UI thread
		 *
		 * @param urls the appropriate URL as a {@link String}
		 * @return the list of book titles
		 */
		@Override
		protected List<String> doInBackground(String... urls) {
			if (urls.length < 1 || urls[0] == null) {
				return null;
			}
			return QueryUtils.fetchBooks(urls[0]);
		}

		/**
		 * This method gets the result of the background thread pool and as it runs on the UI thread
		 * updates the UI correctly ensuring thread-safety.
		 *
		 * @param data the list of book titles parsed from the HTTP request
		 */
		@Override
		protected void onPostExecute(List<String> data) {
			// Clear existing data on adapter before updating with the new list of book titles
			mAdapter.clear();

			// Ensure data list is not empty and add it to the adapter
			if (data != null && !data.isEmpty()) {
				mAdapter.addAll(data);
			}
		}
	}
}

