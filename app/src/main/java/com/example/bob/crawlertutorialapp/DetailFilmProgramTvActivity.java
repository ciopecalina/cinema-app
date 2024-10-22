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
import java.util.ArrayList;

public class DetailFilmProgramTvActivity extends AppCompatActivity {
    private ImageView ivPoster;
    private TextView tvTitluRo,tvTitluEngl, tvDetalii,tvGen,tvDescriere,tvNota,tvPost,tvProgram,tvProgTvPremiu;
    private String strGen,strDetalii,strDescriere,strPoster,strTitluRo, strTitluEngl,strNota,strPost,strProgram,strPremiu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film_program_tv);

        ivPoster=findViewById(R.id.ivDetailFilmPosterProgTv);

        tvTitluRo=findViewById(R.id.tvDetailFilmTitluRoProgTv);
        tvTitluEngl=findViewById(R.id.tvDetailFilmTitluEnglProgTv);
        tvDetalii=findViewById(R.id.tvDetailFilmDetaliiProgTv);
        tvGen=findViewById(R.id.tvDetailFilmGenProgTv);
        tvDescriere=findViewById(R.id.tvDetailFilmDescriereProgTv);
        tvNota=findViewById(R.id.tvDetailFilmNotaProgTv);

        tvPost=findViewById(R.id.tvDetailFilmPostTv);
        tvProgram=findViewById(R.id.tvDetailFilmProgTv);
        tvProgTvPremiu=findViewById(R.id.tvProgTvPremiu);

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

            tvDetalii.setText(strDetalii);
            tvTitluRo.setText(strTitluRo);
            tvTitluEngl.setText("( "+strTitluEngl+" )");
            tvGen.setText(strGen);
            tvDescriere.setText(strDescriere);
            tvNota.setText(strNota);
            tvProgTvPremiu.setText(strPremiu);
            tvPost.setText(strPost);
            tvProgram.setText(strProgram);

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

                Elements data=doc.select("div.layout_6_content.clearfix");
                strPoster=data.select("div.mb15.clearfix").select("table").select("tr")
                        .select("img").first().absUrl("src");

                 strTitluEngl=data.select("h1.inline.pr2").select("a").first().text();
                 strTitluRo=data.select("h2").first().text();

                StringBuilder builderDescriere= new StringBuilder();
                data=doc.select("div.ml15");
                for(int  i=1;i<((data.select("div.ml15").select("p").size()));i++)
                {   builderDescriere.append("           ");
                    builderDescriere.append(data.select("p").eq(i).text());
                    builderDescriere.append("           ");
                }
                strDescriere=builderDescriere.toString();
               // strDescriere="          ";
               // strDescriere=strDescriere.concat(data.select("p").text());

                data=doc.select("div.mb15.clearfix");
                data=data.select("tr").select("td").eq(1).select("li");
                StringBuilder builderDetalii= new StringBuilder();
                StringBuilder builderGen= new StringBuilder();
                for(int  i=0;i<((data.select("li").eq(2).select("span").size())-2);i++)
                {
                    builderGen.append(data.select("li").eq(2).select("span").eq(i).text());
                    builderGen.append(" ");
                }


                builderDetalii.append(data.select("li").eq(0).text());
                builderDetalii.append("\n");
                builderDetalii.append(data.select("li").eq(1).text());
                builderDetalii.append("\n");

                strDetalii=builderDetalii.toString();
                strGen=builderGen.toString();

                data=doc.select("div.imdb-rating.mt5.fsize11");

                strNota=data.select("a").first().text();
                strNota=strNota.substring(6);
                Log.d("nota",strNota);

                data=doc.select("div.mt5.fsize11");
                StringBuilder builderPost= new StringBuilder();
                for(int  i=2;i<((data.select("a").size()));i++)
                {
                    builderPost.append(data.select("a").eq(i).text());
                    builderPost.append(", ");
                }
                builderPost.setLength(builderPost.length() - 2);
                strPost=builderPost.toString();

                strProgram=doc.select("div.box_padding").select("div.mt5.fsize11").html();

                strProgram = strProgram.replaceAll("<br>", "\n");
                strProgram = strProgram.replaceAll("<.*?>", "");
                strProgram = strProgram.substring(31);

                data=doc.select("div.box_padding").eq(1);
                strPremiu=data.select("span").text();
                if (strPremiu.length()<5){
                    strPremiu="Filmul nu are nicio nominalizare.";

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
