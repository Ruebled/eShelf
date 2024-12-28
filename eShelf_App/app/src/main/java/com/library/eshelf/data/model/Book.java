package com.library.eshelf.data.model;

@Entity(tableName = "books")
public class Book {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title;
    private String author;
    private String category;
    private String status = "Reading"; // Reading, Future, Favorites, Dropped
    private long addedDate = System.currentTimeMillis();
    private String coverUrl;
    private int progress = 0; // Reading progress (0-100)
    private float rating = 0f; // User rating (0-5)
    private String notes = ""; // Quick notes about the book
    private Long lastReadDate;
    private boolean isSync = false; // Track if synced with Firebase

    // Constructor
    public Book(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
    }

    public Long getLastReadDate() {
        return lastReadDate;
    }

    public void setLastReadDate(Long lastReadDate) {
        this.lastReadDate = lastReadDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(long addedDate) {
        this.addedDate = addedDate;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isSync() {
        return isSync;
    }

    public void setSync(boolean sync) {
        isSync = sync;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    // ... add remaining getters and setters
} 