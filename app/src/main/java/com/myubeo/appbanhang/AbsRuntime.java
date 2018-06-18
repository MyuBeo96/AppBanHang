package com.myubeo.appbanhang;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.SparseIntArray;
import android.view.View;

public abstract class AbsRuntime extends Activity{

    private SparseIntArray mIntArray;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIntArray = new SparseIntArray();
    }

    public abstract void onPermissionsGrade(int requestCode);

    public void requestAppPermissions(final String[]requestedPermissions, final int stringId, final int requestCode){
        mIntArray.put(requestCode, stringId);

        int permissionsCheck = PackageManager.PERMISSION_GRANTED;
        boolean showRequestPermissions = false;
        for (String permission: requestedPermissions){
            permissionsCheck = permissionsCheck + ContextCompat.checkSelfPermission(this, permission);
            showRequestPermissions = showRequestPermissions || ActivityCompat.shouldShowRequestPermissionRationale(this, permission);

        }

        if(permissionsCheck!=PackageManager.PERMISSION_GRANTED){
            if(showRequestPermissions){
                Snackbar.make(findViewById(android.R.id.content), stringId, Snackbar.LENGTH_INDEFINITE).setAction("GRANT", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.requestPermissions(AbsRuntime.this, requestedPermissions, requestCode);
                    }
                }).show();
            }else {
                ActivityCompat.requestPermissions(this, requestedPermissions, requestCode);
            }
        }else {
            onPermissionsGrade(requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int permisson : grantResults){
            permissionCheck = permissionCheck + permisson;
        }

        if(grantResults.length > 0 && PackageManager.PERMISSION_GRANTED == permissionCheck){
            onPermissionsGrade(requestCode);
        }else {
            Snackbar.make(findViewById(android.R.id.content), mIntArray.get(requestCode), Snackbar.LENGTH_INDEFINITE).setAction("ENABLE", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent();
                    i.setAction(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                    i.setData(Uri.parse("package: "+getPackageName()));
                    i.addCategory(Intent.CATEGORY_DEFAULT);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    startActivity(i);
                }
            }).show();
        }
    }
}
