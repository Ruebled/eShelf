package com.library.eshelf.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "books")
public class Book {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title;
    private String author;
    private String category;
    private String status;
    private long addedDate;
    private String coverUrl;
    private int progress;
    private float rating;
    private String notes;
    private Long lastReadDate;
    private boolean isSync;

    // Required by Room
    public Book() {
        this.status = "Reading";
        this.addedDate = System.currentTimeMillis();
        this.notes = "";
        this.progress = 0;
        this.rating = 0f;
        this.isSync = false;
    }

    // Constructor for Builder
    private Book(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.author = builder.author;
        this.category = builder.category;
        this.status = builder.status;
        this.addedDate = builder.addedDate;
        this.coverUrl = builder.coverUrl;
        this.progress = builder.progress;
        this.rating = builder.rating;
        this.notes = builder.notes;
        this.lastReadDate = builder.lastReadDate;
        this.isSync = builder.isSync;
    }

    // Getters and Setters required by Room
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public long getAddedDate() { return addedDate; }
    public void setAddedDate(long addedDate) { this.addedDate = addedDate; }
    
    public String getCoverUrl() { return coverUrl; }
    public void setCoverUrl(String coverUrl) { this.coverUrl = coverUrl; }
    
    public int getProgress() { return progress; }
    public void setProgress(int progress) { this.progress = progress; }
    
    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public Long getLastReadDate() { return lastReadDate; }
    public void setLastReadDate(Long lastReadDate) { this.lastReadDate = lastReadDate; }
    
    public boolean isSync() { return isSync; }
    public void setSync(boolean sync) { isSync = sync; }

    // Builder pattern for creating Book instances
    public static class Builder {
        private long id;
        private String title;
        private String author;
        private String category;
        private String status = "Reading";
        private long addedDate = System.currentTimeMillis();
        private String coverUrl;
        private int progress;
        private float rating;
        private String notes = "";
        private Long lastReadDate;
        private boolean isSync;

        public Builder(@NonNull String title, @NonNull String author, @NonNull String category) {
            this.title = title;
            this.author = author;
            this.category = category;
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder addedDate(long addedDate) {
            this.addedDate = addedDate;
            return this;
        }

        public Builder coverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
            return this;
        }

        public Builder progress(int progress) {
            this.progress = progress;
            return this;
        }

        public Builder rating(float rating) {
            this.rating = rating;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder lastReadDate(Long lastReadDate) {
            this.lastReadDate = lastReadDate;
            return this;
        }

        public Builder isSync(boolean isSync) {
            this.isSync = isSync;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
} 