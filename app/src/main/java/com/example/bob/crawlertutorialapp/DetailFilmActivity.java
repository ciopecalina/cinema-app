package com.example.bob.crawlertutorialapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DetailFilmActivity extends AppCompatActivity {

    private ImageView ivPoster,ivPoster2;
    private TextView tvTitlu, tvDetalii,tvGen,tvDescriere,tvIntroducere,tvNota;
    private String strGen,strDetalii,strDescriere,strPoster,strIntroducere,strPoster2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        ivPoster=findViewById(R.id.ivDetailFilmPoster_2);
        ivPoster2=findViewById(R.id.ivDetailFilmPoster2_2);
        tvTitlu=findViewById(R.id.tvDetailFilmTitlu_2);
        tvDetalii=findViewById(R.id.tvDetailFilmDetalii_2);
        tvGen=findViewById(R.id.tvDetailFilmGen_2);
        tvDescriere=findViewById(R.id.tvDetailFilmDescriere_2);
        tvIntroducere=findViewById(R.id.tvDetailFilmIntroducere_2);
        tvNota=findViewById(R.id.tvDetailFilmNota_2);

        tvTitlu.setText(getIntent().getStringExtra("titlu"));
        tvNota.setText(getIntent().getStringExtra("nota"));

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
            Picasso.get().load(strPoster).into(ivPoster);
            Picasso.get().load(strPoster2).into(ivPoster2);
            tvDetalii.setText(strDetalii);
            tvGen.setText(strGen);
            tvDescriere.setText(strDescriere);
            tvIntroducere.setText(strIntroducere);

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

                Elements data=doc.select("div.heroic-overview");
//poster
                strPoster=data.select("div.poster").select("a")
                        .select("img").first().absUrl("src").toString();



                //Detalii
                int size =data.size();
                data=doc.select("div.credit_summary_item");
                int size2 =data.size();
                StringBuilder builderDetalii= new StringBuilder();

                for(int i =0; i<(size2); i++){
                    builderDetalii.append(data.eq(i).select("h4.inline").text());
                    builderDetalii.append("     ");
                    builderDetalii.append(data.eq(i).select("div.credit_summary_item").select("a[href*=/name/]").text());
                    builderDetalii.append("\n");

                }


                strDetalii=builderDetalii.toString();
//GEN
                data=doc.select("div.subtext").select("a");
                int size3 =data.size();
                StringBuilder builderGen= new StringBuilder();

                for(int i =1; i<(size3-1); i++){
                    builderGen.append(data.eq(i).text());
                    builderGen.append(" ");

                }
                strGen=builderGen.toString();

                //DESCRIERE
                data=doc.select("div.plot_summary_wrapper");
                data=data.select("div.summary_text");
                StringBuilder builderIntroducere= new StringBuilder();
                builderIntroducere.append("       ");
                builderIntroducere.append(data.text());
                builderIntroducere.append("\n ");
                strIntroducere=builderIntroducere.toString();

                StringBuilder builderDescriere= new StringBuilder();
                data=doc.select("div[id*=titleStoryLine]");
                builderDescriere.append("       ");
                builderDescriere.append(data.select("p").first().select("span").text());
                strDescriere=builderDescriere.toString();

                data=doc.select("div.slate");
//poster
                strPoster2=data.select("a").first()
                        .select("img").first().absUrl("src").toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
