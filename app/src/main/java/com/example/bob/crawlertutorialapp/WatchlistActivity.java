package com.example.bob.crawlertutorialapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class WatchlistActivity extends AppCompatActivity {
    private String email;
    DatabaseHelper db= new DatabaseHelper(this);

    private RecyclerView rvListaWatchlist;
    private ParseAdapterWatchlist adapterWatchlist;
    public ArrayList<Watchlist> parseItemsWatchlist=new ArrayList<Watchlist>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        rvListaWatchlist=findViewById(R.id.rvWatchlistFilme);
        rvListaWatchlist.setHasFixedSize(true);
        rvListaWatchlist.setLayoutManager(new LinearLayoutManager(this));

        email =getIntent().getStringExtra("email");
        Watchlist w ;
        Cursor c = db.getWatchlist(email);
        if (c.moveToFirst()){
            do {
                w = new Watchlist();

                w.setTitlu_film(c.getString(0));
                w.setOra_film(c.getString(1));
                w.setData_film(c.getString(2));

                parseItemsWatchlist.add(w);

            } while(c.moveToNext());
        }
        c.close();

        for(int i=0; i<parseItemsWatchlist.size(); i++)
        {   String titlu=parseItemsWatchlist.get(i).getTitlu_film();
            String ora=parseItemsWatchlist.get(i).getOra_film();
            String data=parseItemsWatchlist.get(i).getData_film();

            Cursor crs = db.getPostTv(titlu, ora,  data);
            crs.moveToFirst();
            String post= crs.getString(0);
            parseItemsWatchlist.get(i).setPostTv_film(post);


        }

        adapterWatchlist= new ParseAdapterWatchlist(parseItemsWatchlist, this);
        rvListaWatchlist.setAdapter(adapterWatchlist);

        adapterWatchlist.notifyDataSetChanged();

//        int pos= adapterWatchlist.getItemPosition();
//        if (pos>=0){
//            adapterWatchlist= new ParseAdapterWatchlist(parseItemsWatchlist, this);
//            rvListaWatchlist.setAdapter(adapterWatchlist);
//        }

    }

}
