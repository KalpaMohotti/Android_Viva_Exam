package com.example.project_protal;

import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.project_protal.Object_classes.DirectionsJSONParser;
import com.example.project_protal.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
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

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private LatLng mOrigin;
    private LatLng mDestination;
    private Polyline mPolyline;
    LocationTrack locationTrack;
    double latitude;
    double longtude;
    ArrayList<LatLng> mMarkerPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        mMarkerPoints = new ArrayList<>();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng point) {
//                // Already two locations
//        if(mMarkerPoints.size()>1){
//            mMarkerPoints.clear();
//            mMap.clear();
//        }
//
//        // Adding new item to the ArrayList
//        mMarkerPoints.add(point);
//
//        // Creating MarkerOptions
//        MarkerOptions options = new MarkerOptions();
//
//        // Setting the position of the marker
//        options.position(point);
//
//        /**
//         * For the start location, the color of marker is GREEN and
//         * for the end location, the color of marker is RED.
//         */
//        if(mMarkerPoints.size()==1){
//            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
//        }else if(mMarkerPoints.size()==2){
//            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//        }
//
//        // Add new marker to the Google Map Android API V2
//        mMap.addMarker(options);
//
//        // Checks, whether start and end locations are captured
//        if(mMarkerPoints.size() >= 2){
//            mOrigin = mMarkerPoints.get(0);
//            mDestination = mMarkerPoints.get(1);
//            drawRoute();
//        }
//
//    }
//});


        MapStyleOptions mapStyleOptions=MapStyleOptions.loadRawResourceStyle(getApplicationContext(),R.raw.theem);
        googleMap.setMapStyle(mapStyleOptions); //map ekta theem ekk add kirima...

        locationTrack=new LocationTrack(MapsActivity2.this);
        if(locationTrack.canGetLocation){
            longtude = locationTrack.getLongitude();
            latitude = locationTrack.getLatitude();
          //  System.out.println(longtude);
          //  System.out.println(latitude);
         //   System.out.println("kk");


            //Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(longtude) + "\nLatitude:" + Double.toString(latitude), Toast.LENGTH_LONG).show();
        }else{

            locationTrack.showSettingsAlert();
        }

        LatLng location=new LatLng(latitude,longtude);  //map ekta location ekk dima.load wenakota..

        final CameraPosition.Builder builder=new CameraPosition.Builder();
        builder.target(location);
        builder.zoom(17);
        CameraPosition cameraPosition=builder.build();

        CameraUpdate cameraUpdate= CameraUpdateFactory.newCameraPosition(cameraPosition);
        googleMap.animateCamera(cameraUpdate,10000,null);

        final MarkerOptions markerOptions=new MarkerOptions();  //Add marker (api dunna location ekta marker ekk add kirima)
        markerOptions.position(location);
        markerOptions.title("You");  //marker ekta value ekk dima
        markerOptions.flat(true);//map eka harawana widiyata marker eka rotate wen na...

        BitmapDescriptor bitmapDescriptor= BitmapDescriptorFactory.fromResource(R.drawable.mar);
        markerOptions.icon(bitmapDescriptor); //marker ekta image ekk set kirima(PNG ekk drowble ekta copy karai)

        Marker marker= googleMap.addMarker(markerOptions);
        marker.showInfoWindow();  //marker eke value eka show kirima..

        //The class
        LatLng location2=new LatLng(7.292286,80.631799);  //map ekta location ekk dima.load wenakota..


        final MarkerOptions markerOptions2=new MarkerOptions();  //Add marker (api dunna location ekta marker ekk add kirima)
        markerOptions2.position(location2);
        markerOptions2.title("Your class");  //marker ekta value ekk dima
        markerOptions2.flat(true);//map eka harawana widiyata marker eka rotate wen na...

        BitmapDescriptor bitmapDescriptor2= BitmapDescriptorFactory.fromResource(R.drawable.mar);
        markerOptions.icon(bitmapDescriptor2); //marker ekta image ekk set kirima(PNG ekk drowble ekta copy karai)

        Marker marker2= googleMap.addMarker(markerOptions2);
        marker2.showInfoWindow();  //marker eke value eka show kirima..

        ////                //Map settings
        googleMap.getUiSettings().setZoomControlsEnabled(true); //zoom krna buttions
        googleMap.getUiSettings().setMyLocationButtonEnabled(true); //UI buttion enable(location buttion)

        mOrigin=location;
        mDestination=location2;
   drawRoute();
    }

    private void drawRoute(){

        // Getting URL to the Google Directions API
        String url = getDirectionsUrl(mOrigin, mDestination);

        DownloadTask downloadTask = new DownloadTask();

        // Start downloading json data from Google Directions API
        downloadTask.execute(url);
    }


    private String getDirectionsUrl(LatLng origin,LatLng dest){

        // Origin of route
        String str_origin = "origin="+origin.latitude+","+origin.longitude;

        // Destination of route
        String str_dest = "destination="+dest.latitude+","+dest.longitude;

        // Key
//        String key = "key=" + getString(R.string.google_maps_key);
        String key = "key=AIzaSyB7IZIaUyX8PgtGGw1GBVBA6HB3oMEvLMQ";


        // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+key;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;
//String url="https://maps.googleapis.com/maps/api/directions/json?\n" +
//        "origin=Pitiyegedara Rajamaha Viharaya&destination=Java Institute Kandy\n" +
//        "&key=AIzaSyB7IZIaUyX8PgtGGw1GBVBA6HB3oMEvLMQ";




//        LatLng orig=new LatLng(7.292286,80.631799);
//        LatLng dest1=new LatLng(6.927079,	79.861244);
//
//        String origin1 = "origin=" + orig.latitude + "," + orig.longitude;
//        String destination = "destination=" + dest1.latitude + "," + dest1.longitude;
//        String key1 = key;
//       String url = "https://maps.googleapis.com/maps/api/directions/json?" + origin1 + "&" + destination + "&" + key1;


     //   String url="https://maps.googleapis.com/maps/api/directions/json?origin=place_id:ChIJ685WIFYViEgRHlHvBbiD5nE&destination=place_id:ChIJA01I-8YVhkgRGJb0fW4UX7Y&key=AIzaSyB7IZIaUyX8PgtGGw1GBVBA6HB3oMEvLMQ";
        return url;
    }

    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb  = new StringBuffer();

            String line = "";
            while( ( line = br.readLine())  != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            Log.d("Exception on download", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    /** A class to download data from Google Directions URL */
    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                Log.d("DownloadTask","DownloadTask : " + data);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    /** A class to parse the Google Directions in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;

            // Traversing through all the routes
            for(int i=0;i<result.size();i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(8);
                lineOptions.color(Color.RED);
            }

            // Drawing polyline in the Google Map for the i-th route
           // mPolyline = mMap.addPolyline(lineOptions);

            if(lineOptions != null) {
                if(mPolyline != null){
                    mPolyline.remove();
                }
                mPolyline = mMap.addPolyline(lineOptions);

            }else
                Toast.makeText(getApplicationContext(),"No route is found", Toast.LENGTH_LONG).show();
        }
    }

}

