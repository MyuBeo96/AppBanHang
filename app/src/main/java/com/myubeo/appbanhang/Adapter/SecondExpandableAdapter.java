package com.myubeo.appbanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.myubeo.appbanhang.Model.ObjectClass.LoaiSanPham;
import com.myubeo.appbanhang.Model.TrangChu.XuLyJSONMenu;
import com.myubeo.appbanhang.R;

import java.util.List;

/**
 * Created by as1 on 3/30/2018.
 */

public class SecondExpandableAdapter extends BaseExpandableListAdapter {

    Context context;
    List<LoaiSanPham> sanPhamList;

    public SecondExpandableAdapter(Context context, List<LoaiSanPham> sanPhamList){
        this.context = context;
        this.sanPhamList = sanPhamList;

        XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
        int count = sanPhamList.size();
        for (int i = 0; i < count; i++){
            int maLoaiSP = sanPhamList.get(i).getMALOAISP();
            sanPhamList.get(i).setLoaiSanPhams(xuLyJSONMenu.LayDanhSachSP(maLoaiSP));
        }
    }

    @Override
    public int getGroupCount() {
        return sanPhamList.size();
    }

    @Override
    public int getChildrenCount(int viTriCha) {
        return sanPhamList.get(viTriCha).getLoaiSanPhams().size();
    }

    @Override
    public Object getGroup(int viTriCha) {
        return sanPhamList.get(viTriCha);
    }

    @Override
    public Object getChild(int viTriCha, int viTriCon) {
        return sanPhamList.get(viTriCha).getLoaiSanPhams().get(viTriCon);
    }

    @Override
    public long getGroupId(int viTriCha) {
        return sanPhamList.get(viTriCha).getMALOAISP();
    }

    @Override
    public long getChildId(int viTriCha, int viTriCon) {
        return sanPhamList.get(viTriCha).getLoaiSanPhams().get(viTriCon).getMALOAISP();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int viTriCha, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewGroupCha = layoutInflater.inflate(R.layout.custom_groupcha, parent, false);
        TextView txtTenLoaiSP = viewGroupCha.findViewById(R.id.txt_TenLoaiSP);
        txtTenLoaiSP.setText(sanPhamList.get(viTriCha).getTENLOAISP());

        return viewGroupCha;
    }

    @Override
    public View getChildView(int viTriCha, int viTriCon, boolean isExpanded, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText(sanPhamList.get(viTriCha).getLoaiSanPhams().get(viTriCon).getTENLOAISP());
        textView.setPadding(30,12,12,12);
        textView.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return textView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
