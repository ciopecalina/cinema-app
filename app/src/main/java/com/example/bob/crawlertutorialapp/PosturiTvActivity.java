package com.example.bob.crawlertutorialapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class PosturiTvActivity extends AppCompatActivity {

    RecyclerView rvPosturiTVromanesti;
    RecyclerView rvPosturiTVfilme;
    LinearLayoutManager layoutManager;
    LinearLayoutManager layoutManager2;
    RecyclerView.LayoutManager rvPosturiTVromanestiLayoutManager;
    RecyclerView.LayoutManager rvPosturiTVfilmeLayoutManager;
    ParseAdapterPostTV adapter;
    ParseAdapterPostTV adapter2;

    ArrayList<PostTV> listaPosturiTVromanesti;
    ArrayList<PostTV> listaPosturiTVfilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posturi_tv);

        String email =getIntent().getStringExtra("email");

        rvPosturiTVromanesti=findViewById(R.id.rvPosturiTVromanesti);
        listaPosturiTVromanesti= new ArrayList<>();

        rvPosturiTVfilme=findViewById(R.id.rvPosturiTVFilme);
        listaPosturiTVfilme= new ArrayList<>();

        layoutManager=new LinearLayoutManager(this);
        layoutManager2=new LinearLayoutManager(this);

        rvPosturiTVromanestiLayoutManager= layoutManager;
        rvPosturiTVromanesti.setLayoutManager(rvPosturiTVromanestiLayoutManager);

        rvPosturiTVfilmeLayoutManager= layoutManager2;
        rvPosturiTVfilme.setLayoutManager(rvPosturiTVfilmeLayoutManager);

        adapter= new ParseAdapterPostTV(this,listaPosturiTVromanesti, email);
        adapter2= new ParseAdapterPostTV(this,listaPosturiTVfilme, email);

        Content content= new Content();
        content.execute();

    }
    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            rvPosturiTVromanesti.setAdapter(adapter);
            rvPosturiTVfilme.setAdapter(adapter2);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try{

                String url="https://www.cinemagia.ro/program-tv/";
                Document doc= Jsoup.connect(url).get();
                Elements data=doc.select("div.col_left").select("div.col").select("ul");

                data= data.get(0).select("li.station-container");

                ArrayList<String> listaNume= new ArrayList<>();
                ArrayList<String> listaLink= new ArrayList<>();

                for(int i =0; i<data.size(); i++) {

                    String strNume = data.eq(i).select("a").get(0)
                            .text();
                    String strUrl = data.eq(i).select("a").get(0)
                            .attr("abs:href");
                    listaNume.add(strNume);
                    listaLink.add(strUrl);

                  //  Log.d("nume   ", strNume);
                }
                for(int i =0; i<listaNume.size(); i++){
                    listaPosturiTVromanesti.add(new PostTV(listaNume.get(i),listaLink.get(i)));
                }


                ///////////////////////
                data=doc.select("div.col_left").select("div.col").select("ul");

                data= data.get(3).select("li.station-container");

                ArrayList<String> listaNume2= new ArrayList<>();
                ArrayList<String> listaLink2= new ArrayList<>();

                for(int i =0; i<data.size(); i++) {

                    String strNume2 = data.eq(i).select("a").get(0)
                            .text();
                    String strUrl2 = data.eq(i).select("a").get(0)
                            .attr("abs:href");
                    listaNume2.add(strNume2);
                    listaLink2.add(strUrl2);

                    Log.d("nume   ", strNume2);
                }
                for(int i =0; i<listaNume2.size(); i++){
                    listaPosturiTVfilme.add(new PostTV(listaNume2.get(i),listaLink2.get(i)));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
