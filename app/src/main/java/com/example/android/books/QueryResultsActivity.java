package com.example.android.books;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QueryResultsActivity
		extends AppCompatActivity
		implements LoaderCallbacks<List<Book>> {

	/** URL for books data from the Google books API */
	private String REQUEST_URL =
			"https://www.googleapis.com/books/v1/volumes?q=";

	private static final String API_KEY = "AIzaSyCaNgg0GLoPlz75osYA3mDIYG0rWAZo01s";

	/** Adapter for the list of book titles */
	private BookAdapter mAdapter;

	/** Constant value for the earthquake loader ID */
	private static final int EARTHQUAKE_LOADER_ID = 1;

	/** Indeterminate progress bar for loading books */
	private ProgressBar mProgressSpinner;

	/** TextView that is displayed when the list is empty */
	private TextView mEmptyStateView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		// Initialize activity on main thread.
		// Bundle holds previous state when re-initialized
		super.onCreate(savedInstanceState);

		// Inflate the activity's UI
		setContentView(R.layout.list_of_books);

		// Hook the recycler view
		BookRecyclerView recyclerView = findViewById(R.id.recycler_view);

		// Set fixed size true and optimize recycler view performance
		// The data container has fixed number of attractions and not infinite list
		recyclerView.setHasFixedSize(true);

		// Connect the {@link RecyclerView} widget to a GridView layout
		// Get the current orientation of the screen
		int orientation = this.getResources().getConfiguration().orientation;
		// Set span count based on orientation
		if (orientation == Configuration.ORIENTATION_PORTRAIT) {
			recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
		} else {
			recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
		}

		// Initialize the adapter with the sample data
		mAdapter = new BookAdapter(new ArrayList<Book>());

		// Attach adapter to the {@link RecyclerView} widget
		// so the widget can be populated in the UI
		recyclerView.setAdapter(mAdapter);

		// Set empty view when there is no data on the recycler view
		mEmptyStateView = findViewById(R.id.empty_text_view);
		recyclerView.setEmptyView(mEmptyStateView);

		// Get reference to the Progress bar
		mProgressSpinner = findViewById(R.id.progress_spinner);
		// Indeterminate progress bar type
		mProgressSpinner.setIndeterminate(true);

		// Get the spawn intent
		Intent queryIntent = getIntent();
		// Get the search text typed by the user
		String searchText = getIntent().getStringExtra("topic");
		// Initialize variable to hold the processed search query
		String processedQuery = "";
		// Get the value for title key packaged in the spawn intent
		String title = queryIntent.getStringExtra("title");
		// Get the value for author key packaged in the spawn intent
		String author = queryIntent.getStringExtra("author");
		// Get the value for isbn key packaged in the spawn intent
		String isbn = queryIntent.getStringExtra("isbn");

		// Determine which radio box was checked based on non-null values from the above keys
		if (title != null) {
			// Title was checked by the user
			processedQuery = searchText + "&" + title + searchText;
		} else if (author != null) {
			// User is searching an author matching the search text
			processedQuery = searchText + "&" + author + searchText;
		} else if (isbn != null) {
			// User is searching the isbn number matching the search text
			processedQuery = searchText + "&" + isbn + searchText;
		} else {
			// No filters used
			processedQuery = searchText;
		}

		// Build the url from user search
		REQUEST_URL += processedQuery + "&maxResults=40" + "&key=" + API_KEY;

		// Get a reference to the ConnectivityManager to check state of network connectivity
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

		// Get details on the currently active default data network
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

		// If there is a network connection, fetch data
		if (networkInfo != null && networkInfo.isConnected()) {
			// Get a reference to the loader manager in order to interact with the loaders
			LoaderManager loaderManager = getLoaderManager();

			// Initialize the loader manager. Pass in the constant declared above as the ID of the
			// loader manager and pass in null for the bundle parameter. Finally, also pass in the
			// context of the application since this application implements the LoaderCallbacks interface
			loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, QueryResultsActivity.this);
		} else {
			// Otherwise, display error
			// First, hide loading indicator so error message will be visible
			mProgressSpinner.setVisibility(View.GONE);

			// Update empty state with no connection error message
			mEmptyStateView.setText(R.string.no_internet_connection);
		}
	}

	/**
	 * Create new loader object to load list of books as search query results for the user
	 */
	@Override
	public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
		return new BookLoader(QueryResultsActivity.this, REQUEST_URL);
	}

	/**
	 * The loader requests and parses information downloader from the internet on a background
	 * thread pool, keeping the UI thread unblocked
	 */
	@Override
	public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
		// Hide progress bar
		mProgressSpinner.setVisibility(View.GONE);

		// Set empty state text to display "No books to display."
		mEmptyStateView.setText(R.string.no_books);

		// Clear the adapter of previous data
		mAdapter.clear();

		// Add valid list of books to the adapter
		if (books != null && !books.isEmpty()) {
			mAdapter.addAll(books);
		}
	}

	/**
	 * This method is called when the loader is being destroyed by the loader manager
	 */
	@Override
	public void onLoaderReset(Loader<List<Book>> loader) {
		// Clear existing data on adapter as loader is reset
		mAdapter.clear();
	}
}

