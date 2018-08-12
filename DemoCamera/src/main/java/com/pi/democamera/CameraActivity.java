package com.pi.democamera;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.pi.pano.PanoSDK;
import com.pi.pano.ChangeResolutionListener;
import com.pi.pano.PanoSDKListener;
import com.pi.pano.TakePhotoListener;

public class CameraActivity extends Activity
{
    private static final String TAG = "CameraActivity";

    public PanoSDK mPanoSDK;
    public ImageView mShutter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_camera);
        initPanoSDK();

        mShutter = findViewById(R.id.shutter);
        mShutter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShutter.setClickable(false);
                String fileName = "/sdcard/DCIM/demo.jpg";
                PanoSDK.takePhoto(fileName,8192, 4096,
                        new TakePhotoListener(true,false,false) {
                            @Override
                            public void onTakePhotoComplete(int errorCode)
                            {
                                CameraActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mShutter.setClickable(true);
                                        Toast.makeText(CameraActivity.this, "take photo finish", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        });
            }
        });
    }

    private void initPanoSDK() {
        FrameLayout frameLayout = findViewById(R.id.fl_preivew);
        mPanoSDK = new PanoSDK(this, frameLayout, true, new PanoSDKListener() {
            @Override
            public void onSDKCreate() {
                PanoSDK.changeCameraResolution(PanoSDK.CAMERA_PREVIEW_4048_2530_15, new ChangeResolutionListener()
                {
                    @Override
                    public void onChangeResolution(int width, int height)
                    {
                        Log.i(TAG,"onChangeResolution "+width+" "+height);
                    }
                });

                PanoSDK.useGyroscope(false);
                PanoSDK.setWatermarkCircle( getResources().openRawResource(R.raw.watermark));
            }

            @Override
            public void onChangePanoMode(int mode) {
            }

            @Override
            public void onSingleTapConfirmed() {
            }
        });
    }
}
