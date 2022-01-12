package com.example.project_protal;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
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
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.maps.android.PolyUtil;

import java.util.List;

import okhttp3.Route;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class view_location extends AppCompatActivity {
    Marker x;
    Marker y;
    Marker p;
    double latitude;
    double longtude;
    LocationTrack locationTrack;
    private FusedLocationProviderClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_location);
            permissionRequest();
        client=LocationServices.getFusedLocationProviderClient(this);
        if(ActivityCompat.checkSelfPermission(view_location.this, ACCESS_FINE_LOCATION)!=getPackageManager().PERMISSION_GRANTED){
        return;
        }

        client.getLastLocation().addOnSuccessListener(view_location.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
               if(location != null){
                   System.out.println(location.toString());
                   System.out.println(location.getLatitude());
                   System.out.println(location.getLongitude());

                  // latitude=(long)location.getLatitude();
                 //  longtude=(long)location.getLongitude();
               }
            }
        });


        SupportMapFragment mapFragment=new SupportMapFragment();
        final FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.layout1,mapFragment,"map");
        transaction.commit();

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("WrongConstant")
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                MapStyleOptions mapStyleOptions=MapStyleOptions.loadRawResourceStyle(getApplicationContext(),R.raw.theem);
                googleMap.setMapStyle(mapStyleOptions); //map ekta theem ekk add kirima...

            locationTrack=new LocationTrack(view_location.this);
            if(locationTrack.canGetLocation){
                longtude = locationTrack.getLongitude();
                latitude = locationTrack.getLatitude();
                System.out.println(longtude);
                System.out.println(latitude);
                System.out.println("kk");


                Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(longtude) + "\nLatitude:" + Double.toString(latitude), Toast.LENGTH_LONG).show();
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
////                System.out.println("kalpa Sadaruwan");


              // String encodeOverview="gfok@ykckNkAy@y@u@[a@i@oAYu@Yo@G[G[EU?]Ba@TqB@_@BY?CBGJ[FSDO?WBgIuCXcBH\\yEDg@`AQ`B]";
//               String encodeOverview="";
//                List<LatLng> polylines= PolyUtil.decode(encodeOverview);
//                PolylineOptions polylineOptions=new PolylineOptions();
//                polylineOptions.add(marker.getPosition());
//                polylineOptions.add(marker2.getPosition());
//
//                polylineOptions.color(R.color.c2);
//                polylineOptions.width(5);
//                polylineOptions.addAll(polylines);
//                googleMap.addPolyline(polylineOptions);





                }

//


        });





    }


private  void permissionRequest(){
      ActivityCompat.requestPermissions(view_location.this,new String[]{ACCESS_FINE_LOCATION},1);
}





}
