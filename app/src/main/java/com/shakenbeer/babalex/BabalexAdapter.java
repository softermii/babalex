package com.shakenbeer.babalex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shakenbeer.babalex.data.BabalexItem;

import java.util.ArrayList;
import java.util.List;


class BabalexAdapter extends RecyclerView.Adapter<BabalexAdapter.BabalexHolder> {

    private static final String TAG = "BabalexAdapter";
    private Context context;
    private final OnItemSelectedCallback onItemSelectedCallback;

    interface OnItemSelectedCallback {
        void onItemSelected(int position, BabalexItem item, View imageView);
    }

    BabalexAdapter(Context context, OnItemSelectedCallback onItemSelectedCallback) {
        this.context = context;
        this.onItemSelectedCallback = onItemSelectedCallback;
    }

    @Override
    public BabalexHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View container = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_babalex, parent, false);
        return new BabalexHolder(container);
    }

    private List<BabalexItem> items = new ArrayList<>();

    void setItems(List<BabalexItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final BabalexHolder holder, int position) {
        holder.image.setImageResource(
                Utils.getDrawableResIdByImageTitle(context, items.get(position).getImageName()));
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemSelectedCallback != null) {
                    int pos = holder.getAdapterPosition();
                    onItemSelectedCallback.onItemSelected(pos, items.get(pos), holder.image);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BABALEX_DEBUG",  "holder.image.isClickable() = " + holder.image.isClickable() );
            }
        });
    }

    public BabalexItem getItem(int adapterPos) {
        return items.get(adapterPos);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    class BabalexHolder extends RecyclerView.ViewHolder {

        SquareImageView image;

        BabalexHolder(View itemView) {
            super(itemView);
            image = (SquareImageView) itemView.findViewById(R.id.image);
        }
    }
}
