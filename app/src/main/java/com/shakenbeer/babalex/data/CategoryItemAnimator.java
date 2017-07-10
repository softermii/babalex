package com.shakenbeer.babalex.data;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;


public class CategoryItemAnimator extends DefaultItemAnimator {

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        Log.d("CategoryItemAnimator", "animateRemove");
        return super.animateRemove(holder);
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        Log.d("CategoryItemAnimator", "animateAdd");
        return super.animateAdd(holder);
    }

    @Override
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        Log.d("CategoryItemAnimator", "animateMove");
        return super.animateMove(holder, fromX, fromY, toX, toY);
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromX, int fromY, int toX, int toY) {
        Log.d("CategoryItemAnimator", "animateChange");
        return super.animateChange(oldHolder, newHolder, fromX, fromY, toX, toY);
    }
}
