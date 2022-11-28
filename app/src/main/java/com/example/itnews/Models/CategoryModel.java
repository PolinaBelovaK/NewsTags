package com.example.itnews.Models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.itnews.db.categories.CategoryItem;
import com.example.itnews.db.categories.CategoryRepository;

import java.util.List;

public class CategoryModel extends AndroidViewModel {

    private CategoryRepository categoryRepository;
    private LiveData<List<CategoryItem>> allCategories;

    public CategoryModel(@NonNull Application application) {
        super(application);
        categoryRepository = new CategoryRepository(application);
        allCategories = categoryRepository.getAllCategories();
    }
    public void insert(CategoryItem categoryItem) {
        categoryRepository.insert(categoryItem);
    }
    public void update(CategoryItem categoryItem) {
        categoryRepository.update(categoryItem);
    }
    public void delete(CategoryItem categoryItem) {
        categoryRepository.delete(categoryItem);
    }
    public LiveData<List<CategoryItem>> getAllCategories() {
        return allCategories;
    }
}
