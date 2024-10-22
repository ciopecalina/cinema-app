package com.example.bob.crawlertutorialapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ParseAdapterWatchlist extends RecyclerView.Adapter<ParseAdapterWatchlist.ViewHolder> {
    private ParseAdapterWatchlist adapter;
    public int position=-100;
    private ArrayList<Watchlist> parseItemsWatchlist;
    private Context context;

    public ParseAdapterWatchlist(ArrayList<Watchlist> parseItemsWatchlist, Context context) {
        this.context=context;
        this.parseItemsWatchlist = parseItemsWatchlist;
        this.adapter = this;
    }

    @NonNull
    @Override
    public ParseAdapterWatchlist.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item_watchlist,parent, false);

        return new ParseAdapterWatchlist.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParseAdapterWatchlist.ViewHolder holder, int position) {
        Watchlist parseItemWatchlist=parseItemsWatchlist.get(position);
        holder.tvTitlu.setText(parseItemWatchlist.getTitlu_film());
        holder.tvOra.setText(parseItemWatchlist.getOra_film());
        holder.tvData.setText(parseItemWatchlist.getData_film());
        holder.tvPost.setText(parseItemWatchlist.getPostTv_film());
    }

    @Override
    public int getItemCount() {
        return parseItemsWatchlist.size();
    }

    public void setItemPosition(int pos) {
        position=pos;
    }
    public int getItemPosition() {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvTitlu;
        TextView tvOra;
        TextView tvData;
        TextView tvPost;
        ImageView ivSterge;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSterge=itemView.findViewById(R.id.ivSterge);
            tvTitlu=itemView.findViewById(R.id.tvWatchlistTitlu);
            tvOra=itemView.findViewById(R.id.tvWatchlistOra);
            tvData=itemView.findViewById(R.id.tvWatchlistData);
            tvPost=itemView.findViewById(R.id.tvWatchlistPostTv);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(final View v) {
            final int position = getAdapterPosition();
            final Watchlist parseItemWatchlist=parseItemsWatchlist.get(position);

            final DatabaseHelper db= new DatabaseHelper(v.getContext());

            AlertDialog.Builder altdialog = new AlertDialog.Builder(v.getContext());
            altdialog.setMessage("Doriti sa eliminati programul din Watchlist?").setCancelable(false)
                    .setPositiveButton("DA", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                             db.deleteWatchlist(parseItemWatchlist.getTitlu_film());
                            db.deleteFilm(parseItemWatchlist.getTitlu_film());
                            parseItemsWatchlist.remove(position);
                           // setItemPosition(position);
                            adapter.notifyDataSetChanged();

                        }
                    })
                    .setNegativeButton("NU", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = altdialog.create();
            alert.setTitle("Stergere");
            alert.show();



        }
    }
}
