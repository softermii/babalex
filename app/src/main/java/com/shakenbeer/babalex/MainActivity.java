package com.shakenbeer.babalex;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class MainActivity extends AppCompatActivity {

    private RecyclerView superBabalex;

    private SuperBabalexAdapter superBabalexAdapter;

    private String state(int state) {
        if (state == SCROLL_STATE_IDLE) {
            return "idle";
        } else if (state == SCROLL_STATE_DRAGGING) {
            return "dragging";
        } else /*if (state == SCROLL_STATE_SETTLING)*/ {
            return "settling";
        }
    }


    private BabalexView.ScrollListener horizontalScrollListener = new BabalexView.ScrollListener() {
        @Override
        public void onAnimate(float deltaX, float alpha) {
            textView.setTranslationX(deltaX);
            textView.setAlpha(alpha);
        }

        @Override
        public void onAnimate(float deltaX, float alpha, Babalex babalex) {
            //Don't change babalex data while scrolling
            if (superBabalex.getScrollState() == SCROLL_STATE_IDLE) {
                textView.setText(babalex.getName());
            }
            onAnimate(deltaX, alpha);
        }
    };

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        superBabalex = (RecyclerView) findViewById(R.id.super_babalex);

        superBabalexAdapter = new SuperBabalexAdapter(Data.animals(), horizontalScrollListener);
        superBabalex.setAdapter(superBabalexAdapter);

        superBabalex.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
//                Log.d("SuperBabalex", "onChildViewAttachedToWindow   " + ((BabalexView)view).getName());
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
//                Log.d("SuperBabalex", "onChildViewDetachedFromWindow " + ((BabalexView)view).getName());
            }
        });

        superBabalex.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Log.d("SuperBabalex", "onScrollStateChanged newState = " + newState + ", " + state(newState));
                if (newState == SCROLL_STATE_IDLE && superBabalex.getChildCount() == 1) {
                    Log.d("SuperBabalex", "IDLE, ask for processTarget");
                    ((BabalexView) superBabalex.getChildAt(0)).processTargetChild();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        textView = (TextView) findViewById(R.id.textView);

    }

}
