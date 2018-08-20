# PilotSDK
PilotSDK is used for pilot era application development

## build and run

0. first, you need a [pilot era](http://pilot.pisofttech.com/) 

1. check out this project

2. open project with android studio

3. connect pilot era to your PC

4. open pilot era's developer mode

   hold left and right button for 10 second, you can see android lanucher,
   then settings->about phone->click multi times build number,the you can
   see developer options

4. run

## DemoCamera
this is a simple demo that show you how to first use pilot sdk.

## DemoIMU
PilotOS is based on Android, so if you want to get motion sensors data, you can refer to [android developer website](https://developer.android.google.cn/guide/topics/sensors/sensors_motion), but there is a little different between Pilot SDK and Android SDK, [DemoIMU](https://github.com/shenzhenpisofttech/PilotSDK/tree/master/DemoIMU) show this difference.

## 如何从图片中获取指南针数据
指南针数据存储在jpeg图片中的XMP元数据中,XMP元数据在jpeg格式的APP1字段中,因此,获取并解析XMP元数据即可获取指南针数据.
pilot拍照生成的jpeg文件中的XMP元数据类似以下格式,其中GPano:PoseHeadingDegrees字段即指南针数值,其范围是0到360,这个值代表展开图的中心点所对应的方向,0代表北,90代表东,180代表南,270代表西.

~~~
<x:xmpmeta xmlns:x="adobe:ns:meta/" x:xmptk="Adobe XMP Core 5.1.0-jc003">
<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
<rdf:Description rdf:about=""
xmlns:GPano="http://ns.google.com/photos/1.0/panorama/"
GPano:CroppedAreaImageWidthPixels="8192"
GPano:CroppedAreaImageHeightPixels="4048"
GPano:FullPanoWidthPixels="8192"
GPano:FullPanoHeightPixels="4048"
GPano:CroppedAreaLeftPixels="0"
GPano:CroppedAreaTopPixels="0"
GPano:ProjectionType="equirectangular"
GPano:UsePanoramaViewer="True"
GPano:PiStitchParam="PE0002c00000807f0396..............."
GPano:PoseHeadingDegrees="180.105"/>
</rdf:RDF>
</x:xmpmeta>
~~~
