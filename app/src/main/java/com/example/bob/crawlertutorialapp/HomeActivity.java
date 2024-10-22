package com.example.bob.crawlertutorialapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    private String email;
    private TextView tvHeaderNume, tvHeaderEmail;
    DatabaseHelper db= new DatabaseHelper(this);
    private Button btn1, btn1_2;
    private Button btn2,btn3,btn4,btn5, btn6, btn7, btn8, btn9, btn10, btn11, btn12, btn13;
    private ImageView ivCinematografe,ivProgramCinema, ivProgramTv, ivWatchlist,ivPremii, ivStiri;
    private TextView tvTopFilmeBune, tvTopFilmePopulare, tvTopSerialeBune, tvTopSerialePopulare, tvCinematografeTraseu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        email =getIntent().getStringExtra("email");
        Cursor c = db.getNumeUser(email);
            c.moveToFirst();
            String nume=c.getString(0);
            c.close();

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer=(DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView=findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        tvHeaderNume=header.findViewById(R.id.tvHeaderNume);
        tvHeaderNume.setText(nume);
        tvHeaderEmail=header.findViewById(R.id.tvHeaderEmail);
        tvHeaderEmail.setText(email);
        navigationView.setNavigationItemSelectedListener( this);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(HomeActivity.this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ivCinematografe=findViewById(R.id.ivHomeCinematografe);
        ivProgramCinema=findViewById(R.id.ivHomeProgramCinema);
        ivProgramTv=findViewById(R.id.ivHomeProgramTv);
        ivWatchlist=findViewById(R.id.ivHomeWatchlist);
        ivPremii=findViewById(R.id.ivHomePremii);
        ivStiri=findViewById(R.id.ivHomeStiri);

        tvTopFilmeBune=findViewById(R.id.tvHomeFilmeBune);
        tvTopFilmePopulare=findViewById(R.id.tvHomeFilmePopulare);
        tvTopSerialeBune=findViewById(R.id.tvHomeSerialeBune);
        tvTopSerialePopulare=findViewById(R.id.tvHomeSerialePopulare);
        tvCinematografeTraseu=findViewById(R.id.tvHomeCinematografeTraseu);

        tvCinematografeTraseu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LocatieCurentaActivity.class);
                intent.putExtra("tip", "toate");
                startActivity(intent);
            }
        });
        ivStiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StiriActivity.class);
                startActivity(intent);
            }
        });
        ivPremii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PremiiActivity.class);
                startActivity(intent);
            }
        });
        //film
        tvTopFilmeBune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link="https://www.imdb.com/chart/top/?ref_=nv_mv_250";
                Intent intent = new Intent(getApplicationContext(), TopFilmeActivity.class);
                intent.putExtra("link",link);
                intent.putExtra("tip","film");
                startActivity(intent);
            }
        });
        tvTopFilmePopulare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link="https://www.imdb.com/chart/moviemeter/?ref_=nv_mv_mpm";
                Intent intent = new Intent(getApplicationContext(), TopFilmeActivity.class);
                intent.putExtra("link",link);
                intent.putExtra("tip","film");
                startActivity(intent);
            }
        });
        //serial
        tvTopSerialeBune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String link="https://www.imdb.com/chart/toptv/?ref_=nv_tvv_250";
                Intent intent = new Intent(getApplicationContext(), TopSerialeActivity.class);
                intent.putExtra("link",link);
                intent.putExtra("tip","serial");
                startActivity(intent);
            }
        });
        tvTopSerialePopulare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link="https://www.imdb.com/chart/tvmeter/?ref_=nv_tvv_mptv";
                Intent intent = new Intent(getApplicationContext(), TopSerialeActivity.class);
                intent.putExtra("link",link);
                intent.putExtra("tip","serial");
                startActivity(intent);
            }
        });
        ivProgramTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PosturiTvActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        ivCinematografe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CinematografeActivity.class);
                startActivity(intent);
            }
        });

        ivWatchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),WatchlistActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);

            }
        });
        ivProgramCinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });
    }


    public void getAllFilm(){
        Cursor c=db.selectAllFilm();
        StringBuffer sb= new StringBuffer();
        Log.i("BazaAfis","BazaAfis");
        if (c.getCount() ==0)
        {
            Log.i("BAZA_DE_DATE_GOALA", sb.toString());
            Log.i("Incercare 2","Incercare2");
        }else {
            while (c.moveToNext()){
                sb.append("titlu: "+c.getString(0)+"\n");
                sb.append("ora: "+c.getString(1)+"\n");
                sb.append("data: "+c.getString(2)+"\n");
                sb.append("post : "+c.getString(3)+"\n");
            }
            Log.i("BAZA_DE_DTAE", sb.toString());
        }

    }



    public void getAllWatchlist(){
        Cursor c=db.selectAllWatchlist();
        StringBuffer sb= new StringBuffer();
        Log.i("BazaAfisWatvh","BazaAfisWatch");
        if (c.getCount() ==0)
        {
            Log.i("BAZA_DE_DATE_GOALA", sb.toString());
        }else {
            while (c.moveToNext()){
                sb.append("id: "+c.getString(0)+"\n");
                sb.append("email_user: "+c.getString(1)+"\n");
                sb.append("titlu_film: "+c.getString(2)+"\n");
                sb.append("ora_film: "+c.getString(3)+"\n");
                sb.append("data_film: "+c.getString(4)+"\n");
            }
            Log.i("BAZA_DE_DTAE", sb.toString());
        }

    }

    public void getAllUser(){
        Cursor c=db.selectAllUser();
        StringBuffer sb= new StringBuffer();
        Log.i("USERBAZA","USERBAZA");
        if (c.getCount() ==0)
        {
            Log.i("BAZA_DE_DATE_GOALA", sb.toString());
        }else {
            while (c.moveToNext()){
                sb.append("email: "+c.getString(0)+"\n");
                sb.append("nume: "+c.getString(1)+"\n");
                sb.append("parola: "+c.getString(2)+"\n");
            }
            Log.i("BAZA_DE_DTAE", sb.toString());
        }

    }

    public void getAllCinema(){
        Cursor c=db.selectAllCinema();
        StringBuffer sb= new StringBuffer();
        Log.i("CINEMA","CINEMA");
        if (c.getCount() ==0)
        {
            Log.i("BD_GOALA_CINEMA", sb.toString());
        }else {
            while (c.moveToNext()){
                sb.append("denumire: "+c.getString(0)+"\n");
                sb.append("adresa: "+c.getString(1)+"\n");
                sb.append("link: "+c.getString(2)+"\n");
                sb.append("lat: "+c.getString(3)+"\n");
                sb.append("long: "+c.getString(4)+"\n");
            }
            Log.i("BAZA_DE_DTAE_CINEMA", sb.toString());
        }

    }


    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.item_cinematograf:
            {
                Intent intent = new Intent(getApplicationContext(), LocatieCurentaActivity.class);
                intent.putExtra("tip", "toate");
                startActivity(intent);
                break;
            }
            case R.id.item_cinematografe:
            {
                Intent intent = new Intent(getApplicationContext(),CinematografeActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.item_programCinema:
            {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

                break;
            }
            case R.id.item_prograTV:
            {
                Intent intent = new Intent(getApplicationContext(),PosturiTvActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
                break;
            }
            case R.id.item_stiri:
            {
                Intent intent = new Intent(getApplicationContext(), StiriActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.item_premii:
            {
                Intent intent = new Intent(getApplicationContext(), PremiiActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.item_top_seriale_bune:
            {
                String link="https://www.imdb.com/chart/toptv/?ref_=nv_tvv_250";
                Intent intent = new Intent(getApplicationContext(), TopSerialeActivity.class);
                intent.putExtra("link",link);
                intent.putExtra("tip","serial");
                startActivity(intent);
                break;
            }
            case R.id.item_top_seriale_populare:
            {
                String link="https://www.imdb.com/chart/tvmeter/?ref_=nv_tvv_mptv";
                Intent intent = new Intent(getApplicationContext(), TopSerialeActivity.class);
                intent.putExtra("link",link);
                intent.putExtra("tip","serial");
                startActivity(intent);
                break;
            }
            case R.id.item_top_filme_bune:
            {
                String link="https://www.imdb.com/chart/top/?ref_=nv_mv_250";
                Intent intent = new Intent(getApplicationContext(), TopFilmeActivity.class);
                intent.putExtra("link",link);
                intent.putExtra("tip","film");
                startActivity(intent);
                break;
            }
            case R.id.item_top_filme_populare:
            {
                String link="https://www.imdb.com/chart/moviemeter/?ref_=nv_mv_mpm";
                Intent intent = new Intent(getApplicationContext(), TopFilmeActivity.class);
                intent.putExtra("link",link);
                intent.putExtra("tip","film");
                startActivity(intent);
                break;
            }
            case R.id.item_watchlist:
            {
                Intent intent = new Intent(getApplicationContext(),WatchlistActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
                break;
            }
            case R.id.item_deconectare:
            {
                AlertDialog.Builder altdialog = new AlertDialog.Builder(HomeActivity.this);
                altdialog.setMessage("Deconectare").setCancelable(false)
                        .setPositiveButton("da", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("nu", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = altdialog.create();
                alert.setTitle("Esti sigur?");
                alert.show();
                break;
            }
        }
        return true;
    }
}
