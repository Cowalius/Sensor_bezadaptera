package com.example.sensor_bezadaptera;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Gyroscope extends AppCompatActivity implements SensorEventListener {
//Gyroscope
TextView textView;
    SensorManager sensorManager;
    Sensor sensor;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        textView=findViewById(R.id.textView2);
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType()==Sensor.TYPE_GYROSCOPE){
             textView.setText(" X:"+
                    sensorEvent.values[0]+ " Y: "+
                    sensorEvent.values[1]+
                    " Z: " +sensorEvent.values[2]);


    }


}

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}