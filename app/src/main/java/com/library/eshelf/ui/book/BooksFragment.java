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

import com.google.android.material.tabs.TabLayout;
import com.library.eshelf.R;
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

        setupToolbar();
        setupTabs();
        setupRecyclerView();
        setupViewModel();
        setupAddButton();
    }

    private void setupToolbar() {
        // Optional: Setup toolbar menu or navigation if needed
        binding.toolbar.setTitle(R.string.title_books);
    }

    private void setupTabs() {
        String[] categories = {
            getString(R.string.tab_reading),
            getString(R.string.tab_completed),
            getString(R.string.tab_future),
            getString(R.string.tab_dropped),
        };

        for (String category : categories) {
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(category));
        }

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String category = tab.getText().toString();
                bookViewModel.getBooksByCategory(category).observe(getViewLifecycleOwner(), books -> {
                    bookAdapter.submitList(books);
                });
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void setupRecyclerView() {
        bookAdapter = new BookAdapter();
        binding.booksRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.booksRecyclerView.setAdapter(bookAdapter);
    }

    private void setupViewModel() {
        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);
        // Initially load "Reading" category
        bookViewModel.getBooksByCategory("Reading").observe(getViewLifecycleOwner(), books -> {
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