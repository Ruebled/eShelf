package com.library.eshelf.ui.book.adapter;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.library.eshelf.databinding.ItemBookBinding;
import com.library.eshelf.data.model.Book;
import com.library.eshelf.R;

public class BookAdapter extends ListAdapter<Book, BookAdapter.BookViewHolder> {
    private OnBookActionListener listener;

    public interface OnBookActionListener {
        void onBookClick(Book book);
        void onEditBook(Book book);
        void onChangeStatus(Book book);
        void onDeleteBook(Book book);
    }

    public BookAdapter() {
        super(new DiffUtil.ItemCallback<Book>() {
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
        });
    }

    public void setOnBookActionListener(OnBookActionListener listener) {
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

            binding.getRoot().setOnClickListener(v -> {
                if (listener != null) {
                    listener.onBookClick(book);
                }
            });

            binding.moreButton.setOnClickListener(v -> showPopupMenu(v, book));
        }

        private void showPopupMenu(View view, Book book) {
            Context wrapper = new ContextThemeWrapper(view.getContext(), R.style.CustomPopupMenu);
            PopupMenu popup = new PopupMenu(wrapper, view, android.view.Gravity.END);
            popup.inflate(R.menu.book_item_menu);
            
            Menu menu = popup.getMenu();
            for (int i = 0; i < menu.size(); i++) {
                MenuItem item = menu.getItem(i);
                SpannableString spanString = new SpannableString(item.getTitle().toString());
                spanString.setSpan(new TextAppearanceSpan(view.getContext(), 
                    R.style.CustomPopupMenuTextAppearance), 0, spanString.length(), 0);
                item.setTitle(spanString);
            }
            
            popup.setOnMenuItemClickListener(item -> {
                if (listener == null) return false;
                
                int itemId = item.getItemId();
                if (itemId == R.id.action_edit) {
                    listener.onEditBook(book);
                    return true;
                } else if (itemId == R.id.action_change_status) {
                    listener.onChangeStatus(book);
                    return true;
                } else if (itemId == R.id.action_delete) {
                    listener.onDeleteBook(book);
                    return true;
                }
                return false;
            });
            
            popup.show();
        }
    }
} 