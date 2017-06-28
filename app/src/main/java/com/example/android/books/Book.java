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
	 * Average rating for the book
	 */
	private float mRating;

	/**
	 * Create book object
	 *
	 * @param title title of the book
	 * @param authors author of the book
	 */
	Book(String title, String authors, float rating) {
		this.mTitle = title;
		this.mAuthors = authors;
		this.mRating = rating;
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
	 * Return the author of the book
	 */
	String getAuthor() {
		// Return author of the book
		return mAuthors;
	}

	/**
	 * Return the average rating of the book
	 */
	public float getRating() {
		return mRating;
	}
}
