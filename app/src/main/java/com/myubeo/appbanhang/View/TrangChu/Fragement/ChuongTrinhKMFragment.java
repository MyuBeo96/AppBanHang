package com.myubeo.appbanhang.View.TrangChu.Fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myubeo.appbanhang.R;

/**
 * Created by as1 on 3/28/2018.
 */

public class ChuongTrinhKMFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_chuongtrinhkm, container, false);
        return view;
    }
}
