package com.example.bob.crawlertutorialapp;

import android.graphics.drawable.Drawable;
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
import java.util.ArrayList;
import java.util.List;

public class IstoricPremiiActivity extends AppCompatActivity {
    private ImageView ivIstoricPremii;
    private TextView tvIstoricPremiiTitlu,tvIstoric1,tvIstoric2,tvIstoricPremiiDescriere;
    private String strUrl,strIstoric1, strIstoric2,strDescriere,strTitlu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_istoric_premii);

        tvIstoricPremiiDescriere=findViewById(R.id.tvIstoricPremiiDescriere);
        tvIstoricPremiiTitlu=findViewById(R.id.tvIstoricPremiiTitlu);
        tvIstoric1=findViewById(R.id.tvIstoric1);
        tvIstoric2=findViewById(R.id.tvIstoric2);

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

            tvIstoricPremiiTitlu.setText(strTitlu);
            tvIstoric1.setText(strIstoric1);
            tvIstoric2.setText(strIstoric2);

            tvIstoricPremiiDescriere.setText(strDescriere);
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

                List<Integer> listOscar=new ArrayList<Integer>(){{ add(3);add(4);add(2); }};
                List<Integer> listGlob=new ArrayList<Integer>(){{ add(2);add(1);add(0); }};
                List<Integer> listBafta=new ArrayList<Integer>(){{ add(1);add(1);add(0); }};
                List<Integer> index=new ArrayList<>();

                int OK=1;
                if (url.contains("Oscar")){
                    index.add(listOscar.get(0));
                    index.add(listOscar.get(1));
                    index.add(listOscar.get(2));
                }else if (url.contains("Globul")){
                    index.add(listGlob.get(0));
                    index.add(listGlob.get(1));
                    index.add(listGlob.get(2));
                    OK=0;
                }else {
                    index.add(listBafta.get(0));
                    index.add(listBafta.get(1));
                    index.add(listBafta.get(2));
                }

                Elements data=doc.select("h1");
                strTitlu=data.select("h1").text();


                data=doc.select("table.infocaseta").select("tr").select("th");
                int size=data.size();
                StringBuilder builderInfo1= new StringBuilder();
                for(int i =0; i<(size-1); i++)
                {
                    if (i!=7){
                    builderInfo1.append(data.eq(i).text());
                    if ((i==0)&&(OK==1)){
                        builderInfo1.append(":\n");
                        builderInfo1.append("\n");
                    }else {
                        builderInfo1.append(":\n");
                    }
                }
                }

                strIstoric1=builderInfo1.toString();

                data=doc.select("table.infocaseta").select("tr").select("td");

                int size2=data.size();
                StringBuilder builderInfo2= new StringBuilder();
                for(int i =index.get(0); i<(size2-index.get(1)); i++)
                {
                    builderInfo2.append(data.eq(i).text());
                    builderInfo2.append("\n");

                }
                strIstoric2=builderInfo2.toString();

                data=doc.select("div.mw-body-content").select("p");
                int size3=data.size();
                StringBuilder builderDescriere= new StringBuilder();
                builderDescriere.append("       ");
                for(int i =index.get(2); i<size3-2; i++)
                {
                        builderDescriere.append(data.eq(i).text());
                        builderDescriere.append("\n");
                        builderDescriere.append("       ");
                }
                strDescriere=builderDescriere.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
