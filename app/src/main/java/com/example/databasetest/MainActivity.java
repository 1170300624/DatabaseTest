package com.example.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnCreateDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCreateDatabase = findViewById(R.id.create_DB);
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
        btnCreateDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.getWritableDatabase();
            }
        });
    }

    public void insertData(MyDatabaseHelper dbHelper){

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values1 = new ContentValues();
        ContentValues values2 = new ContentValues();

        values1.put("name", "The Da Vinci Code");
        values1.put("author", "Dan Brown");
        values1.put("pages", 454);
        values1.put("price", 16.96);

        values2.put("name", "The Lost Symbol");
        values2.put("author", "Dan Brown");
        values2.put("pages", 510);
        values2.put("price", 19.96);

        db.insert("book", null, values1);
        db.insert("book", null, values2);

    }

    public void updateData(MyDatabaseHelper dbHelper){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("price", 200);
        String[] name = new String[1];
        name[0] = "The Da Vinci Code";
        db.update("book", values, "name = ?", name);

    }

    public void deleteData(MyDatabaseHelper dbHelper){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] pages = new String[1];
        pages[0] = "500";
        db.delete("book", "pages > ?", pages);

    }

    @SuppressLint("Range")
    public void queryData(MyDatabaseHelper dbHelper){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("book", null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                float price = cursor.getFloat(cursor.getColumnIndex("price"));

                Log.d("MainActivity", "name : " + name);
                Log.d("MainActivity", "author : " + author);
                Log.d("MainActivity", "pages : " + pages);
                Log.d("MainActivity", "price : " + price);

            } while (cursor.moveToNext());
        }
        cursor.close();

    }

    public void replaceData(MyDatabaseHelper dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
           db.delete("book", null, null);
           ContentValues values = new ContentValues();
           values.put("name", "7");
           values.put("author", "Scarlet Horizon");
           values.put("pages", 1);
           values.put("price", 40);

           db.insert("book", null, values);
           db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }
}