package com.shakenbeer.babalex;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shakenbeer.babalex.data.BabalexCollection;

public class SuperBabalexAdapter extends RecyclerView.Adapter<SuperBabalexAdapter.SuperBabalexViewHolder> {

    private BabalexCollection data;
    private BabalexView.ScrollListener scrollListener;
    private BabalexAdapter.OnItemSelectedCallback onItemSelectedCallback;

    public SuperBabalexAdapter(BabalexCollection data, BabalexView.ScrollListener scrollListener, BabalexAdapter.OnItemSelectedCallback onItemSelectedCallback) {
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
        holder.babalexView.setBackgroundResource(data.get(position).getBackgroundResId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class SuperBabalexViewHolder extends RecyclerView.ViewHolder {

        BabalexView babalexView;

        public SuperBabalexViewHolder(View itemView, BabalexView.ScrollListener scrollListener) {
            super(itemView);
            babalexView = (BabalexView) itemView.findViewById(R.id.babalexView);
            babalexView.setScrollListener(scrollListener);
        }
    }
}
