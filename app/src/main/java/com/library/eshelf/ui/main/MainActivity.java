package com.library.eshelf.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.library.eshelf.R;
import com.library.eshelf.data.model.Book;
import com.library.eshelf.ui.auth.AuthActivity;
import com.library.eshelf.ui.book.AddBookActivity;
import com.library.eshelf.ui.book.BookViewModel;
import com.library.eshelf.ui.book.adapter.BookAdapter;

public class MainActivity extends AppCompatActivity {
    private BookViewModel bookViewModel;
    private BookAdapter bookAdapter;
    private static final int ADD_BOOK_REQUEST = 1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.booksRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookAdapter = new BookAdapter();
        recyclerView.setAdapter(bookAdapter);

        // Initialize ViewModel
        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        bookViewModel.getAllBooks().observe(this, books -> {
            bookAdapter.submitList(books);
        });

        // Setup add button
        findViewById(R.id.addBookButton).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
            startActivityForResult(intent, ADD_BOOK_REQUEST);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_BOOK_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            String title = data.getStringExtra("title");
            String author = data.getStringExtra("author");

            Book newBook = new Book.Builder(title, author, "Reading")
                    .build();
            
            bookViewModel.insert(newBook);
            Toast.makeText(this, "Book added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null) {
            // User is not signed in, redirect to AuthActivity
            startActivity(new Intent(this, AuthActivity.class));
            finish();
        }
        // else continue with main activity
    }
} 