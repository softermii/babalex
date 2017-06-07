package com.shakenbeer.babalex;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


public class SomeAdapter extends RecyclerView.Adapter<SomeAdapter.MyHolder> {


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public MyHolder(View itemView) {
            super(itemView);
        }
    }
}
