package com.example.projectmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;

public class AddingBeerActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    EditText editTextBeerName;
    EditText editTextKindName;
    EditText editTextPrice;
    EditText editTextPercentOfAlcohol;
    RatingBar ratingBarAboutBeer;
    RatingBar ratingBarSweetness;
    RatingBar ratingBarHopiness;
    Button buttonAddPhoto;
    Button buttonSaveToDatabase;
    Intent cameraIntent;
    ImageView imageView;
    MyDatabaseHelper myDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_beer);

        editTextBeerName = findViewById(R.id.editTextBeerName);
        editTextKindName = findViewById(R.id.editTextKindName);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextPercentOfAlcohol = findViewById(R.id.editTextPercentOfAlcohol);

        ratingBarAboutBeer = findViewById(R.id.ratingBarAboutBeer);
        ratingBarHopiness = findViewById(R.id.ratingBarHopiness);
        ratingBarSweetness = findViewById(R.id.ratingBarSweetness);


        buttonAddPhoto = findViewById(R.id.buttonAddPhoto);
        buttonSaveToDatabase = findViewById(R.id.buttonSaveToDatabase);

        imageView = findViewById(R.id.imageView);




        buttonAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,CAMERA_REQUEST);
            }
        });

        buttonSaveToDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB = new MyDatabaseHelper(AddingBeerActivity.this);
                /*myDB.addBeer(   editTextBeerName.getText().toString().trim(),
                                editTextKindName.getText().toString().trim(),
                                editTextPrice.getText().toString().trim(),
                                editTextPercentOfAlcohol.getText().toString().trim(),
                                ratingBarAboutBeer.getRating(),
                                ratingBarSweetness.getRating(),
                                ratingBarHopiness.getRating(),
                                null
                            );*/
                myDB.addBeer(null,null,null,null,0, 0,0,null);

            }
        });
    }

}