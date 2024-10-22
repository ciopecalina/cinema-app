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

public class ParseAdapter extends RecyclerView.Adapter<ParseAdapter.ViewHolder> {

private ArrayList<ParseItem> parseItems;
private Context context;

    public ParseAdapter(ArrayList<ParseItem> parseItems, Context context) {
        this.context=context;
        this.parseItems = parseItems;
    }

    @NonNull
    @Override
    public ParseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseAdapter.ViewHolder holder, int position) {
ParseItem parseItem=parseItems.get(position);
holder.textView.setText(parseItem.getTitle());
Picasso.get().load(parseItem.getImgUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
ImageView imageView;
TextView textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            textView=itemView.findViewById(R.id.textView);
itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ParseItem parseItem=parseItems.get(position);


            Intent intent =new Intent(context, DetailActivity.class);
            intent.putExtra("image",parseItem.getImgUrl());
            intent.putExtra("title",parseItem.getTitle());
            intent.putExtra("detailUrl",parseItem.getDetailUrl());

            context.startActivity(intent);

        }
    }
}
