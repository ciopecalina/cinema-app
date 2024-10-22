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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ParseAdapterEpisod extends RecyclerView.Adapter<ParseAdapterEpisod.ViewHolder> {

    private ArrayList<ParseItemEpisod> parseItemsEpisod;
    private Context context;

    public ParseAdapterEpisod(ArrayList<ParseItemEpisod> parseItemsEpisod, Context context) {
        this.context=context;
        this.parseItemsEpisod = parseItemsEpisod;
    }

    @NonNull
    @Override
    public ParseAdapterEpisod.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item_episod,parent, false);

        return new ParseAdapterEpisod.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseAdapterEpisod.ViewHolder holder, int position) {
        ParseItemEpisod parseItemEpisod=parseItemsEpisod.get(position);
        holder.tvNr.setText(parseItemEpisod.getNr());
        holder.tvTitlu.setText(parseItemEpisod.getTitlu());
        holder.tvDescriere.setText(parseItemEpisod.getDescriere());
        holder.tvNota.setText(parseItemEpisod.getNota());
        Picasso.get().load(parseItemEpisod.getPoster()).into(holder.ivPoster);

    }

    @Override
    public int getItemCount() {
        return parseItemsEpisod.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView ivPoster;
        TextView tvNr;
        TextView tvTitlu;
        TextView tvNota;
        TextView tvDescriere;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNr=itemView.findViewById(R.id.tvEpisodNr);
            ivPoster=itemView.findViewById(R.id.ivEpisodPoster);
            tvTitlu=itemView.findViewById(R.id.tvEpisodTitlu);
            tvNota=itemView.findViewById(R.id.tvEpisodNota);
            tvDescriere=itemView.findViewById(R.id.tvEpisodDescriere);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ParseItemEpisod parseItemEpisod=parseItemsEpisod.get(position);


            Intent intent =new Intent(context, DetailStireActivity.class);

            intent.putExtra("poster",parseItemEpisod.getPoster());

            context.startActivity(intent);

        }
    }
}
