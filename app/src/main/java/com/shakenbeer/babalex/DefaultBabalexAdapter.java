package com.shakenbeer.babalex;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DefaultBabalexAdapter extends BabalexAdapter<DefaultBabalexAdapter.BabalexHolder> {

    private List<Babalex> items = new ArrayList<>();

    public void setItems(List<Babalex> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(BabalexHolder holder, int position) {
        holder.image.setImageResource(items.get(position).getImage());
    }

    public Babalex getItem(int adapterPos) {
        return items.get(adapterPos);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.square_image;
    }

    @Override
    public BabalexHolder createHolder(View child) {
        return new BabalexHolder(child);
    }


    public static class BabalexHolder extends RecyclerView.ViewHolder {

        SquareImageView image;

        public BabalexHolder(View itemView) {
            super(itemView);
            image = (SquareImageView) itemView.findViewById(R.id.image);
        }
    }
}
