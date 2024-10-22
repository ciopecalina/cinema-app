package com.example.bob.crawlertutorialapp;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LocatieCurentaActivity extends AppCompatActivity {
    private static  final int REQUEST_LOCATION=1;

    private String denumireCinema, latitudineCinema, longitudineCinema;

    Button getlocationBtn;
    Button trimiteBtn;
    TextView showLocationTxt;

    LocationManager locationManager;
    String latitude,longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locatie_curenta);

        denumireCinema =getIntent().getStringExtra("denumire");
        latitudineCinema =getIntent().getStringExtra("latitudine");
        longitudineCinema =getIntent().getStringExtra("longitudine");

        ActivityCompat.requestPermissions(this,new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        showLocationTxt=findViewById(R.id.show_location);
        getlocationBtn=findViewById(R.id.getLocation);
        trimiteBtn=findViewById(R.id.trimiteBtn);

        final String tip = getIntent().getExtras().getString("tip");

        getlocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                {
                    OnGPS();
                }
                else
                {
                    getLocation();
                }
            }
        });

        trimiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tip.equals("cinema"))
                {
                    Intent intent = new Intent(getApplicationContext(),CinematografMapsActivity.class);
                    intent.putExtra("lat", latitude);
                    intent.putExtra("long", longitude);
                    intent.putExtra("denumire",denumireCinema);
                    intent.putExtra("latitudine", latitudineCinema);
                    intent.putExtra("longitudine",longitudineCinema);
                    startActivity(intent);

                }else {
                    Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                    intent.putExtra("lat", latitude);
                    intent.putExtra("long", longitude);
                    startActivity(intent);

                }
            }
        });

    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(LocatieCurentaActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(LocatieCurentaActivity.this,

                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else
        {
            Location LocationGps= locationManager
                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork=locationManager
                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive=locationManager
                    .getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (LocationGps !=null)
            {
                double lat=LocationGps.getLatitude();
                double longi=LocationGps.getLongitude();

                latitude= String.valueOf(lat);
                longitude= String.valueOf(longi);

                showLocationTxt.setText("Locatia curenta este:"+"\n\n"+"Latitudine = "+latitude+"\n"+"Longitudine = "+longitude);
            }
            else if (LocationNetwork !=null)
            {
                double lat=LocationNetwork.getLatitude();
                double longi=LocationNetwork.getLongitude();

                latitude= String.valueOf(lat);
                longitude= String.valueOf(longi);

                showLocationTxt.setText("Locatia curenta este:"+"\n\n"+"Latitudine = "+latitude+"\n"+"Longitudine = "+longitude);
            }
            else if (LocationPassive !=null)
            {
                double lat=LocationPassive.getLatitude();
                double longi=LocationPassive.getLongitude();

                latitude= String.valueOf(lat);
                longitude= String.valueOf(longi);

                showLocationTxt.setText("Locatia curenta este:"+"\n\n"+"Latitudine = "+latitude+"\n"+"Longitudine = "+longitude);
            }
            else
            {
                Toast.makeText(this, "Nu se poate accesa locatia curenta!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void OnGPS() {

        final AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setMessage("Activare GPS").setCancelable(false).setPositiveButton("Da", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("Nu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
