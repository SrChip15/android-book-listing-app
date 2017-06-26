package com.example.android.books;

import android.graphics.Bitmap;

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
	 * String url for detailed information of the volume
	 */
	private String mSelfLink;

	/**
	 * Front cover image for the book
	 */
	private Bitmap mCoverImage;

	/**
	 * Create book object
	 *
	 * @param title title of the book
	 * @param authors author of the book
	 */
	Book(String title, String authors, String selfLink) {
		this.mTitle = title;
		this.mAuthors = authors;
		this.mSelfLink = selfLink;
		mCoverImage = null;
	}

	/**
	 * Create a book object with just the title information
	 * @param mTitle title of the book
	 */
	Book(String mTitle) {
		this.mTitle = mTitle;
		mCoverImage = null;
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

	/**
	 * Returns the link for detailed information of the book volume
	 */
	String getSelfLink() {
		return mSelfLink;
	}

	/**
	 * Return the front cover image of the book
	 */
	Bitmap getCoverImage() {
		return mCoverImage;
	}

	/**
	 * Set the front cover image for the book
	 * @param mCoverImage the {@link Bitmap} image of the book's front cover
	 */
	void setCoverImage(Bitmap mCoverImage) {
		this.mCoverImage = mCoverImage;
	}
}
