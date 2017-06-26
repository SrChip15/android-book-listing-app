package com.example.android.books;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class BookAdapter extends RecyclerView.Adapter<BookAdapter.CardViewHolder> {

	private final String LOG_TAG = BookAdapter.class.getSimpleName();

	/**
	 * A {@link List} of {@link String} to hold book titles
	 */
	private List<Book> mListOfBooks;

	private Context mContext;

	/**
	 * Create a new {@link BookAdapter} for the {@link RecyclerView}
	 *
	 * @param listOfBooks a {@link List<String>} of book titles
	 */
	BookAdapter(Context context, List<Book> listOfBooks) {
		this.mListOfBooks = listOfBooks;
		this.mContext = context;
	}

	/**
	 * Create a fresh new {@link ViewGroup} for the {@link BookAdapter} to populate with data
	 * This method gets called when there are no {@link ViewGroup}s to recycle
	 *
	 * @param parent   the activity's {@link ViewGroup} that holds the {@link RecyclerView}
	 * @param viewType used when there are more than one {@link RecyclerView.ViewHolder} to display
	 * @return the {@link CardViewHolder} with the inflated list item's UI
	 */
	@Override
	public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(mContext).inflate(
				R.layout.book_card, parent, false);
		return new CardViewHolder(view);
	}

	/**
	 * Bind relevant data to the view that is being requested by the user
	 *
	 * @param holder   the recycled {@link CardViewHolder} that is ready to be reused with new data
	 * @param position the position in the {@link RecyclerView} at which the user has scrolled to
	 */
	@Override
	public void onBindViewHolder(CardViewHolder holder, int position) {
		// Get the current book that is being requested for display
		Book currentBook = mListOfBooks.get(position);

		// Set the book title to the correct view
		holder.bookTitle.setText(currentBook.getTitle());

		try {

			// Set the author of the book to the correct view
			String authors = currentBook.getAuthor();

			// Check whether the book author information or not
			if (!authors.isEmpty()) {
				// The book does have information on its author
				holder.bookAuthor.setText(authors);
			}

		} catch (NullPointerException e) {
			Log.v(LOG_TAG, "No information on authors");
		}

	}

	/**
	 * This method is called by the {@link RecyclerView} to get the total number of data items
	 * present in the adapter that is to be presented via the UI to the user
	 *
	 * @return the integer value for the total number of data items
	 */
	@Override
	public int getItemCount() {
		return mListOfBooks.size();
	}

	/**
	 * Clear all data from the adapter's data set
	 */
	void clear() {
		mListOfBooks = new ArrayList<>();
	}

	/**
	 * Add data items from a {@link List} to the adapter's data set
	 *
	 * @param data a {@link List} of book titles as {@link String}s
	 */
	void addAll(List<Book> data) {
		// Traverse the data list to add books to the adapter's data set
		for (int i = 0; i < data.size(); i++) {
			// Get the book at current index
			Book book = data.get(i);
			// Add the book to the data set
			mListOfBooks.add(book);

			// Notify the adapter of the change in the data set
			// so that it repopulates the view with the updated data set
			notifyDataSetChanged();
		}
	}

	/**
	 * Static class with the cached results of the findViewById() operations. This is how the
	 * adapter manipulates data that is presented to the user via the UI
	 */
	static class CardViewHolder extends RecyclerView.ViewHolder {
		/**
		 * TextView for title of the book
		 */
		TextView bookTitle;

		/**
		 * TextView for the author
		 */
		TextView bookAuthor;

		/**
		 * ImageView for the front cover of the book
		 */
		ImageView bookArt;

		/**
		 * Create a new {@link ViewGroup}
		 *
		 * @param itemView an inflated custom {@link ViewGroup} to hold a single data item
		 */
		CardViewHolder(View itemView) {
			super(itemView);

			// Get a reference to the {@link TextView} to set title of the book
			bookTitle = (TextView) itemView.findViewById(R.id.book_title_text_view);

			// Get reference to the {@link TextView} to set author of the book
			bookAuthor = (TextView) itemView.findViewById(R.id.author_text_view);

			// Get the reference to the {@link ImageView} to set the front cover art for the book
			bookArt = (ImageView) itemView.findViewById(R.id.front_cover_art_image_view);
		}
	}
}
