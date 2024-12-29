package com.library.eshelf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.library.eshelf.databinding.ActivityAddBookBinding;
import com.library.eshelf.data.model.Book;

public class AddBookActivity extends AppCompatActivity {
    private ActivityAddBookBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonSave.setOnClickListener(v -> validateAndSaveBook());
    }

    private void validateAndSaveBook() {
        String title = binding.editTextTitle.getText().toString().trim();
        String author = binding.editTextAuthor.getText().toString().trim();

        if (!validateInput(title, author)) {
            return;
        }

        Book newBook = new Book.Builder(title, author, "Reading").build();
        Intent resultIntent = new Intent();
        resultIntent.putExtra("title", title);
        resultIntent.putExtra("author", author);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    private boolean validateInput(String title, String author) {
        boolean isValid = true;

        if (TextUtils.isEmpty(title)) {
            binding.editTextTitle.setError("Title is required");
            isValid = false;
        }

        if (TextUtils.isEmpty(author)) {
            binding.editTextAuthor.setError("Author is required");
            isValid = false;
        }

        return isValid;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
} 