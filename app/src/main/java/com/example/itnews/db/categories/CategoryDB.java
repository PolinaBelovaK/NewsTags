package com.example.itnews.db.categories;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {CategoryItem.class}, version = 1)
public abstract class CategoryDB extends RoomDatabase {
    private static CategoryDB instance;
    public abstract CategoryDao categoryDao();
    public static synchronized CategoryDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CategoryDB.class, "categories")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };
    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private CategoryDao categoryDao;
        private PopulateDBAsyncTask(CategoryDB db) {
            categoryDao = db.categoryDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            categoryDao.insert(new CategoryItem("general"));
            categoryDao.insert(new CategoryItem("health"));
            categoryDao.insert(new CategoryItem("science"));
            return null;
        }
    }
}
