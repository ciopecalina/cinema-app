package com.example.bob.crawlertutorialapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ProgramTvActivity extends AppCompatActivity {
    private String email;
    private ImageView ivPoster;
    private TextView tvTitlu,tvZiua1,tvZiua2,tvZiua3,tvZiua4,tvZiua5,tvZiua6,tvZiua7;
    private String strPoster,strTitlu,strZiua1,strZiua2,strZiua3,strZiua4,strZiua5,strZiua6,strZiua7,strLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_tv);

        email=getIntent().getStringExtra("email");

        ivPoster=findViewById(R.id.ivProgramTVlogo);
        tvTitlu=findViewById(R.id.tvProgramTVtitlu);
        tvZiua1=findViewById(R.id.tvProgramTVziua1);
        tvZiua2=findViewById(R.id.tvProgramTVziua2);
        tvZiua3=findViewById(R.id.tvProgramTVziua3);
        tvZiua4=findViewById(R.id.tvProgramTVziua4);
        tvZiua5=findViewById(R.id.tvProgramTVziua5);
        tvZiua6=findViewById(R.id.tvProgramTVziua6);
        tvZiua7=findViewById(R.id.tvProgramTVziua7);

        strTitlu="Program TV - ";
        strTitlu=strTitlu.concat(getIntent().getStringExtra("nume"));
        tvTitlu.setText(strTitlu);

        final String strPostTV=getIntent().getStringExtra("nume");

        tvZiua1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProgramTvCompletActivity.class);
                intent.putExtra("link",strLink);
                intent.putExtra("linkPoster",strPoster);
                intent.putExtra("titlu",strTitlu);
                intent.putExtra("ziua","0");
                intent.putExtra("data",strZiua1.substring(13));
                intent.putExtra("postTv",strPostTV);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        tvZiua2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProgramTvCompletActivity.class);
                intent.putExtra("link",strLink);
                intent.putExtra("linkPoster",strPoster);
                intent.putExtra("titlu",strTitlu);
                intent.putExtra("postTv",strPostTV);
                intent.putExtra("data",strZiua2.substring(13));
                intent.putExtra("ziua","1");
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        tvZiua3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProgramTvCompletActivity.class);
                intent.putExtra("link",strLink);
                intent.putExtra("linkPoster",strPoster);
                intent.putExtra("titlu",strTitlu);
                intent.putExtra("postTv",strPostTV);
                intent.putExtra("data",strZiua3.substring(13));
                intent.putExtra("ziua","2");
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        tvZiua4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProgramTvCompletActivity.class);
                intent.putExtra("link",strLink);
                intent.putExtra("linkPoster",strPoster);
                intent.putExtra("titlu",strTitlu);
                intent.putExtra("postTv",strPostTV);
                intent.putExtra("data",strZiua4.substring(13));
                intent.putExtra("ziua","3");
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        tvZiua5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProgramTvCompletActivity.class);
                intent.putExtra("link",strLink);
                intent.putExtra("linkPoster",strPoster);
                intent.putExtra("titlu",strTitlu);
                intent.putExtra("ziua","4");
                intent.putExtra("data",strZiua5.substring(13));
                intent.putExtra("postTv",strPostTV);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        tvZiua6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProgramTvCompletActivity.class);
                intent.putExtra("link",strLink);
                intent.putExtra("linkPoster",strPoster);
                intent.putExtra("titlu",strTitlu);
                intent.putExtra("postTv",strPostTV);
                intent.putExtra("ziua","5");
                intent.putExtra("data",strZiua6.substring(13));
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        tvZiua7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProgramTvCompletActivity.class);
                intent.putExtra("link",strLink);
                intent.putExtra("linkPoster",strPoster);
                intent.putExtra("titlu",strTitlu);
                intent.putExtra("postTv",strPostTV);
                intent.putExtra("ziua","6");
                intent.putExtra("data",strZiua7.substring(13));
                intent.putExtra("email",email);
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

            Picasso.get().load(strPoster).into(ivPoster);
            tvZiua1.setText(strZiua1.substring(13));
            tvZiua2.setText(strZiua2.substring(13));
            tvZiua3.setText(strZiua3.substring(13));
            tvZiua4.setText(strZiua4.substring(13));
            tvZiua5.setText(strZiua5.substring(13));
            tvZiua6.setText(strZiua6.substring(13));
            tvZiua7.setText(strZiua7.substring(13));
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try{

                String url=getIntent().getStringExtra("link");
                Document doc=Jsoup.connect(url).get();

                Elements data=doc.select("div.program_tv_complet");
                data=data.select("td.container_logo").eq(0);

                strPoster=data.select("td.container_logo").select("a").select("img").first().absUrl("src");

                strLink=data.select("td.container_logo").select("a").attr("abs:href");

                data=doc.select("div.navigation_container").select("ul.tab_5").select("li");
                strZiua1=data.eq(1).select("a[href][title]").attr("title");
                strZiua2=data.eq(2).select("a[href][title]").attr("title");
                strZiua3=data.eq(3).select("a[href][title]").attr("title");
                strZiua4=data.eq(4).select("a[href][title]").attr("title");
                strZiua5=data.eq(5).select("a[href][title]").attr("title");
                strZiua6=data.eq(6).select("a[href][title]").attr("title");
                strZiua7=data.eq(7).select("a[href][title]").attr("title");


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
