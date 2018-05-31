package com.myubeo.appbanhang.Adapter;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.myubeo.appbanhang.Model.ObjectClass.LoaiSanPham;
import com.myubeo.appbanhang.Model.TrangChu.XuLyJSONMenu;
import com.myubeo.appbanhang.R;

import java.util.List;

/**
 * Created by as1 on 3/30/2018.
 */

public class ExpandableAdapter extends BaseExpandableListAdapter {

    Context context;
    List<LoaiSanPham> loaiSanPhamList;

    public ExpandableAdapter(Context context, List<LoaiSanPham> loaiSanPhamList){
        this.context = context;
        this.loaiSanPhamList = loaiSanPhamList;

        XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
        int count = loaiSanPhamList.size();
        for (int i = 0; i < count; i++){
            int maLoaiSP = loaiSanPhamList.get(i).getMALOAISP();
            loaiSanPhamList.get(i).setLoaiSanPhams(xuLyJSONMenu.LayDanhSachSP(maLoaiSP));
        }
    }

    @Override
    public int getGroupCount() {
        return loaiSanPhamList.size();
    }

    @Override
    public int getChildrenCount(int viTriCha) {
        if(loaiSanPhamList.get(viTriCha).getLoaiSanPhams().size() != 0){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int viTriCha) {
        return loaiSanPhamList.get(viTriCha);
    }

    @Override
    public Object getChild(int viTriCha, int viTriCon) {
        return loaiSanPhamList.get(viTriCha).getLoaiSanPhams().get(viTriCon);
    }

    @Override
    public long getGroupId(int viTriCha) {
        return loaiSanPhamList.get(viTriCha).getMALOAISP();
    }

    @Override
    public long getChildId(int viTriCha, int viTriCon) {
        return loaiSanPhamList.get(viTriCha).getLoaiSanPhams().get(viTriCon).getMALOAISP();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int viTriCha, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewGroupCha = layoutInflater.inflate(R.layout.custom_groupcha, parent, false);

        TextView txtTenLoaiSP = viewGroupCha.findViewById(R.id.txt_TenLoaiSP);
        ImageView imageView = viewGroupCha.findViewById(R.id.im_MenuPlus);

        txtTenLoaiSP.setText(loaiSanPhamList.get(viTriCha).getTENLOAISP());
        int dem = loaiSanPhamList.get(viTriCha).getLoaiSanPhams().size();

        if(dem > 0){
            imageView.setVisibility(View.VISIBLE);
        }else {
            imageView.setVisibility(View.INVISIBLE);
        }

        if(isExpanded){
            imageView.setImageResource(R.drawable.ic_remove_black);
            viewGroupCha.setBackgroundResource(R.color.colorGray);
        }else {
            imageView.setImageResource(R.drawable.ic_add_black);
        }

        viewGroupCha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        return viewGroupCha;
    }

    @Override
    public View getChildView(int viTriCha, int viTriCon, boolean isExpanded, View convertView, ViewGroup parent) {
//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View viewGroupChilden = layoutInflater.inflate(R.layout.custom_groupchilden, parent, false);
//
//        ExpandableListView expandableListView = viewGroupChilden.findViewById(R.id.ep_Childen);
        SecondExpandable secondExpandable = new SecondExpandable(context);
        ExpandableAdapter secondExpandableAdapter = new ExpandableAdapter(context, loaiSanPhamList.get(viTriCha).getLoaiSanPhams());
        secondExpandable.setAdapter(secondExpandableAdapter);

        secondExpandable.setGroupIndicator(null);
        notifyDataSetChanged();

        return secondExpandable;
    }

    public class SecondExpandable extends ExpandableListView {
        public SecondExpandable(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            int width = size.x;
            int height = size.y;

//            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean isChildSelectable(int viTriCha, int viTriCon) {
        return false;
    }
}
