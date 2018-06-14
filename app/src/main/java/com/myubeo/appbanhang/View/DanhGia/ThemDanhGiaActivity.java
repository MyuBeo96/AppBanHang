package com.myubeo.appbanhang.View.DanhGia;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.myubeo.appbanhang.Model.ObjectClass.DanhGia;
import com.myubeo.appbanhang.Presenter.DanhGia.PresenterDanhGia;
import com.myubeo.appbanhang.R;

public class ThemDanhGiaActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener, ViewDanhGia, View.OnClickListener {

    TextInputLayout iput_TieuDe;
    TextInputLayout iput_NoiDung;
    EditText edt_TieuDe;
    EditText edt_NoiDung;
    RatingBar rb_SaoDanhGia;
    Button btn_DanhGia;

    int masp = 0;
    int sosao = 0;

    PresenterDanhGia presenterDanhGia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhgia);

        iput_TieuDe = findViewById(R.id.iput_tieude);
        iput_NoiDung = findViewById(R.id.iput_noidung);
        edt_TieuDe = findViewById(R.id.edt_TieuDe);
        edt_NoiDung = findViewById(R.id.edt_NoiDung);
        rb_SaoDanhGia = findViewById(R.id.rb_SaoDanhGia);
        btn_DanhGia = findViewById(R.id.btn_DanhGia);

        masp = getIntent().getIntExtra("masp", 0);

        presenterDanhGia = new PresenterDanhGia(this);

        rb_SaoDanhGia.setOnRatingBarChangeListener(this);
        btn_DanhGia.setOnClickListener(this);

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        sosao = (int) rating;
    }

    @Override
    public void DanhGiaThanhCong() {
        Toast.makeText(this, "Đánh giá thành công.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void DanhGiaThatBai() {
        Toast.makeText(this, "Đánh giá thất bại!!!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

        }
        String madg = telephonyManager.getDeviceId();
        String tenthietbi = Build.MODEL;
        String tieude = edt_TieuDe.getText().toString();
        String noidung = edt_NoiDung.getText().toString();

        if(tieude.trim().length() > 0){
            iput_TieuDe.setErrorEnabled(false);
            iput_TieuDe.setError("");
        }else {
            iput_TieuDe.setErrorEnabled(true);
            iput_TieuDe.setError("Bạn chưa nhập tiêu đề");
        }

        if(noidung.trim().length() > 0){
            iput_NoiDung.setErrorEnabled(false);
            iput_NoiDung.setError("");
        }else {
            iput_NoiDung.setErrorEnabled(true);
            iput_NoiDung.setError("Bạn chưa nhập nội dung");
        }

        if(!iput_TieuDe.isErrorEnabled() && !iput_NoiDung.isErrorEnabled()){
            DanhGia danhGia = new DanhGia();
            danhGia.setMADG(madg);
            danhGia.setMASP(masp);
            danhGia.setTENTHIETBI(tenthietbi);
            danhGia.setTIEUDE(tieude);
            danhGia.setNOIDUNG(noidung);
            danhGia.setSOSAO(sosao);

            presenterDanhGia.ThemDanhGia(danhGia);
            finish();

        }
    }
}
