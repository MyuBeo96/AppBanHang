package com.myubeo.appbanhang.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myubeo.appbanhang.Model.ObjectClass.KhuyenMai;
import com.myubeo.appbanhang.R;

import java.util.List;

public class AdapterKhuyenMai extends RecyclerView.Adapter<AdapterKhuyenMai.ViewHolderKhuyenMai> {

    Context context;
    List<KhuyenMai> khuyenMaiList;

    public AdapterKhuyenMai(Context context, List<KhuyenMai> khuyenMaiList){
        this.context = context;
        this.khuyenMaiList = khuyenMaiList;
    }

    public class ViewHolderKhuyenMai extends RecyclerView.ViewHolder {

        RecyclerView rcv_ItemKhuyenMai;
        TextView txt_TieuDeKM;

        public ViewHolderKhuyenMai(View itemView) {
            super(itemView);

            txt_TieuDeKM = itemView.findViewById(R.id.txt_TieuDeKM);
            rcv_ItemKhuyenMai = itemView.findViewById(R.id.rcv_ItemKhuyenMai);
        }
    }

    @Override
    public ViewHolderKhuyenMai onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_khuyenmai,parent,false);

        ViewHolderKhuyenMai viewHolderKhuyenMai = new ViewHolderKhuyenMai(view);

        return viewHolderKhuyenMai;
    }

    @Override
    public void onBindViewHolder(ViewHolderKhuyenMai holder, int position) {
        KhuyenMai khuyenMai = khuyenMaiList.get(position);

        holder.txt_TieuDeKM.setText(khuyenMai.getTENLOAISP());
        AdapterTopMayTinh adapterTopMayTinh = new AdapterTopMayTinh(context, R.layout.custom_recyclerview_laptop, khuyenMai.getDanhSachSanPhamKhuyenMai());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        holder.rcv_ItemKhuyenMai.setLayoutManager(layoutManager);
        holder.rcv_ItemKhuyenMai.setAdapter(adapterTopMayTinh);
        adapterTopMayTinh.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return khuyenMaiList.size();
    }
}
