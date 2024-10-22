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

public class ParseAdapterActor extends RecyclerView.Adapter<ParseAdapterActor.ViewHolder> {

private ArrayList<Actor> parseItemsActor;
private Context context;

public ParseAdapterActor(ArrayList<Actor> parseItemsActor, Context context) {
        this.context=context;
        this.parseItemsActor = parseItemsActor;
        }

@NonNull
@Override
public ParseAdapterActor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item_actor,parent, false);

        return new ParseAdapterActor.ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull ParseAdapterActor.ViewHolder holder, int position) {
        Actor parseItemActor=parseItemsActor.get(position);

        holder.tvNume.setText(parseItemActor.getNume());
        holder.tvPersonaj.setText(parseItemActor.getPersonaj());
        Picasso.get().load(parseItemActor.getPoster()).into(holder.ivPoster);

        }

@Override
public int getItemCount() {
        return parseItemsActor.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView ivPoster;
    TextView tvNume;
    TextView tvPersonaj;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        ivPoster=itemView.findViewById(R.id.ivActorPoster);
        tvNume=itemView.findViewById(R.id.tvActorNume);
        tvPersonaj=itemView.findViewById(R.id.tvActorPersonaj);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        Actor parseItemActor=parseItemsActor.get(position);

        Intent intent =new Intent(context, DetailStireActivity.class);

        intent.putExtra("nume",parseItemActor.getPoster());

        context.startActivity(intent);

    }
}
}

