package com.example.sensor_bezadaptera;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Localization extends AppCompatActivity {

    Button button;
    TextView textView1, textView2, textView3, textView4, textView5;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localization);

        button = findViewById(R.id.bt_location);
        textView1 = findViewById(R.id.textv1);
        textView2 = findViewById(R.id.textv2);
        textView3 = findViewById(R.id.textv3);
        textView4 = findViewById(R.id.textv4);
        textView5 = findViewById(R.id.textv5);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //permission
                if (ActivityCompat.checkSelfPermission(Localization.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(Localization.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                //zainicjowanie lokalizacji
                Location location = task.getResult();
                if (location != null) {

                    //adress
                    try {
                        Geocoder geocoder = new Geocoder(Localization.this, Locale.getDefault());

                        List<Address> adresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                        //latitude
                        textView1.setText(Html.fromHtml("<font color='#6200EE'><b>Latitude: </b><br></font>"
                                + adresses.get(0).getLatitude()) );
                        //longitude
                        textView2.setText(Html.fromHtml("<font color='#6200EE'><b>Longitude: </b><br></font>"
                                + adresses.get(0).getLongitude()));
                        //country
                        textView3.setText(Html.fromHtml("<font color='#6200EE'><b>Country: </b><br></font>" + adresses.get(0).getCountryName()));
                        //locality
                        textView4.setText(Html.fromHtml("<font color='#6200EE'><b>locality: </b><br></font>" + adresses.get(0).getLocality()));
                        //address
                        textView5.setText(Html.fromHtml("<font color='#6200EE'><b>Address: </b><br></font>"+ adresses.get(0).getAddressLine(0)));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "NO DATA",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}