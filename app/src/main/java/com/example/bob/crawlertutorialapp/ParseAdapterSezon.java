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

public class ParseAdapterSezon extends RecyclerView.Adapter<ParseAdapterSezon.ViewHolder> {
    private Context context;
    private ArrayList<Sezon> list;

    ParseAdapterSezon(Context mContext, ArrayList<Sezon> mList)
    {
        context=mContext;
        list=mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view =layoutInflater.inflate(R.layout.parse_item_sezon,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final Sezon sezonItem=list.get(i);

        TextView item_nr=viewHolder.item_nr;
        item_nr.setText(sezonItem.getNr());

        //event click pe text , deschide activ detalii
        viewHolder.item_nr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context,ListaEpisoadeActivity.class);
                intent.putExtra("link",sezonItem.getLink());
                intent.putExtra("nr",sezonItem.getNr());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_nr;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_nr=itemView.findViewById(R.id.tvNrSezon);

        }
    }
}

