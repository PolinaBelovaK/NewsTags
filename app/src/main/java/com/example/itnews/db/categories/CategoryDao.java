package com.example.itnews.db.categories;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insert(CategoryItem categoryItem);

    @Update
    void update(CategoryItem categoryItem);

    @Delete
    void delete(CategoryItem categoryItem);

    @Query("SELECT * FROM categories")
    LiveData<List<CategoryItem>> getAllCategories();
}
