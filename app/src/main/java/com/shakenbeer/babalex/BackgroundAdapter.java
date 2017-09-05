package com.shakenbeer.babalex;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by onos on 01.09.17.
 */

class BackgroundAdapter extends RecyclerView.Adapter<BackgroundAdapter.BackgroundViewHolder> {

    private ArrayList<Integer> backgroundResources;

    BackgroundAdapter(ArrayList<Integer> backgroundResources) {
        this.backgroundResources = backgroundResources;
    }

    @Override
    public BackgroundAdapter.BackgroundViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.background_item, parent, false);
        return new BackgroundAdapter.BackgroundViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BackgroundViewHolder holder, int position) {
        holder.imageView.setImageResource(backgroundResources.get(position));
    }

    @Override
    public int getItemCount() {
        return backgroundResources.size();
    }

    static class BackgroundViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public BackgroundViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.background_image_view);
        }
    }

}
