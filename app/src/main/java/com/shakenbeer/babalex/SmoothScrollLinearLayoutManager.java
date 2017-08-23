package com.shakenbeer.babalex;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

/**
 * Created by onos on 18.08.17.
 */

class SmoothScrollLinearLayoutManager extends LinearLayoutManager {

    private static final float MILLISECONDS_PER_INCH = 650f;

    SmoothScrollLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state,
                                       int position) {
        RecyclerView.SmoothScroller smoothScroller = new TopSnappedSmoothScroller(recyclerView.getContext()) {
            //This controls the direction in which smoothScroll looks for your view
            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return SmoothScrollLinearLayoutManager.this
                        .computeScrollVectorForPosition(targetPosition);
            }

            //This returns the milliseconds it takes to scroll one pixel.
            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
            }
        };
        smoothScroller.setTargetPosition(position);
        startSmoothScroll(smoothScroller);
    }

    private class TopSnappedSmoothScroller extends LinearSmoothScroller {
        TopSnappedSmoothScroller(Context context) {
            super(context);

        }

        @Override
        public PointF computeScrollVectorForPosition(int targetPosition) {
            return SmoothScrollLinearLayoutManager.this
                    .computeScrollVectorForPosition(targetPosition);
        }

        @Override
        protected int getVerticalSnapPreference() {
            return SNAP_TO_ANY;
        }
    }


}
