package com.example.projectmobileapp;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//klasa CustomAdapter powstala z tego wzgledu, ze musielismy wyswietlac dane w recyclerView
//to wymaga zdefiniowania pomocniczej klasy


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    //definiujemy listy tych wartosci ktore dostaniemy z bazy danych
    Context context;
    ArrayList<Integer> beer_id;
    ArrayList<String> name,kind,price,percentOfAlcohol;
    ArrayList<Float> rateAboutBeer,hopiness,sweetness;
    ArrayList<byte[]> image;
    //konstruktor
    CustomAdapter(Context context,ArrayList<Integer> beer_id,ArrayList<String> name, ArrayList<String> kind, ArrayList<String> price,ArrayList<String> percentOfAlcohol,
                  ArrayList<Float> rateAboutBeer,ArrayList<Float> hopiness, ArrayList<Float> sweetness,  ArrayList<byte[]> image)
    {
        this.context = context;
        this.beer_id = beer_id;
        this.name = name;
        this.kind = kind;
        this.price = price;
        this.percentOfAlcohol = percentOfAlcohol;
        this.rateAboutBeer = rateAboutBeer;
        this.hopiness = hopiness;
        this.sweetness = sweetness;
        this.image = image;

    }

    //funkcja przepisana z dokumentacji
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.my_row,parent,false);

        return new MyViewHolder(view);
    }

    //wrzucanie na recyclerView danych
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(beer_id.get(position)));
        holder.name.setText(String.valueOf(name.get(position)));
        holder.kind.setText(String.valueOf(kind.get(position)));
        holder.price.setText(String.valueOf(price.get(position)));
        holder.percent.setText(String.valueOf(percentOfAlcohol.get(position)));
        holder.rate.setText(String.valueOf(rateAboutBeer.get(position)));
        holder.hopiness.setText(String.valueOf(hopiness.get(position)));
        holder.sweetness.setText(String.valueOf(sweetness.get(position)));
        if(image.get(position) != null)
        {
            //zabezpieczenie, gdyby nie bylo obrazka
            //tym razem z byte array na bitmape
            holder.image.setImageBitmap(BitmapFactory.decodeByteArray(image.get(position),0,image.get(position).length));
        }





    }

    @Override
    public int getItemCount() {
        return beer_id.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        //pobranie uchwytow do pol z pliku my_row.xml
        TextView id,name,kind,price,percent,rate,hopiness,sweetness;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.textViewId);
            name = itemView.findViewById(R.id.textViewName);
            kind = itemView.findViewById(R.id.textViewKind);
            price = itemView.findViewById(R.id.textViewPrice);
            percent = itemView.findViewById(R.id.textViewAlcohol);
            rate = itemView.findViewById(R.id.textViewRate);
            hopiness = itemView.findViewById(R.id.textViewHopiness);
            sweetness = itemView.findViewById(R.id.textViewSweetness);
            image = itemView.findViewById(R.id.imageView3);
        }
    }
}
