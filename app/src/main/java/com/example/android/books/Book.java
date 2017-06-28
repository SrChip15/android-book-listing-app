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
	 * Retail price of the book
	 */
	private float mPrice;

	/**
	 * Create book object
	 *
	 * @param title   title of the book
	 * @param authors author of the book
	 * @param rating  average rating for the book
	 * @param price   retail price of the book
	 */
	Book(String title, String authors, float rating, float price) {
		this.mTitle = title;
		this.mAuthors = authors;
		this.mRating = rating;
		this.mPrice = price;
	}

	/**
	 * Return the title information of the book
	 *
	 * @return the title of the book
	 */
	String getTitle() {
		return mTitle;
	}

	/**
	 * Return the author of the book
	 */
	String getAuthor() {
		return mAuthors;
	}

	/**
	 * Return the average rating of the book
	 */
	float getRating() {
		return mRating;
	}

	/**
	 * Return the retail price of the book
	 */
	float getPrice() {
		return mPrice;
	}
}
