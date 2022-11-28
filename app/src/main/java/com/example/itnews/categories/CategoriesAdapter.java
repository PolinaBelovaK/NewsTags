package com.example.itnews.categories;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.itnews.R;
import com.example.itnews.db.categories.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private List<CategoryItem> categoryItems = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tag, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryItem currentItem = categoryItems.get(position);
        holder.button.setText(currentItem.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categoryItems.size();
    }

    public void setCategoryItems(List<CategoryItem> categoryItems) {
        this.categoryItems = categoryItems;
        notifyDataSetChanged();
    }

    public CategoryItem getCategoryAt(int position) {
        return categoryItems.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
           private TextView button;
           private boolean isClicked = false;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.itemTag);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(categoryItems.get(position));
                    isClicked = true;
                }
            });
        }
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
