package com.myubeo.appbanhang.Presenter.ChiTietSanPham;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.myubeo.appbanhang.R;
import com.squareup.picasso.Picasso;

public class FragmentSliderChiTietSanPham extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment_slider_chitietsanpham, container,false);

        Bundle bundle = getArguments();
        String linkAnh = bundle.getString("linkAnh");

        ImageView imageView = view.findViewById(R.id.img_HinhSlider);

        Picasso.get().load(linkAnh).into(imageView);

        return view;
    }
}
