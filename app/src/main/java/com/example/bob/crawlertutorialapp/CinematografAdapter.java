package com.example.bob.crawlertutorialapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CinematografAdapter extends RecyclerView.Adapter<CinematografAdapter.ViewHolder> {
private Context context;
private ArrayList<Cinematograf> list;

    CinematografAdapter(Context mContext, ArrayList<Cinematograf> mList)
    {
        context=mContext;
        list=mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view =layoutInflater.inflate(R.layout.rv_cinematograf_items,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final Cinematograf cinemaItem=list.get(i);

        ImageView image=viewHolder.item_image;
        TextView name, adress;

        name=viewHolder.item_name;
        adress=viewHolder.item_adress;

        image.setImageResource(cinemaItem.getImage());
        name.setText(cinemaItem.getName());
        adress.setText(cinemaItem.getAdress());

        viewHolder.item_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDetail= new Intent(context,CinematografDetailActivity.class);
                intentDetail.putExtra("denumire",cinemaItem.getName());
                intentDetail.putExtra("link",cinemaItem.getLink());
                intentDetail.putExtra("latitudine",cinemaItem.getLat());
                intentDetail.putExtra("longitudine",cinemaItem.getLongi());

                context.startActivity(intentDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_image;
        TextView item_name, item_adress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        item_image=itemView.findViewById(R.id.cinema_item_image);
        item_name=itemView.findViewById(R.id.cinema_item_name);
        item_adress=itemView.findViewById(R.id.cinema_item_adress);

        }
    }
}
