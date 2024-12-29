package com.library.eshelf.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.library.eshelf.data.dao.BookDao;
import com.library.eshelf.data.model.Book;

@Database(entities = {Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    public abstract BookDao bookDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "book_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
} 