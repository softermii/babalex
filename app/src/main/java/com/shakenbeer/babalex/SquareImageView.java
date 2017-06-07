package com.shakenbeer.babalex;

import android.content.Context;
import android.util.AttributeSet;

public class SquareImageView extends android.support.v7.widget.AppCompatImageView {

    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int h = MeasureSpec.getSize(widthMeasureSpec);
        int hSpec = MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, hSpec);
    }
}
