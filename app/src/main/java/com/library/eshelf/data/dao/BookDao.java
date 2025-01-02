package com.library.eshelf.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.library.eshelf.data.model.Book;

import java.util.List;

@Dao
public interface BookDao {
    @Insert
    long insert(Book book);

    @Update
    void update(Book book);

    @Delete
    void delete(Book book);

    @Query("SELECT * FROM books ORDER BY addedDate DESC")
    LiveData<List<Book>> getAllBooks();

    @Query("SELECT * FROM books WHERE category = :category ORDER BY addedDate DESC")
    LiveData<List<Book>> getBooksByCategory(String category);
} 