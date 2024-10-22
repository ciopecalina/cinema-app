package com.example.bob.crawlertutorialapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CinematografMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private String denumireCinema, latitudineCinema, longitudineCinema;

    TextView tvMapsInfoCinema;
    TextView tvMapsInfoDistanta;
    List<MarkerOptions> markers = new ArrayList<MarkerOptions>();
    Double latCurenta;
    Double longiCurenta;

    private GoogleMap mMap;

    MarkerOptions origin, destination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_cinematograf);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        String lat= getIntent().getStringExtra("lat");
        String longi=getIntent().getStringExtra("long");

        tvMapsInfoCinema=findViewById(R.id.tvMapsInfoCinema_2);
        tvMapsInfoDistanta=findViewById(R.id.tvMapsInfoDistanta_2);

        latCurenta= Double.parseDouble(lat);
        longiCurenta= Double.parseDouble(longi);

        denumireCinema =getIntent().getStringExtra("denumire");
        latitudineCinema =getIntent().getStringExtra("latitudine");
        longitudineCinema =getIntent().getStringExtra("longitudine");

        origin = new MarkerOptions().position(new LatLng(latCurenta, longiCurenta)).title("LOCATIE CURENTA").snippet("Punct de plecare");
        destination = new MarkerOptions().position(new LatLng(Double.parseDouble(latitudineCinema), Double.parseDouble(longitudineCinema)))
                .snippet("Destinatie");

        markers.add(destination);

        String url = getDirectionsUrl(origin.getPosition(), destination.getPosition());

        DownloadTask downloadTask = new DownloadTask();

        downloadTask.execute(url);
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        ArrayList distante = new ArrayList();

        distante.add(getDistance(destination.getPosition(),origin.getPosition()));

        destination.snippet("Distanta: "+distante.get(0).toString()+" km");

        float distanceMin=900;
        String cinemaMin="";
        for (int i=0;i<distante.size();i++)
        {
            if (Float.parseFloat(distante.get(i).toString()) <= distanceMin)
            {
                distanceMin = Float.parseFloat(distante.get(i).toString());
                cinemaMin=markers.get(i).getTitle();
            }
        }

        tvMapsInfoCinema.setText(cinemaMin);
        tvMapsInfoDistanta.setText("Distanta dintre locatia curenta si cinematograf : "+ String.valueOf(distanceMin)+" KM");

        mMap.addMarker(origin);
        mMap.addMarker(destination);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(origin.getPosition(), 13));
    }


    private class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            String data = "";

            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            parserTask.execute(result);

        }
    }


    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DataParser parser = new DataParser();

                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList points = new ArrayList();
            PolylineOptions lineOptions = new PolylineOptions();


            for (int i = 0; i < result.size(); i++) {

                List<HashMap<String, String>> path = result.get(i);

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                lineOptions.addAll(points);
                lineOptions.width(14);
                lineOptions.color(Color.BLUE);
                lineOptions.geodesic(true);
            }

// Drawing polyline in the Google Map for the i-th route

            if (points.size() != 0)
                mMap.addPolyline(lineOptions);
        }
    }
    public static String getDistance(LatLng latlngA, LatLng latlngB) {
        Location locationA = new Location("Origine");
//origine
        locationA.setLatitude(latlngA.latitude);
        locationA.setLongitude(latlngA.longitude);
//dest
        Location locationB = new Location("Destinatie");

        locationB.setLatitude(latlngB.latitude);
        locationB.setLongitude(latlngB.longitude);

        float distance = locationA.distanceTo(locationB)/1000;
        return String.format("%.2f", distance);
    }
    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        //setting transportation mode
        String mode = "mode=driving";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + "AIzaSyCBtX5aN6WjMFLBRo4G4rrfbJm9V9JIB6U";


        return url;
    }

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();

            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

}


