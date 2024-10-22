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

public class ParseAdapterTop extends RecyclerView.Adapter<ParseAdapterTop.ViewHolder> {

    private ArrayList<ParseItemTop> parseItemsTop;
    private Context context;

    public ParseAdapterTop(ArrayList<ParseItemTop> parseItemsTop, Context context) {
        this.context=context;
        this.parseItemsTop = parseItemsTop;
    }

    @NonNull
    @Override
    public ParseAdapterTop.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item_top,parent, false);

        return new ParseAdapterTop.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseAdapterTop.ViewHolder holder, int position) {
        ParseItemTop parseItemTop=parseItemsTop.get(position);
        holder.tvNota.setText(parseItemTop.getNota());
        holder.tvTitlu.setText(parseItemTop.getTitlu());
        holder.tvNr.setText(parseItemTop.getNr());
        Picasso.get().load(parseItemTop.getPoster()).into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return parseItemsTop.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView ivPoster;
        TextView tvTitlu;
        TextView tvNota;
        TextView tvNr;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster=itemView.findViewById(R.id.ivTopFilmePoster);
            tvTitlu=itemView.findViewById(R.id.tvTopFilmeTitlu);
            tvNota=itemView.findViewById(R.id.tvTopFilmeNota);
            tvNr=itemView.findViewById(R.id.tvTopNr);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ParseItemTop parseItemTop=parseItemsTop.get(position);

            Intent intent =new Intent(context, DetailSerialActivity.class);


            intent.putExtra("poster",parseItemTop.getPoster());
            intent.putExtra("url",parseItemTop.getUrl());
            intent.putExtra("titlu",parseItemTop.getTitlu());
            intent.putExtra("nr",parseItemTop.getNr());
            intent.putExtra("nota",parseItemTop.getNota());

            context.startActivity(intent);

        }
    }
}
