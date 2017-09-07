package com.shakenbeer.babalex;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shakenbeer.babalex.data.Category;

import java.util.List;

class SuperBabalexAdapter extends RecyclerView.Adapter<SuperBabalexAdapter.SuperBabalexViewHolder> {

    private static final String TAG = "SuperBabalexAdapter";
    private List<Category> data;
    private BabalexView.ScrollListener scrollListener;
    private BabalexAdapter.OnItemSelectedCallback onItemSelectedCallback;

    SuperBabalexAdapter(List<Category> data, BabalexView.ScrollListener scrollListener, BabalexAdapter.OnItemSelectedCallback onItemSelectedCallback) {
        this.data = data;
        this.scrollListener = scrollListener;
        this.onItemSelectedCallback = onItemSelectedCallback;
    }

    @Override
    public SuperBabalexViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_super_babalex, parent, false);
        return new SuperBabalexViewHolder(view, scrollListener);
    }

    @Override
    public void onBindViewHolder(SuperBabalexViewHolder holder, int position) {
        holder.babalexView.createAndSetAdapter(data.get(position), onItemSelectedCallback);
        holder.babalexView.setName("" + position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class SuperBabalexViewHolder extends RecyclerView.ViewHolder {

        BabalexView babalexView;

        SuperBabalexViewHolder(View itemView, BabalexView.ScrollListener scrollListener) {
            super(itemView);
            babalexView = (BabalexView) itemView.findViewById(R.id.babalexView);
            babalexView.setScrollListener(scrollListener);
        }
    }
}
