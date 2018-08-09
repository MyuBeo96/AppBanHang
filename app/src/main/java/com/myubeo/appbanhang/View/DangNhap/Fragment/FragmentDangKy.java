package com.myubeo.appbanhang.View.DangNhap.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.myubeo.appbanhang.CustomView.CustomPassword;
import com.myubeo.appbanhang.Model.ObjectClass.NhanVien;
import com.myubeo.appbanhang.Presenter.DangKy.PresenterDangKy;
import com.myubeo.appbanhang.R;
import com.myubeo.appbanhang.View.DangNhap.ViewDangKy;

/**
 * Created by as1 on 3/31/2018.
 */

public class FragmentDangKy extends Fragment implements ViewDangKy, View.OnClickListener{

    PresenterDangKy presenterDangKy;
    String sendEmail = "";

    Button btn_dangky;
    EditText et_hoTen;
    EditText et_email;
    EditText et_password;
    EditText et_nhapLaiPassword;
    SwitchCompat scp_sendemail;
    TextInputEditText tip_hoTen;
    TextInputEditText tip_email;
    TextInputEditText tip_password;
    TextInputEditText tip_nhapLaiPassword;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dangky, container, false);

        btn_dangky = view.findViewById(R.id.btn_dangky);
        et_hoTen = view.findViewById(R.id.et_hoTen);
        et_email = view.findViewById(R.id.et_email);
        et_password = view.findViewById(R.id.et_password);
        et_nhapLaiPassword = view.findViewById(R.id.et_nhapLaiPassword);
        scp_sendemail = view.findViewById(R.id.scp_sendemail);
        tip_hoTen = view.findViewById(R.id.tip_hoTen);
        tip_email = view.findViewById(R.id.tip_email);
        tip_password = view.findViewById(R.id.tip_password);
        tip_nhapLaiPassword = view.findViewById(R.id.tip_nhapLaiPassword);

        presenterDangKy = new PresenterDangKy(this);

        btn_dangky.setOnClickListener(this);

        return view;
    }

    @Override
    public void DangKyThanhCong() {

    }

    @Override
    public void DangKyThatBai() {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_dangky:
                break;

                default:
                    break;
        }
    }

    private void btn_dangky(){
        String hoTen = et_hoTen.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        String nhapLaiPassword = et_nhapLaiPassword.getText().toString();

        scp_sendemail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sendEmail = b + "";
            }
        });

        if(nhapLaiPassword.equalsIgnoreCase(password)){
//            tip_nhapLaiPassword.setErrorEnabled(true);
            tip_nhapLaiPassword.setError("Mật khẩu không trùng khớp");
        }

        NhanVien nhanVien = new NhanVien();
        nhanVien.setTENNV(hoTen);
        nhanVien.setTENDANGNHAP(email);
        nhanVien.setMATKHAU(password);
        nhanVien.setSENDEMAIL(sendEmail);
        nhanVien.setMALOAINV(7);
    }
}
