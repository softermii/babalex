package com.shakenbeer.babalex;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.shakenbeer.babalex.data.Babalex;
import com.shakenbeer.babalex.data.Category;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

public class BabalexView extends RecyclerView {

    @Retention(SOURCE)
    @IntDef({LEFT, CENTER, RIGHT, NULL})
    @interface TargetPos {
    }

    private static final String TAG = "BabalexView";
    private static final float DEFAULT_SCALE_FACTOR = 0.55f;
    private static final float MIN_SCALE_FACTOR = 0.2f;
    private static final float MAX_SCALE_FACTOR = 0.99f;

    private static final int LEFT = -1;
    private static final int CENTER = 0;
    private static final int RIGHT = 1;
    private static final int NULL = 2595;
    private String name;

    private int padding;
    private float pivotY;
    private float scaleFactor;
    private int imageMaxHeight;
    private int imageMaxWidth;

    private int threshold = 50;
    private int scrollState;
    @TargetPos
    private int targetPos;

    private final SnapHelper snapperCarr = new PagerSnapHelper();
    private BabalexAdapter babalexAdapter;
    @Nullable
    private ScrollListener scrollListener;
    private ViewGroup targetChild;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void createAndSetAdapter(Category category, BabalexAdapter.OnItemSelectedCallback onItemSelectedCallback) {
        babalexAdapter = new BabalexAdapter(onItemSelectedCallback);
        babalexAdapter.setItems(category.getItems());
        setAdapter(babalexAdapter);
    }

    public void setScrollListener(@Nullable ScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }

    public int getImageMaxHeight() {
        return imageMaxHeight;
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        setClipToPadding(false);
        setLayoutManager(new BabalexLayoutManager(context));
        snapperCarr.attachToRecyclerView(this);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BabalexView, 0, 0);

        scaleFactor = a.getFloat(R.styleable.BabalexView_scaleFactor, DEFAULT_SCALE_FACTOR);
        if (scaleFactor < MIN_SCALE_FACTOR) scaleFactor = MIN_SCALE_FACTOR;
        if (scaleFactor > MAX_SCALE_FACTOR) scaleFactor = MAX_SCALE_FACTOR;

