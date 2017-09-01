package com.shakenbeer.babalex;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.AttributeSet;
import android.util.Log;


public class SuperBabalexView extends RecyclerView {

    private final SnapHelper snapperCarr = new PagerSnapHelper();
    private static final String TAG = "SuperBabalexView";

    private BabalexView target;
    private int changeCategoryEdge = 0;
    private int prevFirstChildY = 0;

    @Nullable
    private ScrollListener scrollListener;
    private ParallaxBackgroundScrollListener backgroundScrollListener;
    private int scrollState = SCROLL_STATE_SETTLING;

    public SuperBabalexView(Context context) {
        super(context);
        init(context);
    }

//    @Override
//    public boolean fling(int velocityX, int velocityY) {
//        velocityY *= 0.05f; // (between 0 for no fling, and 1 for normal fling, or more for faster fling).
//        Log.d(TAG, "fling: " + velocityY);
//        return super.fling(velocityX, velocityY);
//    }

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

    public void attachParallaxBackgroundScroll(ParallaxBackgroundScrollListener listener) {
        this.backgroundScrollListener = listener;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        BabalexView babalexView = (BabalexView) getChildAt(0);
        changeCategoryEdge = (int) (-0.4f * babalexView.getHeight());
        Log.d(TAG, "onLayout image = " + babalexView.getImageMaxHeight() + ", changeCategoryEdge = " + changeCategoryEdge);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        if (target == null) target = (BabalexView) getChildAt(0);

        if (scrollListener != null) {
            if (Math.abs(target.getY()) <= target.getImageMaxHeight()) {
                scrollListener.onScroll((int) Math.abs(target.getY()), target.getImageMaxHeight());
            } else if (scrollState == SCROLL_STATE_SETTLING) {
                scrollListener.onScroll((int) Math.abs(getHeight() + target.getY()),
                        // getY() returns a negative number here, since target is above the screen
                        target.getImageMaxHeight());
            }

            BabalexView firstChild = (BabalexView) getChildAt(0);

            int firstChildCategory = findContainingViewHolder(firstChild).getAdapterPosition();
            //moving down
            if (dy > 0 && prevFirstChildY > changeCategoryEdge && firstChild.getY() * 1.1f < changeCategoryEdge) {
                scrollListener.categoryChanged(firstChildCategory + 1);
            }
            //moving up
            else if (dy < 0 && prevFirstChildY < changeCategoryEdge && firstChild.getY() * 0.9f > changeCategoryEdge) {
                scrollListener.categoryChanged(firstChildCategory);
            }

            prevFirstChildY = (int) firstChild.getY();
        }

        scrollBy(dx, (int) (dy * 0.4f));
        if (backgroundScrollListener != null) {
            backgroundScrollListener.onScrolled(dx, dy);
        }
    }

    private String state(int state) {
        if (state == SCROLL_STATE_IDLE) {
            return "idle";
        } else if (state == SCROLL_STATE_DRAGGING) {
            return "dragging";
        } else /*if (state == SCROLL_STATE_SETTLING)*/ {
            return "settling";
        }
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        Log.d("SuperBabalexView", state(state));
        target = (BabalexView) getChildAt(0);
        if (scrollListener != null) {
            if (Math.abs(target.getY()) <= target.getImageMaxHeight()) {
                scrollListener.onScroll((int) Math.abs(target.getY()), target.getImageMaxHeight());
            }
        }

        scrollState = state;
    }

    public void setScrollListener(@Nullable ScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }

    interface ScrollListener {
        void onScroll(int shiftByY, int imageHeight);

        void categoryChanged(int activePos);
    }

    interface ParallaxBackgroundScrollListener {
        void onScrolled(int dx, int dy);
    }
}
