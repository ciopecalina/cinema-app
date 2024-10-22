package com.example.bob.crawlertutorialapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ParseAdapterProgramTV extends RecyclerView.Adapter<ParseAdapterProgramTV.ViewHolder> {

    private Context context;
    private ArrayList<Film> list;

    private String email;

    ParseAdapterProgramTV(Context mContext, ArrayList<Film> mList, String emailIntent)
    {
        context=mContext;
        list=mList;
        email=emailIntent;
    }


    @NonNull
    @Override
    public ParseAdapterProgramTV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view =layoutInflater.inflate(R.layout.parse_item_program_tv,parent,false);
        ParseAdapterProgramTV.ViewHolder viewHolder=new ParseAdapterProgramTV.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ParseAdapterProgramTV.ViewHolder viewHolder, int i) {

        final Film item=list.get(i);

        TextView tvOra=viewHolder.tvOra;
        final TextView tvAdauga=viewHolder.tvAdauga;
        TextView tvTitlu=viewHolder.tvTitlu;
        TextView tvDescriere=viewHolder.tvDescriere;
        ImageView ivPoster=viewHolder.ivPoster;

        tvOra.setText(item.getOra());
        tvTitlu.setText(item.getTitlu());

        if(!(item.getDescriere().equals("NU")))
        {
            tvDescriere.setText(item.getDescriere());
        }

        Log.d("link   ", item.getLink());

        if(!(item.getPoster().equals("NU"))) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(900, 540);
            ivPoster.setLayoutParams(layoutParams);
            ivPoster.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.get().load(item.getPoster()).into(ivPoster);
        }

        viewHolder.tvTitlu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context,DetailFilmProgramTvActivity.class);
                intent.putExtra("link",item.getLink());
                context.startActivity(intent);
            }
        });

        viewHolder.tvAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseHelper db= new DatabaseHelper(v.getContext());
                db.insertFilm(item);

                Watchlist w= new Watchlist(email, item.getTitlu(), item.getOra(), item.getData());
                db.insertWatchlist(w);

                Toast.makeText(v.getContext(), "Adaugat in Watchlist", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOra;
        TextView tvAdauga;
        TextView tvTitlu;
        TextView tvDescriere;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOra=itemView.findViewById(R.id.tvProgramTVitemOra);
            tvAdauga=itemView.findViewById(R.id.tvAdaugaWatchlist);
            tvTitlu=itemView.findViewById(R.id.tvProgramTVitemTitlu);
            tvDescriere=itemView.findViewById(R.id.tvProgramTVitemDescriere);
            ivPoster=itemView.findViewById(R.id.ivProgramTVitemPoster);

        }
    }
}

