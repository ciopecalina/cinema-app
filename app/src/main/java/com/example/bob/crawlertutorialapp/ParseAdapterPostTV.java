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

public class ParseAdapterPostTV extends RecyclerView.Adapter<ParseAdapterPostTV.ViewHolder> {
    private Context context;
    private ArrayList<PostTV> list;
    private String email;

    ParseAdapterPostTV(Context mContext, ArrayList<PostTV> mList, String emailIntent)
    {
        context=mContext;
        list=mList;
        email= emailIntent;
    }

    @NonNull
    @Override
    public ParseAdapterPostTV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view =layoutInflater.inflate(R.layout.parse_item_post_tv,parent,false);
        ParseAdapterPostTV.ViewHolder viewHolder=new ParseAdapterPostTV.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ParseAdapterPostTV.ViewHolder viewHolder, int i) {

        final PostTV postTvItem=list.get(i);

        TextView item_nume=viewHolder.item_nume;
        item_nume.setText(postTvItem.getNume());

        viewHolder.item_nume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context,ProgramTvActivity.class);
                intent.putExtra("email",email);
                intent.putExtra("nume",postTvItem.getNume());
                intent.putExtra("link",postTvItem.getLink());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_nume;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_nume=itemView.findViewById(R.id.tvPostTVNume);

        }
    }
}

