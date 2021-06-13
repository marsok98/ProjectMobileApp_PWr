package com.example.projectmobileapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Beers.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_KIND = "kind";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_ALCOHOL = "percentOfAlcohol";
    private static final String COLUMN_RATE_BEER = "mainRate";
    private static final String COLUMN_HOPINESS = "hopinessRate";
    private static final String COLUMN_SWEETNESS = "sweetnessRate";
    private static final String COLUMN_PHOTO = "photo";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        COLUMN_NAME + " TEXT, "+
                        COLUMN_KIND + " TEXT, "+
                        COLUMN_PRICE + " TEXT, "+
                        COLUMN_ALCOHOL + " TEXT, "+
                        COLUMN_RATE_BEER + " REAL, "+
                        COLUMN_HOPINESS + " REAL, "+
                        COLUMN_SWEETNESS + " REAL, "+
                        COLUMN_PHOTO + " BLOB); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);

    }
    void addBeer(String name,String kind, String price, String percentOfAlcohol, float rateAboutBeer,float hopiness, float sweetness,byte[] image)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_KIND,kind);
        cv.put(COLUMN_PRICE,price);
        cv.put(COLUMN_ALCOHOL,percentOfAlcohol);
        cv.put(COLUMN_RATE_BEER,rateAboutBeer);
        cv.put(COLUMN_HOPINESS,hopiness);
        cv.put(COLUMN_SWEETNESS,sweetness);
        cv.put(COLUMN_PHOTO,image);

        long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1)
        {
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context,"Added Succesfully",Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData()
    {
        String query = "SELECT * FROM " +TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query,null);
        }
        return  cursor;
    }

}
