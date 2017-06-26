package com.example.android.books;

class Book {

	/**
	 * Title of the book
	 */
	private String mTitle;

	/**
	 * Author of the book
	 */
	private String mAuthors;

	/**
	 * Create book object
	 *
	 * @param title title of the book
	 * @param authors author of the book
	 */
	Book(String title, String authors) {
		this.mTitle = title;
		this.mAuthors = authors;
	}

	Book(String mTitle) {
		this.mTitle = mTitle;
	}

	String getTitle() {
		// Return book title
		return mTitle;
	}

	String getAuthor() {
		// Return author of the book
		return mAuthors;
	}
}
