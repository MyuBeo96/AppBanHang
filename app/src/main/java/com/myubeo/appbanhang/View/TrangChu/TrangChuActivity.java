package com.myubeo.appbanhang.View.TrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.myubeo.appbanhang.Adapter.ExpandableAdapter;
import com.myubeo.appbanhang.Adapter.ViewPagerAdapter;
import com.myubeo.appbanhang.Model.ObjectClass.LoaiSanPham;
import com.myubeo.appbanhang.Presenter.TrangChu.XuLyMenu.XuLyMenuLogic;
import com.myubeo.appbanhang.R;
import com.myubeo.appbanhang.View.DangNhap.DangNhapActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by as1 on 3/27/2018.
 */

public class TrangChuActivity extends AppCompatActivity implements XuLyMenuView{
    public static final String SERVER_NAME = "http://192.168.1.7/webLazada/loaisanpham.php";
//    public static final String SERVER= "http://192.168.43.79:8080/webLazada";
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView expandableListView;
    XuLyMenuLogic xuLyMenuLogic;
    String useName = "";
    AccessToken accessToken;
    Menu menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_trangchu);

        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.id_TabLayout);
        viewPager = findViewById(R.id.id_ViewPager);
        drawerLayout = findViewById(R.id.id_DrawerLayout);
        expandableListView = findViewById(R.id.ep_Menu);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        xuLyMenuLogic = new XuLyMenuLogic(this);
        xuLyMenuLogic.LayDanhSachMenu();
        xuLyMenuLogic.LayUseFaceBook();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        this.menu = menu;

        accessToken = xuLyMenuLogic.LayUseFaceBook();

        if(accessToken != null){
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        useName = object.getString("name");
                        MenuItem menuItem = menu.findItem(R.id.id_DangNhap);
                        menuItem.setTitle(useName);
                        Log.d("Kiểm tra", useName);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            Bundle parameters = new Bundle();
            parameters.putString("fields", "name");

            graphRequest.setParameters(parameters);
            graphRequest.executeAsync();
        }


        if(accessToken != null){
            MenuItem itemLogout = menu.findItem(R.id.id_Logout);
            itemLogout.setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        int id = item.getItemId();
        switch (id){
            case R.id.id_DangNhap:
                if(accessToken == null){
                    Intent itDangNhap = new Intent(this, DangNhapActivity.class);
                    startActivity(itDangNhap);
                }
                break;

            case R.id.id_Logout:
                if(accessToken != null){
                    LoginManager.getInstance().logOut();
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }

        }

        return true;
    }

    @Override
    public void HienThiDanhSachMenu(List<LoaiSanPham> loaiSanPhamList) {
        ExpandableAdapter expandableAdapter = new ExpandableAdapter(this, loaiSanPhamList);
        expandableListView.setAdapter(expandableAdapter);
        expandableAdapter.notifyDataSetChanged();
    }
}