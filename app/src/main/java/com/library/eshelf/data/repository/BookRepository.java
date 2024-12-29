package com.library.eshelf.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.library.eshelf.data.AppDatabase;
import com.library.eshelf.data.dao.BookDao;
import com.library.eshelf.data.model.Book;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookRepository {
    private BookDao bookDao;
    private LiveData<List<Book>> allBooks;
    private ExecutorService executorService;

    public BookRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        bookDao = database.bookDao();
        allBooks = bookDao.getAllBooks();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void insert(Book book) {
        executorService.execute(() -> bookDao.insert(book));
    }

    public void update(Book book) {
        executorService.execute(() -> bookDao.update(book));
    }

    public void delete(Book book) {
        executorService.execute(() -> bookDao.delete(book));
    }

    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }

    public LiveData<List<Book>> getBooksByCategory(String category) {
        return bookDao.getBooksByCategory(category);
    }
} 