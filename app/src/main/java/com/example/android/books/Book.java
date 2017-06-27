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

	/**
	 * Create a book object with just the title information
	 * @param mTitle title of the book
	 */
	Book(String mTitle) {
		this.mTitle = mTitle;
	}

	/**
	 * Return the title information of the book
	 * @return the title of the book
	 */
	String getTitle() {
		// Return book title
		return mTitle;
	}

	/**
	 * Returns the author of the book
	 */
	String getAuthor() {
		// Return author of the book
		return mAuthors;
	}
}
