package com.example.bob.crawlertutorialapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class DetailSerialActivity extends AppCompatActivity {

    private ImageView ivPoster,ivPoster2;
    private TextView tvTitlu, tvDetalii,tvGen,tvDescriere,tvIntroducere,tvAni,tvNota;
    private String strGen,strDetalii,strDescriere,strPoster,strIntroducere,strPoster2,strAni;

    RecyclerView rvSezoane;
    LinearLayoutManager layoutManager;
    RecyclerView.LayoutManager rvSezoaneLayoutManager;
    ParseAdapterSezon adapter;

    ArrayList<Sezon> listaSezoane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_serial);

        ivPoster=findViewById(R.id.ivDetailFilmPoster);
        ivPoster2=findViewById(R.id.ivDetailFilmPoster2);
        tvTitlu=findViewById(R.id.tvDetailFilmTitlu);
        tvDetalii=findViewById(R.id.tvDetailFilmDetalii);
        tvGen=findViewById(R.id.tvDetailFilmGen);
        tvDescriere=findViewById(R.id.tvDetailFilmDescriere);
        tvIntroducere=findViewById(R.id.tvDetailFilmIntroducere);
        tvAni=findViewById(R.id.tvDetailFilmAni);
        tvNota=findViewById(R.id.tvDetailFilmNota);

        rvSezoane=findViewById(R.id.rvSezoane);

        listaSezoane= new ArrayList<>();

        layoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        rvSezoaneLayoutManager= layoutManager;
        rvSezoane.setLayoutManager(rvSezoaneLayoutManager);


        adapter= new ParseAdapterSezon(this,listaSezoane);

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
            tvAni.setText(strAni);

            rvSezoane.setAdapter(adapter);

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try{

                String url=getIntent().getStringExtra("url");
                Document doc=Jsoup.connect(url).get();

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


                //SEZOANE

                ArrayList<String> listaLink= new ArrayList<>();
                ArrayList<String> listaNr= new ArrayList<>();

                data=doc.select("div.seasons-and-year-nav").select("a[href*=sn]");
                int size4=data.size();

                for(int i =0; i<(size4); i++){
                    listaLink.add(data.eq(i).select("a")
                            .attr("abs:href"));
                    listaNr.add(data.eq(i).select("a").text());

                }
                for(int i =0; i<listaNr.size(); i++){
                   listaSezoane.add(new Sezon(listaNr.get(i),listaLink.get(i)));
                }

                data=doc.select("div.seasons-and-year-nav").select("a[href*=sn]");
                int size5=data.size();

                for(int i =0; i<(size5); i++){
                    listaLink.add(data.eq(i).select("a")
                            .attr("abs:href"));
                    listaNr.add(data.eq(i).select("a").text());

                }

                data=doc.select("div.seasons-and-year-nav").select("a[href*=yr]");
                int size6=data.size();
                StringBuilder builderAni= new StringBuilder();
                for(int i =0; i<(size6); i++){
                    builderAni.append(data.eq(i).select("a").text());
                    builderAni.append(", ");
                }
                builderAni.setLength(builderAni.length() - 2);
                strAni=builderAni.toString();
//ACTORI

//
//                data=doc.select("table.cast_list").select("tr");
//                int size7 =data.size();
//
//                for(int i =1; i<(size7); i=i+2) {
//                    Log.e("index ---- ",String.valueOf(i));
//                    String strPoster = data.select("td.primary_photo")
//                            .select("img")
//                            .eq(i)
//                            .attr("src");
//
//                    String strNume = data.eq(i).select("a").get(1)
//                            .text();
//                   String strPersonaj =  data.eq(i).select("a").get(2)
//                           .text();
//                    parseItemsActor.add(new Actor(strNume, strPersonaj, strPoster));
//
//                  // Log.e("nume: ",strNume);
//                }
//                Log.e("size7 ",String.valueOf(size7));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