        padding = getPaddingStart();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (getChildCount() > 0) {
            ViewGroup container = (ViewGroup) getChildAt(0);
            View image = container.getChildAt(0);
            imageMaxHeight = image.getHeight();
            imageMaxWidth = image.getWidth();
            pivotY = imageMaxHeight - (scaleFactor * imageMaxHeight / (1 - scaleFactor));
            initRescale(container);
        }
    }

    private void initRescale(ViewGroup firstChild) {
        float width = firstChild.getChildAt(0).getWidth();
        if (getChildCount() >= 3) {
            if (getChildAt(0) != null) {
                ViewGroup container = (ViewGroup) getChildAt(0);
                View child = container.getChildAt(0);
                scale(child, Math.abs(container.getX()), width, scaleFactor);
            }
            if (getChildAt(2) != null) {
                ViewGroup container = (ViewGroup) getChildAt(2);
                View child = container.getChildAt(0);
                scale(child, Math.abs(container.getX() - (container.getWidth() / 2)), 0, scaleFactor);
            }
        } else {
            if (getChildAt(0) != null) {
                ViewGroup container = (ViewGroup) getChildAt(0);
                if (container.getLeft() < 0) {
                    View child = container.getChildAt(0);
                    scale(child, Math.abs(container.getX()), width, scaleFactor);
                }
            }
            if (getChildAt(1) != null) {
                ViewGroup container = (ViewGroup) getChildAt(1);
                if (container.getRight() > getWidth()) {
                    View child = container.getChildAt(0);
                    scale(child, Math.abs(container.getX() - (container.getWidth() / 2)), 0, scaleFactor);
                }
            }
        }
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);

        int childCount = getChildCount();

        processTargetChild();

        for (int layoutIndex = 0; layoutIndex < childCount; layoutIndex++) {
            ViewGroup container = (ViewGroup) getChildAt(layoutIndex);
            if (targetPos != CENTER) {
                checkTargetCandidate(container);
            }
            scaleImage(container);
        }
    }

    private void checkTargetCandidate(ViewGroup container) {
        if (container.getX() >= padding - threshold && container.getX() <= padding + threshold) {
            targetChild = container;
            targetPos = CENTER;
            center();
        }
    }

    public void processTargetChild() {
        targetPos = targetInThreshold();
        if (targetPos == CENTER) {
            center();
        } else if (targetPos == LEFT) {
            left();
        } else if (targetPos == RIGHT) {
            right();
        }
    }

    private void scaleImage(ViewGroup container) {
        View image = container.getChildAt(0);
        float rate = 0;

        if (container.getLeft() <= padding) {
            if (container.getLeft() >= padding - container.getWidth()) {
                rate = (padding - container.getLeft()) * 1f / container.getWidth();
            } else {
                rate = 1;
            }
            scale(image, Math.abs(container.getX()), container.getWidth(), 1 - rate * (1 - scaleFactor));
        } else {
            if (container.getLeft() <= getWidth() - padding) {
                rate = (getWidth() - padding - container.getLeft()) * 1f / container.getWidth();
            }
            scale(image, Math.abs(container.getX() - (container.getWidth() / 2)), 0, scaleFactor + rate * (1 - scaleFactor));
        }
    }

    private
    @TargetPos
    int targetInThreshold() {
        if (targetChild != null) {
            if (targetChild.getX() < padding - threshold) {
                return LEFT;
            }
            if (targetChild.getX() > padding + threshold) {
                return RIGHT;
            }
            return CENTER;
        }
        return NULL;
    }

    private void stopped() {
        if (scrollListener != null) {
            float deltaX = 0;
            float alpha = 1;
            scrollListener.onScroll(deltaX, alpha);
        }
    }

    private void right() {
        if (scrollListener != null) {
            float deltaX = threshold;
            float alpha = 0;
            scrollListener.onScroll(deltaX, alpha);
        }
    }

    private void left() {
        if (scrollListener != null) {
            float deltaX = -threshold;
            float alpha = 0;
            scrollListener.onScroll(deltaX, alpha);
        }
    }

    private void center() {
        if (scrollListener != null) {
            if (targetChild != null) {
                float deltaX = targetChild.getX() - padding;
                float alpha = 1 - Math.abs(deltaX / threshold);

                ViewHolder viewHolder = findContainingViewHolder(targetChild);
                if (viewHolder != null) {
                    Babalex babalex = babalexAdapter.getItem(viewHolder.getAdapterPosition());
                    scrollListener.onScroll(deltaX, alpha, babalex);
                } else {
                    scrollListener.onScroll(deltaX, alpha);
                }
            }
        }
    }

    private void scale(View view, float scrollDistanceX, float pivotX, float scale) {
        if (isLayoutMeasured()) return;
        float pivotAbs = Math.abs(pivotY - (imageMaxHeight / 2));
        float endPivotY = (imageMaxHeight / 2) - (pivotAbs * (scrollDistanceX * 1.25f / imageMaxWidth));
        view.setPivotX(pivotX);
        view.setPivotY(endPivotY);
        view.setScaleX(scale);
        view.setScaleY(scale);
    }

    private boolean isLayoutMeasured() {
        return imageMaxHeight == 0 || imageMaxWidth == 0;
    }

    interface ScrollListener {
        void onScroll(float shiftByX, float alpha);

        void onScroll(float shiftByX, float alpha, Babalex babalex);
    }


    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);

        if (state == SCROLL_STATE_IDLE && scrollState == SCROLL_STATE_SETTLING) {
            stopped();
        }

        scrollState = state;
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
}
