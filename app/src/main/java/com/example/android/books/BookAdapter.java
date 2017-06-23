package com.example.android.books;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class BookAdapter extends RecyclerView.Adapter<BookAdapter.CardViewHolder> {

	private final String LOG_TAG = BookAdapter.class.getSimpleName();

	/** A {@link List} of {@link String} to hold book titles */
	private List<String> mListOfBooks;

	private Context mContext;

	/**
	 * Create a new {@link BookAdapter} for the {@link RecyclerView}
	 *
	 * @param listOfBooks a {@link List<String>} of book titles
	 */
	BookAdapter(Context context, List<String> listOfBooks) {
		this.mListOfBooks = listOfBooks;
		this.mContext = context;
	}

	/**
	 * Create a fresh new {@link ViewGroup} for the {@link BookAdapter} to populate with data
	 * This method gets called when there are no {@link ViewGroup}s to recycle
	 *
	 * @param parent the activity's {@link ViewGroup} that holds the {@link RecyclerView}
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
	 * @param holder the recycled {@link CardViewHolder} that is ready to be reused with new data
	 * @param position the position in the {@link RecyclerView} at which the user has scrolled to
	 */
	@Override
	public void onBindViewHolder(CardViewHolder holder, int position) {
		// Get the current book that is being requested for display
		String book = mListOfBooks.get(position);

		// Embed the book to the {@link CardViewHolder}
		holder.bookTitle.setText(book);
	}

	/**
	 * This method is called by the {@link RecyclerView} to get the total number of data items
	 * present in the adapter that is to be presented via the UI to the user
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
	void addAll(List<String> data) {
		for (String title : data) {
			mListOfBooks.add(title);

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
		/** TextView for title of the book */
		TextView bookTitle;

		/**
		 * Create a new {@link ViewGroup}
		 *
		 * @param itemView an inflated custom {@link ViewGroup} to hold a single data item
		 */
		CardViewHolder(View itemView) {
			super(itemView);

			// Get a reference to the {@link TextView} to set title of the book
			bookTitle = (TextView) itemView.findViewById(R.id.book_title_text_view);
		}
	}
}
