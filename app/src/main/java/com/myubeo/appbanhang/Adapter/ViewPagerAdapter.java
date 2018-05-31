package com.myubeo.appbanhang.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.myubeo.appbanhang.View.TrangChu.Fragement.ChuongTrinhKMFragment;
import com.myubeo.appbanhang.View.TrangChu.Fragement.DienTuFragment;
import com.myubeo.appbanhang.View.TrangChu.Fragement.LamDepFragment;
import com.myubeo.appbanhang.View.TrangChu.Fragement.MeBeFragment;
import com.myubeo.appbanhang.View.TrangChu.Fragement.NhaCuaDoiSongFragment;
import com.myubeo.appbanhang.View.TrangChu.Fragement.NoiBatFragment;
import com.myubeo.appbanhang.View.TrangChu.Fragement.TheThaoDuLichFragment;
import com.myubeo.appbanhang.View.TrangChu.Fragement.ThoiTrangFragment;
import com.myubeo.appbanhang.View.TrangChu.Fragement.ThuongHieuFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by as1 on 3/28/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> listFragment = new ArrayList<Fragment>();
    List<String> titleFragment = new ArrayList<String>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        listFragment.add(new DienTuFragment());
        listFragment.add(new NoiBatFragment());
        listFragment.add(new ChuongTrinhKMFragment());
//        listFragment.add(new NhaCuaDoiSongFragment());
//        listFragment.add(new MeBeFragment());
//        listFragment.add(new LamDepFragment());
//        listFragment.add(new ThoiTrangFragment());
//        listFragment.add(new TheThaoDuLichFragment());
//        listFragment.add(new ThuongHieuFragment());

        titleFragment.add("Điện tử");
        titleFragment.add("Nổi bật");
        titleFragment.add("Chương trình khuyến mại");
//        titleFragment.add("Nhà cửa & đời sống");
//        titleFragment.add("Mẹ & bé");
//        titleFragment.add("Làm đẹp");
//        titleFragment.add("Thời trang");
//        titleFragment.add("Thể thao & du lịch");
//        titleFragment.add("Thương hiệu");

    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragment.get(position);
    }
}
