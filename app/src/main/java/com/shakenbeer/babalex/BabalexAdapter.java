package com.shakenbeer.babalex;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;


public abstract class BabalexAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {


    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View container = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_babalex, parent, false);
        ViewStub viewStub = (ViewStub) container.findViewById(R.id.stub);
        viewStub.setLayoutResource(getLayoutResId());
        viewStub.inflate();
        return createHolder(container);
    }

    public abstract int getLayoutResId();

    public abstract VH createHolder(View child);
}
