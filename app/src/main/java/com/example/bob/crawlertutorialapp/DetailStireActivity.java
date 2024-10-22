package com.example.bob.crawlertutorialapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DetailStireActivity extends AppCompatActivity {
    private ImageView ivDetailPosterStire;
    private TextView tvDetailTitluStire,tvDetailDescriereStire,tvDetailCopyrightStire,tvDetailIntroducereStire;
    private String strPoster,strDescriere, strIntroducere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_stire);

        ivDetailPosterStire=findViewById(R.id.ivDetailPosterStire);

        tvDetailDescriereStire=findViewById(R.id.tvDetailDescriereStire);
        tvDetailTitluStire=findViewById(R.id.tvDetailTitluStire);
        tvDetailIntroducereStire=findViewById(R.id.tvDetailIntroducereStire);
        tvDetailCopyrightStire=findViewById(R.id. tvDetailCopyrightStire);

        tvDetailTitluStire.setText(getIntent().getStringExtra("title"));
        tvDetailCopyrightStire.setText("Stire oferita de catre 2020 © www.filmnow.ro");

        Content content = new Content();
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
            Picasso.get().load(strPoster).into(ivDetailPosterStire);
            tvDetailDescriereStire.setText(strDescriere);
            tvDetailIntroducereStire.setText(strIntroducere);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try{

                String url=getIntent().getStringExtra("detailUrl");
                Document doc= Jsoup.connect(url).get();

                //POSTER
                Elements data=doc.select("div.flex.flex-stretch");
                data=data.select("div.col-8.col-md-7.col-sm-12").select("article.article.article-thumb");
                strPoster=data
                        .select("img")
                        .attr("src");

                //DESCRIERE + INTRODUCERE
                data=doc.select("div.page-content.article-page-content").select("p");
                int size =data.size();
                strIntroducere=("       ");
                StringBuilder builderDescriere= new StringBuilder();
                builderDescriere.append("       ");
                for(int i =2; i<(size-1); i++)
                {
                    String info =data.select("p").eq(i).text();
                    if(i==2){
                        strIntroducere=strIntroducere.concat(data.select("p").eq(i).text());
                    }else{
                        builderDescriere.append(info);
                        builderDescriere.append("\n");
                        builderDescriere.append("       ");
                    }
                }
                strDescriere=builderDescriere.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
