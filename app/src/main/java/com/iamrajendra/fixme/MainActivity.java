package com.iamrajendra.fixme;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.iamrajendra.codenamefixme.AppInfo;
import com.iamrajendra.codenamefixme.FixMe;
import com.iamrajendra.codenamefixme.MobileInfo;
import com.iamrajendra.codenamefixme.Shake;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.single.PermissionListener;

public class MainActivity extends AppCompatActivity implements PermissionListener, PermissionRequestErrorListener {

private FixMe fixMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
fixMe  = new FixMe(this);
fixMe.resume();
fixMe.setMobileInfo(getMobileInfo());
fixMe.setAppInfo(getAppInfo());
        new Thread(new Runnable() {
            @Override public void run() {
                Dexter.withActivity(MainActivity.this)
                        .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .withListener(MainActivity.this)
                        .withErrorListener(MainActivity.this)
                        .onSameThread()
                        .check();
            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(fixMe!=null)fixMe.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(fixMe!=null)fixMe.pause();
    }

    private MobileInfo getMobileInfo() {
        return new MobileInfo().setManufacturerName(Build.MANUFACTURER)
                .setBrand(Build.BRAND)
                .setBoard(Build.BOARD)
                .setHardware(Build.HARDWARE)
                .setSerial(Build.SERIAL).setOSVersion(android.os.Build.VERSION.SDK_INT);
    }

    private AppInfo getAppInfo() {
        String name = null;
        int no=-1;
        try {

            PackageInfo pInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
            name = pInfo.versionName;
            no = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return new AppInfo().setAppName(getResources().getString(R.string.app_name))
                .setPackageName("com.iamrajendra.fixme")
                .setVersionName(name).setVersionNumber(no);
    }

    @Override
    public void onPermissionGranted(PermissionGrantedResponse response) {


    }

    @Override
    public void onPermissionDenied(PermissionDeniedResponse response) {

    }

    @Override
    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

    }

    @Override
    public void onError(DexterError error) {

    }
}
