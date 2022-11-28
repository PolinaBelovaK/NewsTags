package com.example.itnews.db.categories;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;

public class CategoryRepository {
    private CategoryDao categoryDao;
    private LiveData<List<CategoryItem>> allCategories;

    public CategoryRepository(Application application) {
        CategoryDB categoryDB = CategoryDB.getInstance(application);
        categoryDao = categoryDB.categoryDao();
        allCategories = categoryDao.getAllCategories();
    }
    public void insert(CategoryItem categoryItem) {
        new InsertCategoryAsyncTask(categoryDao).execute(categoryItem);
    }
    public void update(CategoryItem categoryItem) {
        new UpdateCategoryAsyncTask(categoryDao).execute(categoryItem);
    }
    public void delete(CategoryItem categoryItem) {
        new DeleteCategoryAsyncTask(categoryDao).execute(categoryItem);
    }
    public LiveData<List<CategoryItem>> getAllCategories() {
        return allCategories;
    }

    private static class InsertCategoryAsyncTask extends AsyncTask<CategoryItem, Void, Void> {
        private CategoryDao categoryDao;
        private InsertCategoryAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }
        @Override
        protected Void doInBackground(CategoryItem... categoryItems) {
            categoryDao.insert(categoryItems[0]);
            return null;
        }
    }
    private static class UpdateCategoryAsyncTask extends AsyncTask<CategoryItem, Void, Void> {
        private CategoryDao categoryDao;
        private UpdateCategoryAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }
        @Override
        protected Void doInBackground(CategoryItem... categoryItems) {
            categoryDao.update(categoryItems[0]);
            return null;
        }
    }
    private static class DeleteCategoryAsyncTask extends AsyncTask<CategoryItem, Void, Void> {
        private CategoryDao categoryDao;
        private DeleteCategoryAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }
        @Override
        protected Void doInBackground(CategoryItem... categoryItems) {
            categoryDao.delete(categoryItems[0]);
            return null;
        }
    }
}
