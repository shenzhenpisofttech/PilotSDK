package com.pi.pilot.demoimu;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

/**
 *  Compass 指南针
 */
public class MainActivity extends Activity {
    private static final String TAG="MainActivity";

    private SensorManager sensorManager;
    private TextView mDataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        sensorManager =(SensorManager)getSystemService(SENSOR_SERVICE);
        mDataText = findViewById(R.id.data1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Sensor orient =sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sensorManager.registerListener(listener, orient, SensorManager.SENSOR_DELAY_GAME);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listener);
    }
    private SensorEventListener listener =new SensorEventListener() {
        @SuppressWarnings("deprecation")
        @Override
        public void onSensorChanged(SensorEvent event) {
            switch (event.sensor.getType()) {
                case Sensor.TYPE_ORIENTATION:
                    final float x = event.values[SensorManager.DATA_X];
                    final float y = event.values[SensorManager.DATA_Y];
                    final float z = event.values[SensorManager.DATA_Z];
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mDataText != null) {
                                mDataText.setText("x:" + (int) x + "\ny:" + (int) y + "\nz:" + (int) z);
                            }
                        }
                    });
                    break;
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
            Log.d(TAG, "onAccuracyChanged: "+i);
        }
    };
}
