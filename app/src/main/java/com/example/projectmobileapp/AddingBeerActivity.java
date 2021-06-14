package com.example.projectmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;

import java.io.ByteArrayOutputStream;

public class AddingBeerActivity extends AppCompatActivity {
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
    byte[] blobFromCamera;
    //w taki sposob blob jest definiowany


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
                dispatchTakePictureIntent();
            }
        });

        buttonSaveToDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB = new MyDatabaseHelper(AddingBeerActivity.this);
                //dodajemy do bazy danych, na przycisk
                myDB.addBeer(   editTextBeerName.getText().toString().trim(),
                                editTextKindName.getText().toString().trim(),
                                editTextPrice.getText().toString().trim(),
                                editTextPercentOfAlcohol.getText().toString().trim(),
                                ratingBarAboutBeer.getRating(),
                                ratingBarSweetness.getRating(),
                                ratingBarHopiness.getRating(),
                                blobFromCamera
                            );
                //myDB.addBeer(null,null,null,null,0, 0,0,null);


            }
        });


    }
    // jest to przeciazenie ktore wywolania sie wtedy kiedy przyjdzie result z aktywnosci
    //gdy zgadzaja sie kody tzn ze to przyszlo z kamery, zapisujemy to do bloba
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            //tak jest opisane "data"
            //dostajemy bitmape a musimy zmienic na tablice bajtow
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG,0,byteArrayOutputStream);

            imageView.setImageBitmap(imageBitmap);
            blobFromCamera = byteArrayOutputStream.toByteArray();
        }
    }

    //metoda do brania zdjecia z aparatu
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }

}