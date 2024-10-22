package com.example.bob.crawlertutorialapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CinematografDetailActivity extends AppCompatActivity {

    private TextView tvCinemaTitlu, tvCinemaDescriere, tvCinemaTraseuToate, tvCinemaTraseu;
    private TextView tvCinemaDetalii;
    private String strTitlu, strDescriere,strDetalii;
    private String link, denumire, latitudine, longitudine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinematograf_detail);

        tvCinemaTraseu=findViewById(R.id.tvCinemaTraseu);
        tvCinemaTraseuToate=findViewById(R.id.tvCinemaTraseuToate);
        tvCinemaTitlu=findViewById(R.id.tvCinemaTitlu);
        tvCinemaDescriere=findViewById(R.id.tvCinemaDescriere);
        tvCinemaDetalii=findViewById(R.id.tvCinemaDetalii);

        link =getIntent().getStringExtra("link");
        denumire =getIntent().getStringExtra("denumire");
        latitudine =getIntent().getStringExtra("latitudine");
        longitudine =getIntent().getStringExtra("longitudine");

        tvCinemaTraseu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LocatieCurentaActivity.class);
                intent.putExtra("tip", "cinema");
                intent.putExtra("denumire",denumire);
                intent.putExtra("latitudine", latitudine);
                intent.putExtra("longitudine",longitudine);

                startActivity(intent);
            }
        });
        tvCinemaTraseuToate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LocatieCurentaActivity.class);
                intent.putExtra("tip", "toate");
                startActivity(intent);
            }
        });

        Content content = new Content();
        content.execute();
    }

    private class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            tvCinemaTitlu.setText(strTitlu);
            tvCinemaDescriere.setText(strDescriere);
            tvCinemaDetalii.setText(strDetalii);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url=link;

                Document doc=Jsoup.connect(url).get();
                Elements data=doc.select("div.text");

                StringBuilder titlu= new StringBuilder();
                titlu.append(data.select("div.title-area").select("h1").text());
                strTitlu=titlu.toString();

                StringBuilder descriere= new StringBuilder();
                data=doc.select("div.text").select("div:not(.col-md-12)");

                for(int i =0; i<3; i++)
                {   descriere.append("              ");
                    descriere.append(data.select("p").eq(i).text());
                    descriere.append("\n");
                                    }
                strDescriere=descriere.toString();

                Document doc2=Jsoup.connect(url).get();
                Elements data2=doc2.select("div.row");
                StringBuilder detalii= new StringBuilder();
                data2=data2.select("div.row");

                  data2=data2.select("div.col-md-3").select("ul").select("li");
                 for (int i =0; i<data2.size(); i++){
                     if (i==(data2.size()-1)){
                         detalii.append("Website: ");
                         detalii.append(data2.eq(i).select("a").attr("href"));
                     }else
                     {
                         detalii.append(data2.eq(i).text());
                         detalii.append("\n");
                     }
                 }
                  strDetalii=detalii.toString();


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }}


