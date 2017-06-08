package com.shakenbeer.babalex;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.AttributeSet;


public class SuperBabalexView extends RecyclerView {

    private final SnapHelper snapperCarr = new PagerSnapHelper();

    public SuperBabalexView(Context context) {
        super(context);
        init(context);
    }

    public SuperBabalexView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SuperBabalexView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        setLayoutManager(new LinearLayoutManager(context));
        snapperCarr.attachToRecyclerView(this);
    }


}
