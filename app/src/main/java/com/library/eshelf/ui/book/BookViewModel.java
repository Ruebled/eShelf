package com.library.eshelf.ui.book;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.library.eshelf.data.model.Book;
import com.library.eshelf.data.repository.BookRepository;

import java.util.List;

public class BookViewModel extends AndroidViewModel {
    private BookRepository repository;
    private LiveData<List<Book>> allBooks;

    public BookViewModel(@NonNull Application application) {
        super(application);
        repository = new BookRepository(application);
        allBooks = repository.getAllBooks();
    }

    public void insert(Book book) {
        repository.insert(book);
    }

    public void update(Book book) {
        repository.update(book);
    }

    public void delete(Book book) {
        repository.delete(book);
    }

    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }

    public LiveData<List<Book>> getBooksByCategory(String category) {
        return repository.getBooksByCategory(category);
    }
} 