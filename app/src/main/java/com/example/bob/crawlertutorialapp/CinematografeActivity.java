package com.example.bob.crawlertutorialapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class CinematografeActivity extends AppCompatActivity {
    DatabaseHelper db= new DatabaseHelper(this);

    TextView tvLantCinemaTraseu;

    RecyclerView rvLantCinemaCity;
    RecyclerView rvGrandCinema;
    RecyclerView rvAlteCinematografe;

    ArrayList<Cinematograf> lantCinemaCityList;
    ArrayList<Cinematograf> grandCinemaList;
    ArrayList<Cinematograf> alteCinematografeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinematografe);

        tvLantCinemaTraseu=findViewById(R.id.tvLantCinemaTraseu);

        rvLantCinemaCity=findViewById(R.id.rvLantCinemaCity);
        rvGrandCinema=findViewById(R.id.rvGrandCinema);
        rvAlteCinematografe=findViewById(R.id.rvAlteCinematografe);

        lantCinemaCityList= new ArrayList<>();
        grandCinemaList= new ArrayList<>();
        alteCinematografeList= new ArrayList<>();

        Cursor c=db.selectAllCinema();
        Cinematograf cinema;
        int i=1;
            while (c.moveToNext()){
                cinema = new Cinematograf(c.getString(0),c.getString(1),
                        c.getString(2),c.getString(3),c.getString(4) );
                i++;
                if (i>0 && i<10){

                    lantCinemaCityList.add(cinema);

                }else if (i>9 && i<15){

                    grandCinemaList.add(cinema);
                }else {
                    alteCinematografeList.add(cinema);
                }
            }

        lantCinemaCityList.get(0).setImage(R.drawable.cinemacitycotroceni);
        lantCinemaCityList.get(1).setImage(R.drawable.cinemacitycotrocenisalavip);
        lantCinemaCityList.get(2).setImage(R.drawable.cinemacitysunplaza);
        lantCinemaCityList.get(3).setImage(R.drawable.cinemacityparklake);
        lantCinemaCityList.get(4).setImage(R.drawable.cinemacityparklake_vip);
        lantCinemaCityList.get(5).setImage(R.drawable.cinemacitymegamall);
        lantCinemaCityList.get(6).setImage(R.drawable.cinemacitymegamalldx);
        lantCinemaCityList.get(7).setImage(R.drawable.imax);

        grandCinemaList.get(0).setImage(R.drawable.salagrandcinema);
        grandCinemaList.get(1).setImage(R.drawable.grandcinemasalaultra);
        grandCinemaList.get(2).setImage(R.drawable.grandcinemaepika);
        grandCinemaList.get(3).setImage(R.drawable.studios);
        grandCinemaList.get(4).setImage(R.drawable.studios);

        alteCinematografeList.get(0).setImage(R.drawable.movieplex);
        alteCinematografeList.get(1).setImage(R.drawable.multiplex);
        alteCinematografeList.get(2).setImage(R.drawable.happycinema);
        alteCinematografeList.get(3).setImage(R.drawable.cineplexx);

//        lantCinemaCityList.add(new Cinematograf(R.drawable.cinemacitycotroceni,"CINEMA CITY COTROCENI","AFI Cotroceni\n" +
//                "Adresă: Bulevardul General Vasile Milea 4, București 061344","https://www.zilesinopti.ro/locuri/587/cinema-city-cotroceni"));
//        lantCinemaCityList.add(new Cinematograf(R.drawable.cinemacitycotrocenisalavip,"CINEMA CITY COTROCENI (SALA VIP)","AFI Cotroceni\n" +
//                "Adresă: Bulevardul General Vasile Milea 4, București 061344","https://www.zilesinopti.ro/locuri/588/cinema-city-cotroceni-sala-vip"));
//        lantCinemaCityList.add(new Cinematograf(R.drawable.cinemacitysunplaza,"CINEMA CITY SUN PLAZA","Mall Sun Plaza\n" +
//                "Adresă: Calea Văcărești 391, București 040069","https://www.zilesinopti.ro/locuri/589/cinema-city-sun-plaza"));
//        lantCinemaCityList.add(new Cinematograf(R.drawable.cinemacityparklake,"CINEMA CITY PARK LAKE","ParkLake\n" +
//                "Adresă: Park Lake Shopping center, Strada Liviu Rebreanu 4, București","https://www.zilesinopti.ro/locuri/9519/cinema-city-park-lake"));
//        lantCinemaCityList.add(new Cinematograf(R.drawable.cinemacityparklake_vip,"CINEMA CITY PARK LAKE VIP","ParkLake\n" +
//                "Adresă: Park Lake Shopping center, Strada Liviu Rebreanu 4, București","https://www.zilesinopti.ro/locuri/10885/cinema-city-park-lake-vip"));
//        lantCinemaCityList.add(new Cinematograf(R.drawable.cinemacitymegamall,"CINEMA CITY MEGA MALL","Mega Mall\n" +
//                "Adresă: Bulevardul Pierre de Coubertin 3-5, București 021901","https://www.zilesinopti.ro/locuri/7843/cinema-city-mega-mall"));
//        lantCinemaCityList.add(new Cinematograf(R.drawable.cinemacitymegamalldx,"CINEMA CITY MEGA MALL 4DX","Mega Mall\n" +
//                "Adresă: Bulevardul Pierre de Coubertin 3-5, București 021901","https://www.zilesinopti.ro/locuri/8004/cinema-city-mega-mall-4dx"));
//        lantCinemaCityList.add(new Cinematograf(R.drawable.imax,"IMAX ","AFI Cotroceni\n" +
//                "Adresă: Bulevardul General Vasile Milea 4, București 061344"));
//

