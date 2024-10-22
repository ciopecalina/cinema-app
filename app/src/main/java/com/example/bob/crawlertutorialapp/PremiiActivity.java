package com.example.bob.crawlertutorialapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PremiiActivity extends AppCompatActivity {
    private TextView tvOscarDescriere,tvGlobulDeAurDescriere,tvBaftaDescriere,tvCannesDescriere,tvBerlinDescriere,tvVenetiaDescriere,
            tvSundanceDescriere,tvGopoDescriere ;
    private String strOscarDescriere,strGlobulDeAurDescriere,strBaftaDescriere,strCannesDescriere,strBerlinDescriere,strVenetiaDescriere,
            strSundanceDescriere,strGopoDescriere ;
    private Button btnOscar, btnOscarIstoric,btnGlobulDeAur, btnGlobulDeAurIstoric,btnBafta, btnBaftaIstoric,
            btnCannes, btnBerlin,btnVenetia, btnGopo,btnSundance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premii);
        btnOscar=findViewById(R.id.btnOscar);
        btnOscarIstoric=findViewById(R.id.btnOscarIstoric);
        btnGlobulDeAur=findViewById(R.id.btnGlobulDeAur);
        btnGlobulDeAurIstoric=findViewById(R.id.btnGlobulDeAurIstoric);

        btnBafta=findViewById(R.id.btnBafta);
        btnBaftaIstoric=findViewById(R.id.btnBaftaIstoric);

        btnCannes=findViewById(R.id.btnCannes);
        btnBerlin=findViewById(R.id.btnBerlin);
        btnVenetia=findViewById(R.id.btnVenetia);
        btnGopo=findViewById(R.id.btnGopo);
        btnSundance=findViewById(R.id.btnSundance);

        tvGlobulDeAurDescriere=findViewById(R.id.tvGlobulDeAurDescriere);
        tvOscarDescriere=findViewById(R.id.tvOscarDescriere);
        tvBaftaDescriere=findViewById(R.id.tvBaftaDescriere);
        tvCannesDescriere=findViewById(R.id.tvCannesDescriere);
        tvBerlinDescriere=findViewById(R.id.tvBerlinDescriere);
        tvVenetiaDescriere=findViewById(R.id.tvVenetiaDescriere);
        tvSundanceDescriere=findViewById(R.id.tvSundanceDescriere);
        tvGopoDescriere=findViewById(R.id.tvGopoDescriere);

        btnOscarIstoric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IstoricPremiiActivity.class);
                String url="https://ro.wikipedia.org/wiki/Premiile_Oscar";
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
        btnGlobulDeAurIstoric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IstoricPremiiActivity.class);
                String url="https://ro.wikipedia.org/wiki/Premiile_Globul_de_Aur";
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
        btnBaftaIstoric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IstoricPremiiActivity.class);
                String url="https://ro.wikipedia.org/wiki/British_Academy_of_Film_and_Television_Arts";
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
        btnOscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListaPremiiActivity.class);
                String url="https://www.cinemagia.ro/premii/oscar/";
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
        btnGlobulDeAur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListaPremiiActivity.class);
                String url="https://www.cinemagia.ro/premii/globul-de-aur/";
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
        btnBafta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListaPremiiActivity.class);
                String url="https://www.cinemagia.ro/premii/bafta/";
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
        btnCannes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListaPremiiActivity.class);
                String url="https://www.cinemagia.ro/premii/cannes/";
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
        btnBerlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListaPremiiActivity.class);
                String url="https://www.cinemagia.ro/premii/berlin/";
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
        btnVenetia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListaPremiiActivity.class);
                String url="https://www.cinemagia.ro/premii/venetia/";
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
        btnSundance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListaPremiiActivity.class);
                String url="https://www.cinemagia.ro/premii/sundance/";
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
        btnGopo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListaPremiiActivity.class);
                String url="https://www.cinemagia.ro/premii/gopo/";
                intent.putExtra("url",url);
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
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);

            tvOscarDescriere.setText(strOscarDescriere);
            tvGlobulDeAurDescriere.setText(strGlobulDeAurDescriere);
            tvBaftaDescriere.setText(strBaftaDescriere);
            tvCannesDescriere.setText(strCannesDescriere);
            tvBerlinDescriere.setText(strBerlinDescriere);
            tvVenetiaDescriere.setText(strVenetiaDescriere);
            tvSundanceDescriere.setText(strSundanceDescriere);
            tvGopoDescriere.setText(strGopoDescriere);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try{

                String url="https://www.cinemagia.ro/premii/";
                Document doc= Jsoup.connect(url).get();

                //descriere
                Elements data=doc.select("div.award-listing").select("div.clearfix").select("p");
                strOscarDescriere="\n".concat("         ");
                strOscarDescriere=strOscarDescriere.concat(data.select("p").get(0).text());

                strGlobulDeAurDescriere="\n".concat("         ");
                strGlobulDeAurDescriere=strGlobulDeAurDescriere.concat(data.select("p").get(1).text());

                strBaftaDescriere="\n".concat("         ");
                strBaftaDescriere=strBaftaDescriere.concat(data.select("p").get(2).text());

                strCannesDescriere="\n".concat("         ");
                strCannesDescriere=strCannesDescriere.concat(data.select("p").get(3).text());

                strBerlinDescriere="\n".concat("         ");
                strBerlinDescriere=strBerlinDescriere.concat(data.select("p").get(4).text());

                strVenetiaDescriere="\n".concat("         ");
                strVenetiaDescriere=strVenetiaDescriere.concat(data.select("p").get(5).text());

                strSundanceDescriere="\n".concat("         ");
                strSundanceDescriere=strSundanceDescriere.concat(data.select("p").get(6).text());

                strGopoDescriere="\n".concat("         ");
                strGopoDescriere=strGopoDescriere.concat(data.select("p").get(7).text());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
