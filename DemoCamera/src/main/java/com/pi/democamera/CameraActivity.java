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

import com.pi.pano.CameraPreviewCallback;
import com.pi.pano.PilotSDK;
import com.pi.pano.ChangeResolutionListener;
import com.pi.pano.PanoSDKListener;

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
                //you can only call PilotSDK.some_function after onSDKCreate

                PilotSDK.changeCameraResolution(PilotSDK.CAMERA_PREVIEW_4048_2530_15, new ChangeResolutionListener()
                {
                    @Override
                    public void onChangeResolution(int width, int height)
                    {
                        Log.i(TAG,"onChangeResolution "+width+" "+height);
                    }
                });

                PilotSDK.setPreviewCallback(new CameraPreviewCallback() {
                    /**
                     * @param width call back image width, which is equal to the width of one camera's image
                     * @param height call back image height, which is four times of height of one camera's image,
                     *               since there is four images in call back image
                     * @param timeStamp the time stamp of camera0,there are no time stamps of camera1 camera2 and
                     *                  camera3,but the time stamps of these three camera are almost same,pilot
                     *                  four cameras has made frame synchronous,the time stamp difference of four
                     *                  cameras is less than 50us
                     * @param buffer the buffer of four cameras, pixel format is RGBA_8888, e.g.if width is 720
                     *               and height is 4608 (four camera's image, each image is 10:16,720*1152), the
                     *               offset of second is 720*1152*4
                     */
                    @Override
                    public void onPreviewCallback(int width, int height, long timeStamp, ByteBuffer buffer) {
                        Log.i(TAG, "width: " + width + " height: " + height + " time stamp: " + timeStamp +
                                " buffer: " + buffer.capacity());

                        //you can save buffer into file to see image's content
//                        try {
//                            FileOutputStream os = new FileOutputStream("/sdcard/DCIM/"+timeStamp+".jpg");
//                            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//                            bmp.copyPixelsFromBuffer(buffer);
//                            bmp.compress(Bitmap.CompressFormat.JPEG,100,os);
//                            os.close();
//                        }catch (IOException e) {
//                            e.printStackTrace();
//                        }

                        //if you want do something in NDK, you can do like this
//                        extern "C" JNIEXPORT void JNICALL Java_***_***_***_callback(
//                                JNIEnv* env, jobject clazz, jint width, jint height, jlong timeStamp, jobject buffer)
//                        {
//                            unsigned char* buffer = (unsigned char*)env->GetDirectBufferAddress(buffer);
//                            if (!buffer)
//                            {
//                                ...
//                            }
//                        }
                    }
                });
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
