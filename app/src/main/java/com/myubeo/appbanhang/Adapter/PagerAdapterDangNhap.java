package com.myubeo.appbanhang.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.BaseExpandableListAdapter;

import com.myubeo.appbanhang.View.DangNhap.Fragment.FragmentDangKy;
import com.myubeo.appbanhang.View.DangNhap.Fragment.FragmentDangNhap;

/**
 * Created by as1 on 3/31/2018.
 */

public class PagerAdapterDangNhap extends FragmentPagerAdapter{

    public PagerAdapterDangNhap(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentDangNhap fragmentDangNhap = new FragmentDangNhap();
                return  fragmentDangNhap;

            case 1:
                FragmentDangKy fragmentDangKy = new FragmentDangKy();
                return  fragmentDangKy;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return  "Đăng nhập";

            case 1:
                return  "Đăng ký";

            default:
                return null;
        }
    }
}
