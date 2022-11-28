package com.example.itnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itnews.Models.ApiResponse;
import com.example.itnews.Models.CategoryModel;
import com.example.itnews.Models.Headlines;
import com.example.itnews.RVmainCategories.AdapterMainCategories;
import com.example.itnews.categories.CategoriesActivity;
import com.example.itnews.categories.CategoriesAdapter;
import com.example.itnews.db.categories.CategoryItem;
import com.example.itnews.mainAdapters.Adapter;
import com.example.itnews.mainUtils.OnFetchDataListener;
import com.example.itnews.mainUtils.RequestManager;
import com.example.itnews.mainUtils.SelectListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener {

    private CategoryModel categoryModel;
    RecyclerView rvMainNews, rvCategories;
    Adapter adapter;
    CategoriesAdapter adapterCat;
    ProgressDialog dialog;
    List<CategoryItem> categoryItems = new ArrayList<>();

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ожидание
        dialog = new ProgressDialog(this);
        dialog.setTitle("Поиск новостей...");
        dialog.show();

        //без фильтров по умолчанию
        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, "general", null);

        //переход в сортировку
        FloatingActionButton fab = findViewById(R.id.fabToSort);
        fab.setOnClickListener(view -> {
            Intent toSort = new Intent(MainActivity.this, CategoriesActivity.class);
            startActivity(toSort);
        });
        //recycler view с категориями
        rvCategories = findViewById(R.id.rvCategories);
        rvCategories.setHasFixedSize(false);
        rvCategories.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        adapterCat = new CategoriesAdapter();
        rvCategories.setAdapter(adapterCat);
        adapterCat.setCategoryItems(categoryItems);
        categoryModel = new ViewModelProvider(MainActivity.this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(CategoryModel.class);
        categoryModel.getAllCategories().observe(MainActivity.this, new Observer<List<CategoryItem>>() {
            @Override
            public void onChanged(List<CategoryItem> categoryItems) {
                adapterCat.setCategoryItems(categoryItems);
            }
        });
        adapterCat.setOnClickListener(categoryItem -> {
            String category = categoryItem.getCategoryName();
            dialog.setTitle("Поиск по запросу " + category.toLowerCase());
            dialog.show();
            RequestManager requestManager = new RequestManager(this);
            requestManager.getNewsHeadlines(listener, category, null);
            if (!category.equals("business") && !category.equals("entertainment") && !category.equals("general")
                    && !category.equals("health") && !category.equals("science") && !category.equals("sports")
                    && !category.equals("technology")) {
                Toast.makeText(this, "по теме " + category + " новостей не найдено", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private final OnFetchDataListener<ApiResponse> listener = new OnFetchDataListener<ApiResponse>() {
        @Override
        public void onFetchData(List<Headlines> list, String message) {
            showNews(list);
            dialog.dismiss();
        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, "Новостей не найдено", Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<Headlines> list) {
        rvMainNews = findViewById(R.id.recycler_main);
        rvMainNews.setHasFixedSize(true);
        rvMainNews.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new Adapter(this, list, this);
        rvMainNews.setAdapter(adapter);
    }

    @Override
    public void onNewsClick(Headlines headlines) {
        Uri data = Uri.parse(headlines.getUrl());
        startActivity(new Intent(Intent.ACTION_VIEW, data));
    }
}