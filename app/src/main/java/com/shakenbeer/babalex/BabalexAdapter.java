package com.shakenbeer.babalex;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.shakenbeer.babalex.data.Babalex;

import java.util.ArrayList;
import java.util.List;


public class BabalexAdapter extends RecyclerView.Adapter<BabalexAdapter.BabalexHolder> {


    @Override
    public BabalexHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View container = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_babalex, parent, false);
        return new BabalexHolder(container);
    }

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


    public static class BabalexHolder extends RecyclerView.ViewHolder {

        SquareImageView image;

        public BabalexHolder(View itemView) {
            super(itemView);
            image = (SquareImageView) itemView.findViewById(R.id.image);
        }
    }
}
