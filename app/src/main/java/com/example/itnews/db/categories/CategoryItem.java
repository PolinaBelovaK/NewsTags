package com.example.itnews.db.categories;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class CategoryItem {

    public CategoryItem(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getKey() {
        return key;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @PrimaryKey(autoGenerate = true)
    private int key;

    private String categoryName;
}
