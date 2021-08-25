package com.example.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private String name;
    private int version;

    private String createBook = "create table book ( " +
            "id integer primary key autoincrement," +
            "author text," +
            "pages integer," +
            "name text )";

    private String createCategory = "create table category ( " +
            "id integer primary key autoincrement, " +
            "category_name text, " +
            "category_code integer )";


    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.name = name;
        this.context = context;
        this.version = version;
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(createBook);
        sqLiteDatabase.execSQL(createCategory);
        Toast.makeText(context, "Create succeed", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i <= 1){
            sqLiteDatabase.execSQL(createCategory);
        }

    }
}
