package com.example.bob.crawlertutorialapp;

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
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ListaEpisoadeActivity extends AppCompatActivity {
    private RecyclerView rvEpisoade;
    private ParseAdapterEpisod adapterEpisoade;
    private ArrayList<ParseItemEpisod> parseItemsEpisod=new ArrayList<ParseItemEpisod>();
    private ProgressBar pbEpisoade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_episoade);


        pbEpisoade=findViewById(R.id.pbListaEpisoade);
        rvEpisoade=findViewById(R.id.rvListaEpisoade);
        rvEpisoade.setHasFixedSize(true);
        rvEpisoade.setLayoutManager(new LinearLayoutManager(this));
        adapterEpisoade= new ParseAdapterEpisod(parseItemsEpisod, this);
        rvEpisoade.setAdapter(adapterEpisoade);

        Content content= new Content();
        content.execute();
    }
    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbEpisoade.setVisibility(View.VISIBLE);
            pbEpisoade.setAnimation(AnimationUtils.loadAnimation(ListaEpisoadeActivity.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pbEpisoade.setVisibility(View.GONE);
            pbEpisoade.setAnimation(AnimationUtils.loadAnimation(ListaEpisoadeActivity.this, android.R.anim.fade_out));
            adapterEpisoade.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{

                String url=getIntent().getStringExtra("link");

                Document doc= Jsoup.connect(url).get();
                Elements data=doc.select("div.list.detail.eplist");
                int size1 =data.select("div.list_item.even").size();
                int size2 =data.select("div.list_item.odd").size();

                for(int i =0; i<(size1+size2); i++){
                    String strNr=String.valueOf(i+1);

                    String strPoster=data.select("div.image")
                            .select("img")
                            .eq(i)
                            .first().absUrl("src");

                    String strTitlu = data.select("div.info").select("strong")
                            .eq(i)
                            .text();
                    String strNota = data.select("div.ipl-rating-star.small").select("span.ipl-rating-star__rating").eq(i)
                            .text();
                    String strDescriere=data
                            .select("div.item_description")
                            .eq(i)
                            .text();

                    parseItemsEpisod.add(new ParseItemEpisod(strNr, strPoster, strTitlu, strNota, strDescriere));
                    Log.d("nota:   ", strNota);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

