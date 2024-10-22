package com.example.bob.crawlertutorialapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Vector;

public class DetailActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Vector<YoutubeVideo> youtubeVideos=new Vector<>();

    private ImageView imageView;
    private TextView tvTitlu, tvDetalii,tvGen,tvIntroducere,tvCinematografe,tvtrailer;
    private String strDetalii,strGen,strIntroducere,strDescriere,strCinematografe,strVideoUrl;
    private ReadMoreTextView tvDescriereReadMore;
    private Button btnProgram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        youtubeVideos.add(
                new YoutubeVideo( "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Js9nWMozzgk\" frameborder=\"0\" allowfullscreen></iframe>"));
        //  https://www.youtube.com/watch?v=Js9nWMozzgk


        YoutubeVideoAdapter videoAdapter=new YoutubeVideoAdapter(youtubeVideos);
        recyclerView.setAdapter(videoAdapter);

        imageView=findViewById(R.id.imageView);
        tvTitlu=findViewById(R.id.tvTitlu);
        tvDetalii=findViewById(R.id.tvDetalii);
        tvGen=findViewById(R.id.tvGen);
        tvIntroducere=findViewById(R.id.tvIntroducere);
        tvDescriereReadMore=findViewById(R.id.tvDescriereReadMore);
        btnProgram=findViewById(R.id.btnProgramCinema);

        Picasso.get().load(getIntent().getStringExtra("image")).into(imageView);

        tvTitlu.setText(getIntent().getStringExtra("title"));


        btnProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ProgramCinemaActivity.class);
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

            tvDetalii.setText(strDetalii);
            tvGen.setText(strGen);
            tvIntroducere.setText(strIntroducere);
            tvDescriereReadMore.setText(strDescriere);

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try{

                String url=getIntent().getStringExtra("detailUrl");
                Document doc=Jsoup.connect(url).get();

                Elements data=doc.select("div.text");

                //Detalii
                int size =data.size();
                data=doc.select("p"); // toate p
                StringBuilder builder1= new StringBuilder();
                for(int i =4; i<(size-2); i++)
                {
                    String info =data.select("p").eq(i).text();
                    builder1.append(info);
                    builder1.append("\n");
                }
                strDetalii=builder1.toString();
//GEN
               data=doc.select("div.text").select("div.title-area");
              strGen=data.select("a[href]").text();

//INTRODUCERE
                data=doc.select("div.text");
                StringBuilder builderIntroducere= new StringBuilder();
                builderIntroducere.append("     ");
                builderIntroducere.append(data.select("h2").text());
                strIntroducere=builderIntroducere.toString();

//DESCRIERE
                data=doc.select("div.text").select("p");
                StringBuilder builder2= new StringBuilder();
                int size2 =data.size();
                for(int i =0; i<(size-9); i++){
                    String paragraf =data.select("p")
                            .eq(i)
                            .text();
                    builder2.append("        ");
                    builder2.append(paragraf);
                    builder2.append("\n");
                }
                strDescriere=builder2.toString();
  //PROGRAM
                data=doc.select("ul.program").select("li");

                StringBuilder builder3= new StringBuilder();
                StringBuilder builderCinema= new StringBuilder();
                builderCinema.append("      ");
                builderCinema.append("Filmul ruleaza acum in urmatoarele cinematografe din Bucuresti:\n\n");
                builderCinema.append("      ");
                StringBuilder program;
                for(int i =0; i<(data.size()); i++)
                {
                    builderCinema.append(data.eq(i).select("a").text());
                    builderCinema.append(", ");
                    program= new StringBuilder();
                    for(int j=0;j<(data.eq(i).select("p").size());j++)
                    {
                        program.append(data.select("li").eq(i).select("p").eq(j).text());
                        program.append("\n");
                    }
                    builder3.append(builderCinema);
                    builder3.append("\n\n");
                    builder3.append(program);
                    builder3.append("\n");
                }
                strCinematografe=builderCinema.toString();
//TRAILER

                data=doc.select("div.text").select("p[style~=justify]");
                String videoUrl=data
                        .select("iframe")
                        .attr("src");
                StringBuilder builderUrl=new StringBuilder();
                builderUrl.append("<iframe width=\"100%\" height=\"100%\" src=\"");
                builderUrl.append(videoUrl);
                builderUrl.append("\" frameborder=\"0\" allowfullscreen></iframe>");
                strVideoUrl=builderUrl.toString();
         //       "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/7cTMbkH-08Y\" frameborder=\"0\" allowfullscreen></iframe>"));



            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
