package com.shakenbeer.babalex;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SuperBabalexAdapter extends RecyclerView.Adapter<SuperBabalexAdapter.SuperBabalexViewHolder> {

    BabalexCollection data;

    public SuperBabalexAdapter(BabalexCollection data) {
        this.data = data;
    }

    @Override
    public SuperBabalexViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_super_babalex, parent, false);
        return new SuperBabalexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SuperBabalexViewHolder holder, int position) {
        holder.babalexView.setItems(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class SuperBabalexViewHolder extends RecyclerView.ViewHolder {

        BabalexView babalexView;

        public SuperBabalexViewHolder(View itemView) {
            super(itemView);
            babalexView = (BabalexView) itemView.findViewById(R.id.babalexView);
        }
    }
}