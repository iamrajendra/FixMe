package com.iamrajendra.codenamefixme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.net.ContentHandler;
import java.util.Date;

public class FixMe implements ShakeListener {
    private Activity activity;
    private Shake shake;
    private FixMeDialog fixMeDialog;
    private Uri uri;
    private AppInfo appInfo;
    private  MobileInfo mobileInfo;

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public void setMobileInfo(MobileInfo mobileInfo) {
        this.mobileInfo = mobileInfo;
    }

    public FixMe(final Activity activity) {
        this.activity = activity;
        shake = new Shake(activity);
        shake.resume();
        shake.setListener(this);


        fixMeDialog = new FixMeDialog() {
            @Override
            public void pleaseFixMe() {

                String bodyMessage = "Device Inform \n\n"
                        +"Company Name "+mobileInfo.getManufacturer()+
                        "\nOs API Version "+mobileInfo.getOsVersion()+"\n\n"+"App Info \n\n" +
                        "App Name :"+appInfo.getAppName()+"\n Package Name "+appInfo.getPackageName()+"\nApp Version"+appInfo.getVersionName();


                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:rajendra@walkover.in"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, appInfo.getAppName()+" Bug fixes");
                emailIntent.putExtra(Intent.EXTRA_TEXT, bodyMessage);
                try {
                    activity.  startActivity(Intent.createChooser(emailIntent, "Send mail..."));


                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(activity, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public void onShake() {

        fixMeDialog.show(activity);
        takeScreenshot();
    }
    public void  resume(){

       if(shake!=null) shake.resume();
    }
    public void  pause(){

        if(shake!=null) {
            shake.onPause();

        }
    }


    private void takeScreenshot() {

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + "rw" + ".jpg";

            // create bitmap screen capture
            View v1 = activity.getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();
            uri = Uri.fromFile(imageFile);

        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }


    }

    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        activity.startActivity(intent);
    }
}
