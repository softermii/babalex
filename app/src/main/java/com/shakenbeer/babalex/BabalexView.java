package com.shakenbeer.babalex;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class BabalexView extends RecyclerView {

    private static final float DEFAULT_SCALE_FACTOR = 0.55f;
    private static final float MIN_SCALE_FACTOR = 0.2f;
    private static final float MAX_SCALE_FACTOR = 1f;

    private float pivotY;
    private float scaleFactor;

    private final SnapHelper snapperCarr = new PagerSnapHelper();

    private final DefaultBabalexAdapter babalexAdapter = new DefaultBabalexAdapter();

    public BabalexView(Context context) {
        super(context);
    }

    public BabalexView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BabalexView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        setClipToPadding(false);
        setLayoutManager(new BabalexLayoutManager(context));
        snapperCarr.attachToRecyclerView(this);
        setAdapter(babalexAdapter);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BabalexView, 0, 0);

        scaleFactor = a.getFloat(R.styleable.BabalexView_scaleFactor, DEFAULT_SCALE_FACTOR);
        if (scaleFactor < MIN_SCALE_FACTOR) scaleFactor = MIN_SCALE_FACTOR;
        if (scaleFactor > MAX_SCALE_FACTOR) scaleFactor = MAX_SCALE_FACTOR;
    }

    public void setItems(List<Babalex> items) {
        babalexAdapter.setItems(items);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (getChildCount() > 0) {
            ViewGroup child = (ViewGroup) getChildAt(0);
            float height = child.getChildAt(0).getHeight();
            pivotY = height + 0.5f * (scaleFactor * height / (1 - scaleFactor));
            initRescale(child);
        }
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);

        int childCount = getChildCount();
        int containerWidth = getChildAt(0).getWidth();
        int padding = (getWidth() - containerWidth) / 2;

        for (int j = 0; j < childCount; j++) {
            ViewGroup container = (ViewGroup) getChildAt(j);
            View child = container.getChildAt(0);
            float rate = 0;

            if (container.getLeft() <= padding) {
                if (container.getLeft() >= padding - container.getWidth()) {
                    rate = (padding - container.getLeft()) * 1f / container.getWidth();
                } else {
                    rate = 1;
                }
                scale(child, containerWidth, 1 - rate * (1 - scaleFactor));
            } else {
                if (container.getLeft() <= getWidth() - padding) {
                    rate = (getWidth() - padding - container.getLeft()) * 1f / container.getWidth();
                }
                scale(child, 0, scaleFactor + rate * (1 - scaleFactor));
            }
        }
    }

    private void initRescale(ViewGroup firstChild) {
        float width = firstChild.getChildAt(0).getWidth();
        if (getChildCount() >= 3) {
            if (getChildAt(0) != null) {
                ViewGroup container = (ViewGroup) getChildAt(0);
                View child = container.getChildAt(0);
                scale(child, width, scaleFactor);
            }
            if (getChildAt(2) != null) {
                ViewGroup container = (ViewGroup) getChildAt(2);
                View child = container.getChildAt(0);
                scale(child, 0, scaleFactor);
            }
        } else {
            if (getChildAt(0) != null) {
                ViewGroup container = (ViewGroup) getChildAt(0);
                if (container.getLeft() < 0) {
                    View child = container.getChildAt(0);
                    scale(child, width, scaleFactor);
                }
            }
            if (getChildAt(1) != null) {
                ViewGroup container = (ViewGroup) getChildAt(1);
                if (container.getRight() > getWidth()) {
                    View child = container.getChildAt(0);
                    scale(child, 0, scaleFactor);
                }
            }
        }
    }

    private void scale(View view, float pivotX, float scale) {
        view.setPivotX(pivotX);
        view.setPivotY(pivotY);
        view.setScaleX(scale);
        view.setScaleY(scale);
    }

}
