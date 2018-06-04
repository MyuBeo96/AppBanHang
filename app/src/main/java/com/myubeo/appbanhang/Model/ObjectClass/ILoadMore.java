package com.myubeo.appbanhang.Model.ObjectClass;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class ILoadMore extends RecyclerView.OnScrollListener {

    int itemAnDauTien = 0;
    int tongItem = 0;
    int itemLoadTruoc = 6;
    RecyclerView.LayoutManager layoutManager;

    public ILoadMore(RecyclerView.LayoutManager layoutManager){
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        tongItem = layoutManager.getItemCount();

        if(layoutManager instanceof LinearLayoutManager){
            itemAnDauTien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }else if(layoutManager instanceof GridLayoutManager){
            itemAnDauTien = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }

        if(tongItem <= (itemAnDauTien + itemLoadTruoc)){
            Log.d("Kiem tra", tongItem + " - " + itemAnDauTien);
        }

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
