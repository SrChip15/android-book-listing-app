package com.example.android.books;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QueryResults extends AppCompatActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		// Initialize activity on main thread.
		// Bundle holds previous state when re-initialized
		super.onCreate(savedInstanceState);

		// Inflate the activity's UI
		setContentView(R.layout.list_of_books);

		// Hook the recycler view
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

		// Set fixed size true and optimize recycler view performance
		// The data container has fixed number of attractions and not infinite list
		recyclerView.setHasFixedSize(true);

		// Connect the {@link RecyclerView} widget to the vertical linear layout
		recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

		// Add sample data
		List<String> listOfBooks = new ArrayList<>();
		listOfBooks.add("Tell Me Why");
		listOfBooks.add("The Adventures of Tintin");
		listOfBooks.add("Famous Five");
		listOfBooks.add("Perry Mason");
		listOfBooks.add("If Tomorrow Comes");
		listOfBooks.add("The Godfather");
		listOfBooks.add("Angels and Demons");
		listOfBooks.add("Da Vinci Code");
		listOfBooks.add("The Hobbit");

		// Initialize the adapter with the sample data
		BookAdapter adapter = new BookAdapter(listOfBooks);

		// Attach adapter to the {@link RecyclerView} widget which is connected to a layout manager
		recyclerView.setAdapter(adapter);
	}
}
