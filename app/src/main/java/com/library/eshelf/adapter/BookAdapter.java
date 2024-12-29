package com.library.eshelf.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.library.eshelf.R;
import com.library.eshelf.data.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<Book> books = new ArrayList<>();
    private OnBookClickListener listener;

    public void setOnBookClickListener(OnBookClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book currentBook = books.get(position);
        holder.bookTitle.setText(currentBook.getTitle());
        holder.bookAuthor.setText(currentBook.getAuthor());
        holder.bookCategory.setText(currentBook.getCategory());

        // Set click listeners
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onBookClick(currentBook);
            }
        });

        holder.moreButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onMoreClick(currentBook, holder.moreButton);
            }
        });

        // Set status color or indicator
        switch (currentBook.getStatus()) {
            case "Reading":
                holder.bookCategory.setBackgroundResource(R.drawable.category_background);
                break;
            case "Completed":
                holder.bookCategory.setBackgroundResource(R.drawable.category_background_completed);
                break;
            // Add more cases as needed
        }

        // If you want to load book cover images (requires Glide library)
        /*
        if (currentBook.getCoverUrl() != null && !currentBook.getCoverUrl().isEmpty()) {
            Glide.with(holder.itemView.getContext())
                .load(currentBook.getCoverUrl())
                .placeholder(R.drawable.ic_book_placeholder)
                .into(holder.bookCover);
        } else {
            holder.bookCover.setImageResource(R.drawable.ic_book_placeholder);
        }
        */
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public void addBook(Book book) {
        books.add(book);
        notifyItemInserted(books.size() - 1);
    }

    public void removeBook(int position) {
        if (position >= 0 && position < books.size()) {
            books.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void updateBook(Book book) {
        int position = -1;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == book.getId()) {
                position = i;
                break;
            }
        }
        if (position != -1) {
            books.set(position, book);
            notifyItemChanged(position);
        }
    }

    public interface OnBookClickListener {
        void onBookClick(Book book);

        void onMoreClick(Book book, View view);
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        private final TextView bookTitle;
        private final TextView bookAuthor;
        private final TextView bookCategory;
        private final ImageView bookCover;
        private final ImageButton moreButton;

        public BookViewHolder(View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookAuthor = itemView.findViewById(R.id.bookAuthor);
            bookCategory = itemView.findViewById(R.id.bookCategory);
            bookCover = itemView.findViewById(R.id.bookCover);
            moreButton = itemView.findViewById(R.id.moreButton);
        }
    }
}