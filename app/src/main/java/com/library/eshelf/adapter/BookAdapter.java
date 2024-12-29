package com.library.eshelf.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.library.eshelf.databinding.ItemBookBinding;
import com.library.eshelf.data.model.Book;
import com.library.eshelf.R;

public class BookAdapter extends ListAdapter<Book, BookAdapter.BookViewHolder> {
    private OnBookClickListener listener;

    public BookAdapter() {
        super(new BookDiffCallback());
    }

    public void setOnBookClickListener(OnBookClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookBinding binding = ItemBookBinding.inflate(
            LayoutInflater.from(parent.getContext()), parent, false);
        return new BookViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        private final ItemBookBinding binding;

        BookViewHolder(ItemBookBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Book book) {
            binding.bookTitle.setText(book.getTitle());
            binding.bookAuthor.setText(book.getAuthor());
            binding.bookCategory.setText(book.getCategory());

            binding.getRoot().setOnClickListener(v -> {
                if (listener != null) {
                    listener.onBookClick(book);
                }
            });

            binding.moreButton.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onMoreClick(book, binding.moreButton);
                }
            });

            // Set status color
            int backgroundRes = book.getStatus().equals("Completed") 
                ? R.drawable.category_background_completed 
                : R.drawable.category_background;
            binding.bookCategory.setBackgroundResource(backgroundRes);
        }
    }

    private static class BookDiffCallback extends DiffUtil.ItemCallback<Book> {
        @Override
        public boolean areItemsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                   oldItem.getAuthor().equals(newItem.getAuthor()) &&
                   oldItem.getStatus().equals(newItem.getStatus());
        }
    }

    public interface OnBookClickListener {
        void onBookClick(Book book);
        void onMoreClick(Book book, View view);
    }
}