package com.library.eshelf.ui.book;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.library.eshelf.databinding.FragmentBooksBinding;
import com.library.eshelf.data.model.Book;
import com.library.eshelf.ui.book.adapter.BookAdapter;

public class BooksFragment extends Fragment {
    private FragmentBooksBinding binding;
    private BookViewModel bookViewModel;
    private BookAdapter bookAdapter;
    private static final int ADD_BOOK_REQUEST = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBooksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView();
        setupViewModel();
        setupAddButton();
    }

    private void setupRecyclerView() {
        bookAdapter = new BookAdapter();
        binding.booksRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.booksRecyclerView.setAdapter(bookAdapter);
    }

    private void setupViewModel() {
        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        bookViewModel.getAllBooks().observe(getViewLifecycleOwner(), books -> {
            bookAdapter.submitList(books);
        });
    }

    private void setupAddButton() {
        binding.addBookButton.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), AddBookActivity.class);
            startActivityForResult(intent, ADD_BOOK_REQUEST);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_BOOK_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            String title = data.getStringExtra("title");
            String author = data.getStringExtra("author");

            Book newBook = new Book.Builder(title, author, "Reading").build();
            bookViewModel.insert(newBook);
            Toast.makeText(requireContext(), "Book added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
} 