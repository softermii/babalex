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

    private BabalexView target;
    private int changeCategoryEdge = 0;
    private int prevFirstChildY = 0;

    @Nullable
    private ScrollListener scrollListener;
    private int scrollState = SCROLL_STATE_SETTLING;

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

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        BabalexView babalexView = (BabalexView) getChildAt(0);
        Log.d("SuperBabalexView", "onLayout image = " + babalexView.getImageHeigh() + "");
        changeCategoryEdge = (int) (-0.8f * babalexView.getImageHeigh());
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        if (target == null) target = (BabalexView) getChildAt(0);

        if (scrollListener != null) {
            if (Math.abs(target.getY()) <= target.getImageHeigh()) {
                scrollListener.onScroll((int) Math.abs(target.getY()), target.getImageHeigh());
            }

            BabalexView firstChild = (BabalexView) getChildAt(0);

            int firstChildCategory = findContainingViewHolder(firstChild).getAdapterPosition();
            //moving up
            if (prevFirstChildY > changeCategoryEdge && firstChild.getY() < changeCategoryEdge) {
                scrollListener.categoryChanged(firstChildCategory + 1);

            }
            //moving down
            else if (prevFirstChildY < changeCategoryEdge && firstChild.getY() > changeCategoryEdge) {
                scrollListener.categoryChanged(firstChildCategory);
            }

            prevFirstChildY = (int) firstChild.getY();
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
        Log.d("SuperBabalexView" , state(state));
        if (state == SCROLL_STATE_IDLE && scrollState != SCROLL_STATE_DRAGGING) {
            target = (BabalexView) getChildAt(0);
            if (scrollListener != null) {
                if (Math.abs(target.getY()) <= target.getImageHeigh()) {
                    scrollListener.onScroll((int) Math.abs(target.getY()), target.getImageHeigh());
                }
            }
        }
        scrollState = state;
    }

    public void setScrollListener(@Nullable ScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }

    public interface ScrollListener {
        void onScroll(int shiftByY, int imageHeight);

        void categoryChanged(int activePos);
    }
}
