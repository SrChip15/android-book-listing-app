package com.example.android.books;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

class BookLoader extends AsyncTaskLoader<List<Book>> {

	/** The url to query the API */
	private String mSearchUrl;

	/** Data from the API */
	private List<Book> mData;

	/**
	 * Create a loader object
	 *
	 * @param context the {@link Context} of the application
	 * @param url {@link String} search query
	 */
	BookLoader(Context context, String url) {
		super(context);
		mSearchUrl = url;
	}

	/** Explicitly making the loader make HTTP request and begin loading data from content provider */
	@Override
	protected void onStartLoading() {
		if (mData != null) {
			deliverResult(mData); // Use cached data
		} else {
			forceLoad();
		}
	}

	/**
	 * This method is called in a background thread and takes care of the heavy lifting generating
	 * new data from the API
	 */
	@Override
	public List<Book> loadInBackground() {
		// Check for valid string url
		if (mSearchUrl == null) {
			return null;
		}

		// Returns the list of books matching search criteria from Google books
		// after performing network request, parsing input stream, and extracting a list of books
		return QueryUtils.fetchBooks(mSearchUrl);
	}

	@Override
	public void deliverResult(List<Book> data) {
		mData = data; // Cache data
		super.deliverResult(data);
	}
}