//        grandCinemaList.add(new Cinematograf(R.drawable.salagrandcinema,"GRAND CINEMA & MORE","Băneasa Shopping City\n" +
//                "Adresă: Șoseaua București-Ploiești 42D, București 013696","https://www.zilesinopti.ro/locuri/781/grand-cinema-and-more"));
//        grandCinemaList.add(new Cinematograf(R.drawable.grandcinemasalaultra,"GRAND CINEMA AND MORE - SALA GRAND ULTRA","Băneasa Shopping City\n" +
//                "Adresă: Șoseaua București-Ploiești 42D, București 013696","https://www.zilesinopti.ro/locuri/784/grand-cinema-and-more-sala-grand-ultra"));
//        grandCinemaList.add(new Cinematograf(R.drawable.grandcinemaepika,"GRAND CINEMA AND MORE - SALA GRAND EPIKA","Băneasa Shopping City\n" +
//                "Adresă: Șoseaua București-Ploiești 42D, București 013696","https://www.zilesinopti.ro/locuri/3849/grand-cinema-and-more-sala-grand-epika"));
//        grandCinemaList.add(new Cinematograf(R.drawable.studios,"GRAND CINEMA AND MORE - GRAND STUDIOS","Băneasa Shopping City\n" +
//                "Adresă: Șoseaua București-Ploiești 42D, București 013696","https://www.zilesinopti.ro/locuri/3619/grand-cinema-and-more-grand-studios"));
//        grandCinemaList.add(new Cinematograf(R.drawable.studios,"GRAND CINEMA AND MORE - GRAND STUDIOS 2","Băneasa Shopping City\n" +
//                "Adresă: Șoseaua București-Ploiești 42D, București 013696","https://www.zilesinopti.ro/locuri/3817/grand-cinema-and-more-grand-studios-2"));

//        alteCinematografeList.add(new Cinematograf(R.drawable.movieplex,"MOVIEPLEX CINEMA","Plaza România\n" +
//                "Adresă: Bulevardul Timișoara 26 Plaza Romania, etaj 2, București","https://www.zilesinopti.ro/locuri/3/movieplex-cinema"));
//        alteCinematografeList.add(new Cinematograf(R.drawable.multiplex,"HOLLYWOOD MULTIPLEX","București Mall\n" +
//                "Adresă: București Mall, Etaj 2, Calea Vitan 55-59, București 031281","https://www.zilesinopti.ro/locuri/1/hollywood-multiplex"));
//        alteCinematografeList.add(new Cinematograf(R.drawable.happycinema,"\n" +
//                "HAPPY CINEMA","Liberty Center, Etaj 2, Strada Progresului 151-171, București 050696"));
//        alteCinematografeList.add(new Cinematograf(R.drawable.cineplexx,"CINEPLEXX TITAN","Iris Shopping Center, Etaj 1, București 032455","https://www.zilesinopti.ro/locuri/12172/cineplexx-titan"));

        LinearLayoutManager layoutManagerLantCinemaCity=new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvCinematografeLayoutManagerLantCinemaCity= layoutManagerLantCinemaCity;
        rvLantCinemaCity.setLayoutManager(rvCinematografeLayoutManagerLantCinemaCity);

        LinearLayoutManager layoutManagerGrandCinema=new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvCinematografeLayoutManagerGrandCinema= layoutManagerGrandCinema;
        rvGrandCinema.setLayoutManager(rvCinematografeLayoutManagerGrandCinema);

        LinearLayoutManager layoutManagerAlte=new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvCinematografeLayoutManagerAlte= layoutManagerAlte;
        rvAlteCinematografe.setLayoutManager(rvCinematografeLayoutManagerAlte);

        CinematografAdapter adapterLantCinemaCity= new CinematografAdapter(this,lantCinemaCityList);
        CinematografAdapter adapterGrandCinema= new CinematografAdapter(this,grandCinemaList);
        CinematografAdapter adapterAlte= new CinematografAdapter(this,alteCinematografeList);

        rvLantCinemaCity.setAdapter(adapterLantCinemaCity);
        rvGrandCinema.setAdapter(adapterGrandCinema);
        rvAlteCinematografe.setAdapter(adapterAlte);

        tvLantCinemaTraseu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LocatieCurentaActivity.class);
                intent.putExtra("tip", "toate");
                startActivity(intent);
            }
        });
    }
}
