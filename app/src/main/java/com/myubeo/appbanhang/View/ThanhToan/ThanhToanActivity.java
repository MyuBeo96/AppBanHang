package com.myubeo.appbanhang.View.ThanhToan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.myubeo.appbanhang.Model.ObjectClass.ChiTietHoaDon;
import com.myubeo.appbanhang.Model.ObjectClass.HoaDon;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.Presenter.ThanhToan.PresenterThanhToan;
import com.myubeo.appbanhang.R;

import java.util.ArrayList;
import java.util.List;

public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener, ViewThanhToan {

    Toolbar toolbar;
    EditText txt_TenNguoiNhan;
    EditText txt_DiaChiNguoiNhan;
    EditText txt_SoDienThoai;
    ImageButton btn_ship;
    ImageButton btn_visa;
    CheckBox cb_camket;
    Button btn_thanhtoan;

    PresenterThanhToan presenterThanhToan;

    List<ChiTietHoaDon> chiTietHoaDonList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_thanhtoan);

        toolbar = findViewById(R.id.toolbar);
        txt_TenNguoiNhan = findViewById(R.id.txt_TenNguoiNhan);
        txt_DiaChiNguoiNhan = findViewById(R.id.txt_DiaChiNguoiNhan);
        txt_SoDienThoai = findViewById(R.id.txt_SoDienThoai);
        btn_ship = findViewById(R.id.btn_ship);
        btn_visa = findViewById(R.id.btn_visa);
        cb_camket = findViewById(R.id.cb_camket);
        btn_thanhtoan = findViewById(R.id.btn_thanhtoan);

        presenterThanhToan = new PresenterThanhToan(this);
        presenterThanhToan.LayDanhSachSPGioHang(this);

        setSupportActionBar(toolbar);

        btn_thanhtoan.setOnClickListener(this);
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(List<SanPham> sanPhams) {

        for (int i = 0; i < sanPhams.size(); i++){
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.setMASP(sanPhams.get(i).getMASP());
            chiTietHoaDon.setSOLUONG(sanPhams.get(i).getSOLUONG());

            chiTietHoaDonList.add(chiTietHoaDon);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_thanhtoan:
                String tennguoinhan = txt_TenNguoiNhan.getText().toString();
                String sodt = txt_SoDienThoai.getText().toString();
                String diachi = txt_DiaChiNguoiNhan.getText().toString();

                if(tennguoinhan.trim().length() > 0 && sodt.trim().length() > 0 && diachi.trim().length() > 0){
                    if(cb_camket.isChecked()){
                        HoaDon hoaDon = new HoaDon();
                        hoaDon.setTENNGUOINHAN(tennguoinhan);
                        hoaDon.setSODIENTHOAI(sodt);
                        hoaDon.setDIACHI(diachi);
                        hoaDon.setChiTietHoaDons(chiTietHoaDonList);

                        presenterThanhToan.ThemHoaDon(hoaDon);
                    }else {
                        Toast.makeText(this, "Bạn chưa đồng ý với điều khoản.", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(this, "Bạn chưa nhập đầy đủ thông tin.", Toast.LENGTH_LONG).show();
                }

                break;
        }
    }

    @Override
    public void DatHangThanhCong() {
        Toast.makeText(this, "Đặt hàng thành công", Toast.LENGTH_LONG).show();
    }

    @Override
    public void DatHangThatBai() {
        Toast.makeText(this, "Đặt hàng thất bại", Toast.LENGTH_LONG).show();
    }
}
