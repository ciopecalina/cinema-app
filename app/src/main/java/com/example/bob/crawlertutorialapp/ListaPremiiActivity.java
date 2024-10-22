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

public class ListaPremiiActivity extends AppCompatActivity {
    private RecyclerView rvListaPremii;
    private ParseAdapterPremii adapterPremii;
    private ArrayList<ParseItemPremii> parseItemsPremii=new ArrayList<ParseItemPremii>();
    private ProgressBar pbListaPremii;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_premii);

        pbListaPremii=findViewById(R.id.pbListaPremii);
        rvListaPremii=findViewById(R.id.rvListaPremii);
        rvListaPremii.setHasFixedSize(true);
        rvListaPremii.setLayoutManager(new LinearLayoutManager(this));
        adapterPremii= new ParseAdapterPremii(parseItemsPremii, this);
        rvListaPremii.setAdapter(adapterPremii);

        Content content=new Content();
        content.execute();

    }
    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbListaPremii.setVisibility(View.VISIBLE);
            pbListaPremii.setAnimation(AnimationUtils.loadAnimation(ListaPremiiActivity.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pbListaPremii.setVisibility(View.GONE);
            pbListaPremii.setAnimation(AnimationUtils.loadAnimation(ListaPremiiActivity.this, android.R.anim.fade_out));
            adapterPremii.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                String url=getIntent().getStringExtra("url");
                Document doc= Jsoup.connect(url).get();

                Elements data=doc.select("div.award-page").select("div.box-winners");
                int size =data.size();

                for(int i =0; i<size; i++){
                    data=doc.select("div.award-page").select("div.box-winners");

                    String titlu=data.select("h2").eq(i).text();

                    data=doc.select("div.award-page").select("div.box-winners")
                            .eq(i).select("td.cat");
                    int size1=data.size();
                    String categorie="";
                    for (int j =0; j<size1; j++)
                    {
                        categorie=categorie.concat(data.select("td.cat").eq(j).text());
                        categorie=categorie.concat("\n");
                    }


                    data=doc.select("div.award-page").select("div.box-winners")
                            .eq(i).select("td.name");
                    int size2=data.size();
                    String nume="";
                    for (int j =0; j<size1; j++)
                    {   nume=nume.concat("•  ");
                        nume=nume.concat(data.select("td.name").eq(j).text());
                        nume=nume.concat("\n");

                    }


                    parseItemsPremii.add(new ParseItemPremii(titlu, categorie, nume));
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
