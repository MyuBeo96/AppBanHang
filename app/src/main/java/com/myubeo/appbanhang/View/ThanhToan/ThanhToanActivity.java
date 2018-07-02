package com.myubeo.appbanhang.View.ThanhToan;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.myubeo.appbanhang.Model.ObjectClass.ChiTietHoaDon;
import com.myubeo.appbanhang.Model.ObjectClass.HoaDon;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.Presenter.ThanhToan.PresenterThanhToan;
import com.myubeo.appbanhang.R;
import com.myubeo.appbanhang.View.TrangChu.TrangChuActivity;

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
    TextView txt_ThanhToanTanNoi;
    TextView txt_ThanhToanThe;

    PresenterThanhToan presenterThanhToan;

    List<ChiTietHoaDon> chiTietHoaDonList = new ArrayList<>();

    int hinhThucThanhToan = 0;

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
        txt_ThanhToanTanNoi = findViewById(R.id.txt_ThanhToanTanNoi);
        txt_ThanhToanThe = findViewById(R.id.txt_ThanhToanThe);

        presenterThanhToan = new PresenterThanhToan(this, this);
        presenterThanhToan.LayDanhSachSPGioHang();

        setSupportActionBar(toolbar);

        btn_thanhtoan.setOnClickListener(this);
        btn_ship.setOnClickListener(this);
        btn_visa.setOnClickListener(this);
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
                        hoaDon.setCHUYENKHOAN(hinhThucThanhToan);
                        hoaDon.setChiTietHoaDons(chiTietHoaDonList);

                        presenterThanhToan.ThemHoaDon(hoaDon);
                    }else {
                        Toast.makeText(this, "Bạn chưa đồng ý với điều khoản.", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(this, "Bạn chưa nhập đầy đủ thông tin.", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.btn_visa:
                ChonHinhThucThanhToan(txt_ThanhToanThe, txt_ThanhToanTanNoi);
                hinhThucThanhToan = 1;
                break;

            case R.id.btn_ship:
                ChonHinhThucThanhToan(txt_ThanhToanTanNoi, txt_ThanhToanThe);
                hinhThucThanhToan = 0;
                break;
        }
    }

    private void ChonHinhThucThanhToan(TextView txt_DuocChon, TextView txt_KhongDuocChon){
        txt_DuocChon.setTextColor(getIdColor(R.color.colorFacebook));
        txt_KhongDuocChon.setTextColor(getIdColor(R.color.colorGray));
    }

    private int getIdColor(int idColor){
        int color = 0;

        if(Build.VERSION.SDK_INT > 21){
            color = ContextCompat.getColor(this, idColor);
        }else {
            color = getResources().getColor(idColor);
        }

        return color;
    }

    @Override
    public void DatHangThanhCong() {
        Toast.makeText(this, "Đặt hàng thành công", Toast.LENGTH_LONG).show();
        Intent iTrangChu = new Intent(ThanhToanActivity.this, TrangChuActivity.class);
        startActivity(iTrangChu);
    }

    @Override
    public void DatHangThatBai() {
        Toast.makeText(this, "Đặt hàng thất bại", Toast.LENGTH_LONG).show();
    }
}
