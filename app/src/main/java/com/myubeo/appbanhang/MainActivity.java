package com.myubeo.appbanhang;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MainActivity extends AbsRuntime {

    private static final int REQUEST_PERMISSION = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        requestAppPermissions(new String[] {
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_CONTACTS},
                R.string.msg,REQUEST_PERMISSION);
    }

    @Override
    public void onPermissionsGrade(int requestCode) {
        Toast.makeText(getApplicationContext(), "hihi", Toast.LENGTH_LONG).show();
    }
}
