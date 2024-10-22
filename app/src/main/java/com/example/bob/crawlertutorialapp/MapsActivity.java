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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    TextView tvMapsInfo;
    TextView tvMapsInfoCinema;
    TextView tvMapsInfoDistanta;
    List<MarkerOptions> markers = new ArrayList<MarkerOptions>();
    Double latCurenta;
    Double longiCurenta;

    private GoogleMap mMap;

    MarkerOptions origin, destination,destination2,destination3,
            destination4,destination5,destination6,destination7,destination8,destination9,destination10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        String lat= getIntent().getStringExtra("lat");
        String longi=getIntent().getStringExtra("long");

        tvMapsInfo=findViewById(R.id.tvMapsInfo);
        tvMapsInfoCinema=findViewById(R.id.tvMapsInfoCinema);
        tvMapsInfoDistanta=findViewById(R.id.tvMapsInfoDistanta);

        tvMapsInfo.setText("Cel mai apropiat cinematograf este:");

        latCurenta= Double.parseDouble(lat);
        longiCurenta= Double.parseDouble(longi);

        //Setting marker to draw route between these two points

        origin = new MarkerOptions().position(new LatLng(latCurenta, longiCurenta)).title("LOCATIE CURENTA").snippet("Punct de plecare");
        destination = new MarkerOptions().position(new LatLng(44.4280571, 26.0343096)).title("MOVIEPLEX - Plaza Mall");
        destination2 = new MarkerOptions().position(new LatLng(44.430602, 26.052030)).title("CINEMA CITY - Afi Palace Cotroceni");
        destination3 = new MarkerOptions().position(new LatLng(44.396175064705815, 26.122441291809086)).title("CINEMA CITY - Sun Plaza");
        destination4 = new MarkerOptions().position(new LatLng(44.440750970508674, 26.152149438858032)).title("CINEMA CITY - Mega Mall");
        destination5 = new MarkerOptions().position(new LatLng(44.420741, 26.149408)).title("CINEMA CITY Park Lake - Park Lake Shopping Center");
        destination6 = new MarkerOptions().position(new LatLng(44.506496, 26.090260)).title("GRAND CINEMA & More - Baneasa Shopping City");
        destination7 = new MarkerOptions().position(new LatLng(44.440750970508674, 26.152149438858032)).title("CINEMA CITY - Mega Mall");
        destination8 = new MarkerOptions().position(new LatLng(44.4198737, 26.1258525)).title("HOLLYWOOD MULTIPLEX - Bucuresti Mall");
        destination9 = new MarkerOptions().position(new LatLng(44.415736, 26.0798699)).title("HAPPY CINEMA - Liberty Center");
        destination10 = new MarkerOptions().position(new LatLng(44.41931402530711, 26.178703308105472)).title("CINEPLEXX - Auchan Titan");


        markers.add(destination);
        markers.add(destination2);
        markers.add(destination3);
        markers.add(destination4);
        markers.add(destination5);
        markers.add(destination6);
        markers.add(destination7);
        markers.add(destination8);
        markers.add(destination9);
        markers.add(destination10);

        // Getting URL to the Google Directions API
        String url = getDirectionsUrl(origin.getPosition(), destination.getPosition());
        String url2 = getDirectionsUrl(origin.getPosition(), destination2.getPosition());
        String url3 = getDirectionsUrl(origin.getPosition(), destination3.getPosition());
        String url4 = getDirectionsUrl(origin.getPosition(), destination4.getPosition());
        String url5 = getDirectionsUrl(origin.getPosition(), destination5.getPosition());
        String url6 = getDirectionsUrl(origin.getPosition(), destination6.getPosition());
        String url7 = getDirectionsUrl(origin.getPosition(), destination7.getPosition());
        String url8 = getDirectionsUrl(origin.getPosition(), destination8.getPosition());
        String url9 = getDirectionsUrl(origin.getPosition(), destination9.getPosition());
        String url10 = getDirectionsUrl(origin.getPosition(), destination10.getPosition());

        DownloadTask downloadTask = new DownloadTask();
        DownloadTask downloadTask2 = new DownloadTask();
        DownloadTask downloadTask3 = new DownloadTask();
        DownloadTask downloadTask4 = new DownloadTask();
        DownloadTask downloadTask5 = new DownloadTask();
        DownloadTask downloadTask6 = new DownloadTask();
        DownloadTask downloadTask7 = new DownloadTask();
        DownloadTask downloadTask8 = new DownloadTask();
        DownloadTask downloadTask9 = new DownloadTask();
        DownloadTask downloadTask10 = new DownloadTask();


        // Start downloading json data from Google Directions API
        downloadTask.execute(url);
        downloadTask2.execute(url2);
        downloadTask3.execute(url3);
        downloadTask4.execute(url4);
        downloadTask5.execute(url5);
        downloadTask6.execute(url6);
        downloadTask7.execute(url7);
        downloadTask8.execute(url8);
        downloadTask9.execute(url9);
        downloadTask10.execute(url10);
    }


    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        ArrayList distante = new ArrayList();

        distante.add(getDistance(destination.getPosition(),origin.getPosition()));
        distante.add(getDistance(destination2.getPosition(),origin.getPosition()));
        distante.add(getDistance(destination3.getPosition(),origin.getPosition()));
        distante.add(getDistance(destination4.getPosition(),origin.getPosition()));
        distante.add(getDistance(destination5.getPosition(),origin.getPosition()));
        distante.add(getDistance(destination6.getPosition(),origin.getPosition()));
        distante.add(getDistance(destination7.getPosition(),origin.getPosition()));
        distante.add(getDistance(destination8.getPosition(),origin.getPosition()));
        distante.add(getDistance(destination9.getPosition(),origin.getPosition()));
        distante.add(getDistance(destination10.getPosition(),origin.getPosition()));

        destination.snippet("Distanta: "+distante.get(0).toString()+" km");
        destination2.snippet("Distanta: "+distante.get(1).toString()+" km");
        destination3.snippet("Distanta: "+distante.get(2).toString()+" km");
        destination4.snippet("Distanta: "+distante.get(3).toString()+" km");
        destination5.snippet("Distanta: "+distante.get(4).toString()+" km");
        destination6.snippet("Distanta: "+distante.get(5).toString()+" km");
        destination7.snippet("Distanta: "+distante.get(6).toString()+" km");
        destination8.snippet("Distanta: "+distante.get(7).toString()+" km");
        destination9.snippet("Distanta: "+distante.get(8).toString()+" km");
        destination10.snippet("Distanta: "+distante.get(9).toString()+" km");

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
        tvMapsInfoDistanta.setText("Distanta: "+ String.valueOf(distanceMin)+" KM");

        mMap.addMarker(origin);
        mMap.addMarker(destination);
        mMap.addMarker(destination2);
        mMap.addMarker(destination3);
        mMap.addMarker(destination4);
        mMap.addMarker(destination5);
        mMap.addMarker(destination6);
        mMap.addMarker(destination7);
        mMap.addMarker(destination8);
        mMap.addMarker(destination9);
        mMap.addMarker(destination10);

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


