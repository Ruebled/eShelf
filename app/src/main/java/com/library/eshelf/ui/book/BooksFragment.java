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
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class BooksFragment extends Fragment {
    private FragmentBooksBinding binding;
    private BookViewModel bookViewModel;
    private BookAdapter bookAdapter;
    private static final int ADD_BOOK_REQUEST = 1;
    private static final int EDIT_BOOK_REQUEST = 2;

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
        bookAdapter.setOnBookActionListener(new BookAdapter.OnBookActionListener() {
            @Override
            public void onBookClick(Book book) {
                // Handle book click - maybe show details
            }

            @Override
            public void onEditBook(Book book) {
                Intent intent = new Intent(requireContext(), AddBookActivity.class);
                intent.putExtra("book_id", book.getId());
                intent.putExtra("book_title", book.getTitle());
                intent.putExtra("book_author", book.getAuthor());
                startActivityForResult(intent, EDIT_BOOK_REQUEST);
            }

            @Override
            public void onChangeStatus(Book book) {
                showChangeStatusDialog(book);
            }

            @Override
            public void onDeleteBook(Book book) {
                new MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Delete Book")
                    .setMessage("Are you sure you want to delete '" + book.getTitle() + "'?")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        bookViewModel.delete(book);
                        Toast.makeText(requireContext(), "Book deleted", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
            }
        });
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

    private void showChangeStatusDialog(Book book) {
        String[] statuses = {
            getString(R.string.tab_reading),
            getString(R.string.tab_completed),
            getString(R.string.tab_future),
            getString(R.string.tab_dropped)
        };

        new MaterialAlertDialogBuilder(requireContext())
            .setTitle("Change Status")
            .setItems(statuses, (dialog, which) -> {
                String newStatus = statuses[which];
                book.setStatus(newStatus);
                book.setCategory(newStatus);
                bookViewModel.update(book);
                
                int selectedTabPosition = binding.tabLayout.getSelectedTabPosition();
                TabLayout.Tab currentTab = binding.tabLayout.getTabAt(selectedTabPosition);
                if (currentTab != null) {
                    String currentCategory = currentTab.getText().toString();
                    if (!currentCategory.equals(newStatus)) {
                        bookViewModel.getBooksByCategory(currentCategory)
                            .observe(getViewLifecycleOwner(), books -> {
                                bookAdapter.submitList(books);
                            });
                    }
                }
                
                Toast.makeText(requireContext(), "Status updated", Toast.LENGTH_SHORT).show();
            })
            .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
} 