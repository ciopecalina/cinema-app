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
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class TopFilmeActivity extends AppCompatActivity {
    private RecyclerView rvTop;
    private ParseAdapterTop2 adapterTop;
    private ArrayList<ParseItemTop> parseItemsTop=new ArrayList<ParseItemTop>();
    private ProgressBar pbTop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_filme);

        pbTop=findViewById(R.id.pbTopFilme);
        rvTop=findViewById(R.id.rvTopFilme);
        rvTop.setHasFixedSize(true);
        rvTop.setLayoutManager(new LinearLayoutManager(this));
        adapterTop= new ParseAdapterTop2(parseItemsTop, this);
        rvTop.setAdapter(adapterTop);

        Content content= new Content();
        content.execute();


    }
    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbTop.setVisibility(View.VISIBLE);
            pbTop.setAnimation(AnimationUtils.loadAnimation(TopFilmeActivity.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pbTop.setVisibility(View.GONE);
            pbTop.setAnimation(AnimationUtils.loadAnimation(TopFilmeActivity.this, android.R.anim.fade_out));
            adapterTop.notifyDataSetChanged();
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
                Elements data=doc.select("tbody.lister-list").select("tr");

                for(int i =0; i<250; i++){

                    String strPoster=data.select("td.posterColumn")
                            .select("img")
                            .eq(i)
                            .attr("src");

                    String strTitlu = data.select("td.titleColumn").select("a")
                            .eq(i)
                            .text();
                    String strNota = data.select("td.ratingColumn.imdbRating")
                            .eq(i)
                            .text();
                    String strUrl=data
                            .select("td.titleColumn").select("a")
                            .eq(i)
                            .attr("abs:href");

                    String strNr=String.valueOf(i+1);
                    strNr=strNr.concat(".");


                    parseItemsTop.add(new ParseItemTop(strPoster,strTitlu,strNota, strUrl,strNr));

                    Log.d("ceva:   ",strUrl);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
