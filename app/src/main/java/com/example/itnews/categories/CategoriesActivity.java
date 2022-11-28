package com.example.itnews.categories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.itnews.MainActivity;
import com.example.itnews.Models.CategoryModel;
import com.example.itnews.R;
import com.example.itnews.db.categories.CategoryItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {
    private CategoryModel categoryModel;
RecyclerView recyclerView;
CategoriesAdapter adapterCat;
List<CategoryItem> categoryItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        //back button func START
        FloatingActionButton back = findViewById(R.id.backBtn);
        back.setOnClickListener(view -> {
            Intent backToMain = new Intent(CategoriesActivity.this, MainActivity.class);
            backToMain.putExtra("dataList", (Parcelable) categoryItems);
            startActivity(backToMain);
        });
        //back button func END

        //recycler view in activity START
        recyclerView = findViewById(R.id.recyclerTags);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(CategoriesActivity.this, 2));
        adapterCat = new CategoriesAdapter();
        recyclerView.setAdapter(adapterCat);
        adapterCat.setCategoryItems(categoryItems);
        //recycler view in activity END

        categoryModel = new ViewModelProvider(CategoriesActivity.this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(CategoryModel.class);
        categoryModel.getAllCategories().observe(CategoriesActivity.this, new Observer<List<CategoryItem>>() {
            @Override
            public void onChanged(List<CategoryItem> categoryItems) {
                adapterCat.setCategoryItems(categoryItems);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Dialog dialog = new Dialog(CategoriesActivity.this);
                dialog.setContentView(R.layout.access_delete);
                Button accept = dialog.findViewById(R.id.acceptBtn);
                Button cancel = dialog.findViewById(R.id.cancelBtn);
                accept.setOnClickListener(view -> {
                    categoryModel.delete(adapterCat.getCategoryAt(viewHolder.getAdapterPosition()));
                    dialog.dismiss();
                });
                cancel.setOnClickListener(view -> {
                    categoryModel.update(adapterCat.getCategoryAt(viewHolder.getAdapterPosition()));
                    dialog.dismiss();
                });
                dialog.show();
            }
        }).attachToRecyclerView(recyclerView);

        adapterCat.setOnClickListener(categoryItem -> {
            Dialog dialog = new Dialog(CategoriesActivity.this);
            dialog.setContentView(R.layout.add_lay);
            EditText editText = dialog.findViewById(R.id.editText);
            FloatingActionButton save = dialog.findViewById(R.id.saveFab);
            TextView textView = dialog.findViewById(R.id.tvAddLay);
            textView.setText(R.string.update);
            editText.setText(categoryItem.getCategoryName());

            save.setOnClickListener(view -> {
                String categoryName = editText.getText().toString();
                CategoryItem categoryItem1 = new CategoryItem(categoryName);
                categoryItem1.setKey(categoryItem.getKey());
                categoryModel.update(categoryItem1);
                dialog.dismiss();
            });
            dialog.show();
        });

        FloatingActionButton add = findViewById(R.id.addButton);
        add.setOnClickListener(view -> {
            Dialog dialog = new Dialog(CategoriesActivity.this);
            dialog.setContentView(R.layout.add_lay);
            EditText editText = dialog.findViewById(R.id.editText);
            FloatingActionButton save = dialog.findViewById(R.id.saveFab);

            save.setOnClickListener(view1 -> {
                String categoryName = editText.getText().toString();

                CategoryItem categoryItem = new CategoryItem(categoryName);
                categoryModel.insert(categoryItem);
                dialog.dismiss();
            });
            dialog.show();
        });
    }
}