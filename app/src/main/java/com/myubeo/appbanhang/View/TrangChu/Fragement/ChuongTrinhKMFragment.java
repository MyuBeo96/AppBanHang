package com.myubeo.appbanhang.View.TrangChu.Fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.myubeo.appbanhang.Adapter.AdapterKhuyenMai;
import com.myubeo.appbanhang.Model.ObjectClass.KhuyenMai;
import com.myubeo.appbanhang.Presenter.KhuyenMai.PresenterKhuyenMai;
import com.myubeo.appbanhang.R;
import com.myubeo.appbanhang.View.TrangChu.ViewKhuyenMai;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by as1 on 3/28/2018.
 */

public class ChuongTrinhKMFragment extends Fragment implements ViewKhuyenMai{

    LinearLayout ln_HinhKhuyenMai;
    RecyclerView rcv_DanhSachKhuyenMai;
    PresenterKhuyenMai presenterKhuyenMai;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_chuongtrinhkm, container, false);

        ln_HinhKhuyenMai = view.findViewById(R.id.ln_HinhKhuyenMai);
        rcv_DanhSachKhuyenMai = view.findViewById(R.id.rcv_DanhSachKhuyenMai);

        presenterKhuyenMai = new PresenterKhuyenMai(this);
        presenterKhuyenMai.LayDanhSachKhuyenMai();

        return view;
    }

    @Override
    public void HienThiDanhSachKhuyenMai(List<KhuyenMai> khuyenMaiList) {

        int demHinh = khuyenMaiList.size();
        if(demHinh >= 5){
            demHinh = 5;
        }else {
            demHinh = khuyenMaiList.size();
        }

        ln_HinhKhuyenMai.removeAllViews();
        for(int i = 0; i < demHinh; i++){
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 370);
            layoutParams.setMargins(0,10,0,10);
            imageView.setLayoutParams(layoutParams);

            Picasso.get().load(khuyenMaiList.get(i).getHINHKHUYENMAI()).resize(720,370).into(imageView);

            ln_HinhKhuyenMai.addView(imageView);
        }

        AdapterKhuyenMai adapterKhuyenMai = new AdapterKhuyenMai(getContext(), khuyenMaiList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        rcv_DanhSachKhuyenMai.setLayoutManager(layoutManager);
        rcv_DanhSachKhuyenMai.setAdapter(adapterKhuyenMai);
        adapterKhuyenMai.notifyDataSetChanged();

    }
}
