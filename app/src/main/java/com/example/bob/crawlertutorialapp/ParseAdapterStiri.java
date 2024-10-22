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

public class ParseAdapterStiri  extends RecyclerView.Adapter<ParseAdapterStiri.ViewHolder> {

    private ArrayList<ParseItemStiri> parseItemsStiri;
    private Context context;

    public ParseAdapterStiri(ArrayList<ParseItemStiri> parseItemsStiri, Context context) {
        this.context=context;
        this.parseItemsStiri = parseItemsStiri;
    }

    @NonNull
    @Override
    public ParseAdapterStiri.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item_stiri,parent, false);

        return new ParseAdapterStiri.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseAdapterStiri.ViewHolder holder, int position) {
        ParseItemStiri parseItemStiri=parseItemsStiri.get(position);
        holder.textView.setText(parseItemStiri.getTitle());
        Picasso.get().load(parseItemStiri.getImgUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return parseItemsStiri.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.ivStire);
            textView=itemView.findViewById(R.id.tvTitluStire);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ParseItemStiri parseItemStiri=parseItemsStiri.get(position);


            Intent intent =new Intent(context, DetailStireActivity.class);
            intent.putExtra("image",parseItemStiri.getImgUrl());
            intent.putExtra("title",parseItemStiri.getTitle());
            intent.putExtra("detailUrl",parseItemStiri.getDetailUrl());

            context.startActivity(intent);

        }
    }
}
