package com.example.bob.crawlertutorialapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class StiriActivity extends AppCompatActivity {

    private RecyclerView rvStiri;
    private ParseAdapterStiri adapterStiri;
    private ArrayList<ParseItemStiri> parseItemsStiri=new ArrayList<ParseItemStiri>();
    private ProgressBar pbStiri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stiri);

        pbStiri=findViewById(R.id.pbStiri);
        rvStiri=findViewById(R.id.rvStiri);
        rvStiri.setHasFixedSize(true);
        rvStiri.setLayoutManager(new LinearLayoutManager(this));
        adapterStiri= new ParseAdapterStiri(parseItemsStiri, this);
        rvStiri.setAdapter(adapterStiri);

        Content content= new Content();
        content.execute();

    }
    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbStiri.setVisibility(View.VISIBLE);
            pbStiri.setAnimation(AnimationUtils.loadAnimation(StiriActivity.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pbStiri.setVisibility(View.GONE);
            pbStiri.setAnimation(AnimationUtils.loadAnimation(StiriActivity.this, android.R.anim.fade_out));
            adapterStiri.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                String url="https://www.filmnow.ro/stiri";
                Document doc= Jsoup.connect(url).get();
                Elements data=doc.select("div.flex.flex-stretch").select("article.article.article-small");
                int size =data.size();
                for(int i =0; i<size; i++){
                    String imgUrlStire=data.select("figure.article-thumb")
                            .select("img")
                            .eq(i)
                            .attr("src");
                    String titleStire="         ";
                    titleStire =titleStire.concat(data.select("figcaption")
                            .select("h2")
                            .eq(i)
                            .text());
                    String detailUrlStire=data
                            .select("article.article-small").select("a")
                            .eq(i)
                            .attr("href");
                    String detailUrlComplet="https://www.filmnow.ro";
                    detailUrlComplet =detailUrlComplet.concat(detailUrlStire);

                    parseItemsStiri.add(new ParseItemStiri(imgUrlStire, titleStire, detailUrlComplet));
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
