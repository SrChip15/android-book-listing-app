package com.example.android.books;

import android.content.AsyncTaskLoader;
import android.content.Context;

class BookLoader extends AsyncTaskLoader {

	/**
	 * The built-up search query following the API
	 */
	private String mSearchUrl;

	/**
	 * Create a loader object
	 * @param context the {@link Context} of the application
	 * @param url {@link String} search query
	 */
	public BookLoader(Context context, String url) {
		super(context);
		this.mSearchUrl = url;
	}

	/**
	 * Explicitly making the loader make HTTP request and begin loading data from content provider
	 */
	@Override
	protected void onStartLoading() {
		forceLoad();
	}

	/**
	 * This method is called in a background thread and takes care of the heavy lifting generating
	 * new data from scraping the API
	 */
	@Override
	public Object loadInBackground() {
		// Check for valid string url
		if (mSearchUrl == null) {
			return null;
		}

		// Returns the list of books matching search criteria from Google books
		// after performing network request, parsing input stream, and extracting a list of books
		return QueryUtils.fetchBooks(mSearchUrl);
	}
}
