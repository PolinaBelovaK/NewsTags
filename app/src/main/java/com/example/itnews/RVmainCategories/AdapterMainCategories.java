package com.example.itnews.RVmainCategories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itnews.R;
import com.example.itnews.db.categories.CategoryItem;

import java.util.List;

public class AdapterMainCategories extends RecyclerView.Adapter<CategoriesViewHolder> {

    private Context context;
    private List<CategoryItem> itemCategories;

    public AdapterMainCategories(Context context, List<CategoryItem> itemCategories) {
        this.context = context;
        this.itemCategories = itemCategories;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoriesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        holder.button.setText(itemCategories.get(position).getCategoryName());
    }

    @Override
    public int getItemCount() {
        return itemCategories.size();
    }
}
class CategoriesViewHolder extends RecyclerView.ViewHolder {
    Button button;
    public CategoriesViewHolder(@NonNull View itemView) {
        super(itemView);
        button = itemView.findViewById(R.id.itemTag);
    }
}
