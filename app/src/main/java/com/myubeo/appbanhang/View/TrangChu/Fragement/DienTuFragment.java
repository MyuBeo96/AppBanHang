package com.myubeo.appbanhang.View.TrangChu.Fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myubeo.appbanhang.Adapter.AdapterDienTu;
import com.myubeo.appbanhang.Adapter.AdapterLogoThuongHieu;
import com.myubeo.appbanhang.Adapter.AdapterTopMayTinh;
import com.myubeo.appbanhang.Model.ObjectClass.DienTu;
import com.myubeo.appbanhang.Model.ObjectClass.SanPham;
import com.myubeo.appbanhang.Model.ObjectClass.ThuongHieu;
import com.myubeo.appbanhang.Presenter.TrangChuDienTu.PresenterDienTu;
import com.myubeo.appbanhang.R;
import com.myubeo.appbanhang.View.TrangChu.ViewDienTu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by as1 on 3/28/2018.
 */

public class DienTuFragment extends Fragment implements ViewDienTu{

    RecyclerView recyclerView;
    RecyclerView rcw_logothuonghieu;
    RecyclerView rcw_hangmoive;
    List<DienTu> dienTuList;
    PresenterDienTu presenterDienTu;
    ImageView img_sanpham1;
    ImageView img_sanpham2;
    ImageView img_sanpham3;
    TextView txt_sanpham1;
    TextView txt_sanpham2;
    TextView txt_sanpham3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dientu, container, false);

        recyclerView = view.findViewById(R.id.rv_dientu);
        rcw_logothuonghieu = view.findViewById(R.id.rcw_logothuonghieu);
        rcw_hangmoive = view.findViewById(R.id.rcw_hangmoive);
        img_sanpham1 = view.findViewById(R.id.img_sanpham1);
        img_sanpham2 = view.findViewById(R.id.img_sanpham2);
        img_sanpham3 = view.findViewById(R.id.img_sanpham3);
        txt_sanpham1 = view.findViewById(R.id.txt_sanpham1);
        txt_sanpham2 = view.findViewById(R.id.txt_sanpham2);
        txt_sanpham3 = view.findViewById(R.id.txt_sanpham3);

        presenterDienTu = new PresenterDienTu(this);
        dienTuList = new ArrayList<>();

        presenterDienTu.LayDanhSachDienTu();
        presenterDienTu.LayDanhSachLogoThuongHieu();
        presenterDienTu.LayDanhSachSanPhamMoiVe();

        return view;
    }

    @Override
    public void HienThiDanhSach(List<DienTu> dienTuList) {
        dienTuList = dienTuList;

        AdapterDienTu adapterDienTu = new AdapterDienTu(getContext(), dienTuList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDienTu);

        adapterDienTu.notifyDataSetChanged();

    }

    @Override
    public void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieuList) {

        AdapterLogoThuongHieu adapterLogoThuongHieu = new AdapterLogoThuongHieu(getContext(), thuongHieuList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);

        rcw_logothuonghieu.setLayoutManager(layoutManager);
        rcw_logothuonghieu.setAdapter(adapterLogoThuongHieu);

        adapterLogoThuongHieu.notifyDataSetChanged();
    }

    @Override
    public void HienThiDanhSachSPMoiVe(List<SanPham> sanPhamList) {
        AdapterTopMayTinh adapterTopMayTinh = new AdapterTopMayTinh(getContext(), R.layout.custom_recyclerview_laptop, sanPhamList);

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        rcw_hangmoive.setLayoutManager(layoutManager1);
        rcw_hangmoive.setAdapter(adapterTopMayTinh);

        adapterTopMayTinh.notifyDataSetChanged();

        Random random = new Random();

        int vitri = random.nextInt(sanPhamList.size());
        Picasso.get().load(sanPhamList.get(vitri).getHINHLON()).fit().centerInside().into(img_sanpham1);
        txt_sanpham1.setText(sanPhamList.get(vitri).getTENSP());

        int vitri1 = random.nextInt(sanPhamList.size());
        Picasso.get().load(sanPhamList.get(vitri1).getHINHLON()).fit().centerInside().into(img_sanpham2);
        txt_sanpham2.setText(sanPhamList.get(vitri1).getTENSP());

        int vitri2 = random.nextInt(sanPhamList.size());
        Picasso.get().load(sanPhamList.get(vitri2).getHINHLON()).fit().centerInside().into(img_sanpham3);
        txt_sanpham3.setText(sanPhamList.get(vitri2).getTENSP());

    }
}
