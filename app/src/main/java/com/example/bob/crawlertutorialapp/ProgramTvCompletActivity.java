package com.example.bob.crawlertutorialapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ProgramTvCompletActivity extends AppCompatActivity {
    private String email;
    String strPostTv, strData;
    private ImageView ivPoster;
    private TextView tvTitlu,tvDescriere;
    private String strDescriere;

    RecyclerView rv;
    LinearLayoutManager layoutManager;
    RecyclerView.LayoutManager rvLayoutManager;
    ParseAdapterProgramTV adapter;

    ArrayList<Film> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_tv_complet);

        email=getIntent().getStringExtra("email");

        ivPoster=findViewById(R.id.ivProgramTvCompletLogo);
        tvTitlu=findViewById(R.id.tvProgramTvCompletTitlu);
        tvDescriere=findViewById(R.id.tvProgramTvCompletDescriere);

        tvTitlu.setText(getIntent().getStringExtra("titlu"));
        Picasso.get().load(getIntent().getStringExtra("linkPoster")).into(ivPoster);

        rv=findViewById(R.id.rvProgramTvComplet);
        lista= new ArrayList<>();
        layoutManager=new LinearLayoutManager(this);
        rvLayoutManager= layoutManager;
        rv.setLayoutManager(rvLayoutManager);
        adapter= new ParseAdapterProgramTV(this,lista, email);

        strPostTv=getIntent().getStringExtra("postTv");
        strData=getIntent().getStringExtra("data");



        Content content= new Content();
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
            tvDescriere.setText(strDescriere);

            rv.setAdapter(adapter);
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

                int nrZi=Integer.parseInt(getIntent().getStringExtra("ziua"));

                Elements data=doc.select("td.container_events").eq(nrZi);

                data= data.select("tr");

                     for(int i =0; i<data.size(); i++) {

                         String strOra=data.eq(i).select("td.ora").select("div").first().text() ;

                         String strTitlu=data.eq(i).select("td.event").select("div.title").text() ;

                         String strLink="NU";
                         if(data.eq(i).select("td.event").select("div.title").select("a").attr("abs:href").length()>0)
                         {
                            strLink=data.eq(i).select("td.event").select("div.title").select("a").attr("abs:href") ;
                         }

                         String strDesc="NU";
                         if( data.eq(i).select("td.event").select("span.small").text().length()>0 )
                         {
                             strDesc=data.eq(i).select("td.event").select("span.small").text() ;
                         }

                         String strPoster;
                         try {
                             strPoster = data.eq(i).select("td.event").select("div.thumb").select("a")
                                     .select("img").first().absUrl("src");
                         } catch (NullPointerException e) {
                             strPoster="NU";
                         }

                         lista.add(new Film(strOra, strTitlu, strDesc, strLink, strPoster, strData, strPostTv));

                }
                     data=doc.select("td.container_logo");
                     strDescriere=data.select("td.container_logo").eq(nrZi).text();

                Elements data2=doc.select("td.container_events").eq(nrZi+7);
                data2= data2.select("tr");

                for(int j =0; j<data2.size(); j++) {

                    String strOra2 = data2.eq(j).select("td.ora").select("div").first().text();

                    String strTitlu2 = data2.eq(j).select("td.event").select("div.title").text();

                    String strLink2 = "NU";
                    if (data2.eq(j).select("td.event").select("div.title").select("a").attr("abs:href").length() > 0) {
                        strLink2 = data2.eq(j).select("td.event").select("div.title").select("a").attr("abs:href");
                    }

                    String strDesc2 = "NU";
                    if (data2.eq(j).select("td.event").select("span.small").text().length() > 0) {
                        strDesc2 = data2.eq(j).select("td.event").select("span.small").text();
                    }

                    String strPoster2;
                    try {
                        strPoster2 = data2.eq(j).select("td.event").select("div.thumb").select("a")
                                .select("img").first().absUrl("src");
                    } catch (NullPointerException e) {
                        strPoster2 = "NU";
                    }

                    lista.add(new Film(strOra2, strTitlu2, strDesc2, strLink2, strPoster2, strData, strPostTv));
                }




  } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
