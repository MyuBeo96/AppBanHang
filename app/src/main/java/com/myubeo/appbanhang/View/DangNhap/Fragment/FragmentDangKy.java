package com.myubeo.appbanhang.View.DangNhap.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myubeo.appbanhang.R;

/**
 * Created by as1 on 3/31/2018.
 */

public class FragmentDangKy extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dangky, container, false);
        return view;
    }
}
