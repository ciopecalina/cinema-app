package com.example.bob.crawlertutorialapp;

import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.annotation.Documented;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private RecyclerView recycleView;
private ParseAdapter adapter;
private ArrayList<ParseItem> parseItems=new ArrayList<ParseItem>();
private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar=findViewById(R.id.progressBar);
        recycleView=findViewById(R.id.recycleView);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new ParseAdapter(parseItems, this);
        recycleView.setAdapter(adapter);

        Content content= new Content ();
        content.execute();

    }
    private class Content extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out));
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                String url="https://www.zilesinopti.ro/bucuresti/filme/";
                Document doc=Jsoup.connect(url).get();
                Elements data=doc.select("ul.row.thumbs-items").select("div.item-event");
                int size =data.size();
               // for(int i =0; i<size; i++){
                    String imgUrl=data.select("a.image-container")
                            .select("img")
                            .eq(0)
                            .attr("src");
                    String title =data.select("div.text").select("a[href]")
                            .select("h3")
                            .eq(0)
                            .text();
                    String detailUrl=data
                            .select("a.image-container")
                            .eq(0)
                            .attr("href");

                    imgUrl="https://www.zilesinopti.ro/media/13815020265e57caf47cac78.23032201.jpg/resize?w=750";
                    title="Urma";
                    detailUrl="https://www.zilesinopti.ro/evenimente/filme/53094/urma";

                    String imgUrl2="https://upload.wikimedia.org/wikipedia/en/1/1f/Dolittle_%282020_film_poster%29.png";
                    String title2="Dolittle";
                    String detailUrl2="";

                    String imgUrl3="https://upload.wikimedia.org/wikipedia/ro/thumb/0/09/Jumanji-_Aventur%C4%83_%C3%AEn_jungl%C4%83.jpg/260px-Jumanji-_Aventur%C4%83_%C3%AEn_jungl%C4%83.jpg";
                    String title3="Jumanji: Aventură în junglă";
                    String detailUrl3="";

                    String imgUrl4="https://upload.wikimedia.org/wikipedia/ro/8/88/1917_%28film_din_2019%29.jpg";
                    String title4="1917: Speranță și moarte";
                    String detailUrl4="";

                    String imgUrl5="https://upload.wikimedia.org/wikipedia/en/9/90/Bad_Boys_for_Life_poster.jpg";
                    String title5="Bad Boys for Life";
                    String detailUrl5="";

                    parseItems.add(new ParseItem(imgUrl, title, detailUrl));
                    parseItems.add(new ParseItem(imgUrl2, title2, detailUrl2));
                    parseItems.add(new ParseItem(imgUrl3, title3, detailUrl3));
                    parseItems.add(new ParseItem(imgUrl4, title4, detailUrl4));
                    parseItems.add(new ParseItem(imgUrl5, title5, detailUrl5));

                    Log.d("items","img: "+imgUrl+" , title: "+title);

             //   }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
