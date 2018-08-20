# DemoCamera
this is a simple demo that show you how to first use pilot sdk.

1.create your own android project.

2.copy PilotSDK\DemoCamera\libs\SDKPano-release.aar to your project.

3.use SDKPano-release.aar in build.gradle

~~~ 
dependencies 
{
    implementation files('libs/SDKPano-release.aar')
}
~~~

4.make sure add android:largeHeap="true" in your AndroidManifest.xml, or you application may be memory out when you take 8k photo.

~~~
<application
    android:allowBackup="true"
    android:icon="@drawable/ic_launcher_camera"
    android:label="@string/app_name"
    android:largeHeap="true"
    android:supportsRtl="true"
    android:uiOptions="splitActionBarWhenNarrow">

    <activity
        android:name=".CameraActivity"
        android:configChanges="orientation|keyboardHidden|screenSize"
        android:label="@string/app_name"
        android:screenOrientation="portrait"
        android:launchMode="singleTask">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>
</application>
 ~~~
 
 5.create your own layout, add a FrameLayout, this FrameLayout is parent view of pilot preview view.
 
 6.add code frameLayout is parent view of pilot preview view.
 
 ~~~
 new PilotSDK(this, frameLayout, true, new PanoSDKListener())
 ~~~
