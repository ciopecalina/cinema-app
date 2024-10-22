package com.example.bob.crawlertutorialapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ParseAdapterPremii extends RecyclerView.Adapter<ParseAdapterPremii.ViewHolder> {

    private ArrayList<ParseItemPremii> parseItemsPremii;
    private Context context;

    public ParseAdapterPremii(ArrayList<ParseItemPremii> parseItemsPremii, Context context) {
        this.context=context;
        this.parseItemsPremii = parseItemsPremii;
    }

    @NonNull
    @Override
    public ParseAdapterPremii.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item_premiu,parent, false);

        return new ParseAdapterPremii.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseAdapterPremii.ViewHolder holder, int position) {
        ParseItemPremii parseItemPremii=parseItemsPremii.get(position);
        holder.tvTitlu.setText(parseItemPremii.getTitlu());
        holder.tvNume.setText(parseItemPremii.getNume());
        holder.tvCategorie.setText(parseItemPremii.getCategorie());
    }

    @Override
    public int getItemCount() {
        return parseItemsPremii.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvTitlu;
        TextView tvCategorie;
        TextView tvNume;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitlu=itemView.findViewById(R.id.tvLPTitlu);
            tvCategorie=itemView.findViewById(R.id.tvLPCategorie);
            tvNume=itemView.findViewById(R.id.tvLPNume);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ParseItemPremii parseItemPremii=parseItemsPremii.get(position);


            Intent intent =new Intent(context, DetailStireActivity.class);
            intent.putExtra("titlu",parseItemPremii.getTitlu());
            intent.putExtra("categorie",parseItemPremii.getCategorie());
            intent.putExtra("nume",parseItemPremii.getNume());

            context.startActivity(intent);

        }
    }
}
