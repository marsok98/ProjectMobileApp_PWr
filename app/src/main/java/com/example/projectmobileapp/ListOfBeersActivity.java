package com.example.projectmobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ListOfBeersActivity extends AppCompatActivity {

    MyDatabaseHelper myDB;
    ArrayList<Integer> beer_id;
    ArrayList<String> name,kind,price,percentOfAlcohol;
    ArrayList<Float> rateAboutBeer,hopiness,sweetness;
    ArrayList<byte[]> image;
    RecyclerView recyclerView;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_beers);
        recyclerView = findViewById(R.id.recyclerView);

        myDB = new MyDatabaseHelper(ListOfBeersActivity.this);
        beer_id = new ArrayList<>();
        name = new ArrayList<>();
        kind = new ArrayList<>();
        price = new ArrayList<>();
        percentOfAlcohol = new ArrayList<>();

        rateAboutBeer = new ArrayList<>();
        hopiness = new ArrayList<>();
        sweetness = new ArrayList<>();
        image = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(ListOfBeersActivity.this,beer_id,name,kind,price,
                percentOfAlcohol,rateAboutBeer,hopiness,sweetness,image);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListOfBeersActivity.this));
        // = new CustomAdapter();
    }

    void storeDataInArrays()
    {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0)
        {
            Toast.makeText(this,"No data.",Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                beer_id.add(cursor.getInt(0));
                name.add(cursor.getString(1));
                kind.add(cursor.getString(2));
                price.add(cursor.getString(3));
                percentOfAlcohol.add(cursor.getString(4));
                rateAboutBeer.add(cursor.getFloat(5));
                hopiness.add(cursor.getFloat(6));
                sweetness.add(cursor.getFloat(7));
                image.add(cursor.getBlob(8));
            }
        }
    }
}