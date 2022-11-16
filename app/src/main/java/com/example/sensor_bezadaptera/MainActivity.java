package com.example.sensor_bezadaptera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.textView);
        SensorManager sensorManager =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        ListView list=(ListView) findViewById(R.id.list);
        list.setAdapter(new MySensorsAdapter(this, R.layout.row_item,sensors));
        int i = sensors.size();


        textView.setText("Number of sensors: "+ i);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               if(sensors.get(i).getName()=="Goldfish 3 axis Accelerometer"){
                   Intent intent = new Intent(MainActivity.this, Accelerometer.class);
                   startActivity(intent);
               }
                if(sensors.get(i).getName()=="Goldfish 3 axis Gyroscope"){
                    Intent intent = new Intent(MainActivity.this,Gyroscope.class);
                    startActivity(intent);

                }
//if(itemtext.equals("Youtube ")||itemtext.equals("youtube.com ")){
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com"));
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.setPackage("com.google.android.youtube");
//                    startActivity(intent);
            }
        });

    }



}