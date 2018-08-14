package com.pi.democamera;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.pi.pano.PilotSDK;
import com.pi.pano.ChangeResolutionListener;
import com.pi.pano.PanoSDKListener;
import com.pi.pano.TakePhotoListener;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class CameraActivity extends Activity
{
    private static final String TAG = "CameraActivity";

    public PilotSDK mPilotSDK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_camera);
        initPanoSDK();
    }

    private void initPanoSDK() {
        FrameLayout frameLayout = findViewById(R.id.fl_preivew);
        mPilotSDK = new PilotSDK(this, frameLayout, true, new PanoSDKListener() {
            @Override
            public void onSDKCreate() {
                PilotSDK.changeCameraResolution(PilotSDK.CAMERA_PREVIEW_4048_2530_15, new ChangeResolutionListener()
                {
                    @Override
                    public void onChangeResolution(int width, int height)
                    {
                        Log.i(TAG,"onChangeResolution "+width+" "+height);
                    }
                });

                PilotSDK.setWatermarkCircle( getResources().openRawResource(R.raw.watermark));

//                PilotSDK.setPreviewCallback(new CameraPreviewCallback() {
//                    @Override
//                    public void onPreviewCallback(int width, int height, long timeStamp, ByteBuffer buffer) {
//                        Log.i(TAG, "width: " + width + " height: " + height + " time stamp: "+ timeStamp +
//                                " buffer: " + buffer.capacity());
//
//                        try {
//                            FileOutputStream os = new FileOutputStream("/sdcard/DCIM/"+timeStamp+".jpg");
//                            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//                            bmp.copyPixelsFromBuffer(buffer);
//                            bmp.compress(Bitmap.CompressFormat.JPEG,100,os);
//                            os.close();
//                        }catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
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
