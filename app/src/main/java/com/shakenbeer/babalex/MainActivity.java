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

    RecyclerView superBabalex;

    private SuperBabalexAdapter superBabalexAdapter;

    private DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();

    private BabalexView.ScrollListener horizontalScrollListener = new BabalexView.ScrollListener() {
        @Override
        public void hideToLeft() {
            Log.d("MainActivity", "start x = " + textView.getX() + ", translationX = " + textView.getTranslationX());
            textView.animate()
                    .translationX(-50)
                    .alpha(0)
                    .setDuration(150)
                    .setInterpolator(decelerateInterpolator)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            Log.d("MainActivity", "end   x = " + textView.getX() + ", translationX = " + textView.getTranslationX());
                        }
                    });
        }

        @Override
        public void hideToRight() {
            textView.animate().translationX(50).alpha(0).setDuration(150).setInterpolator(decelerateInterpolator);
        }

        @Override
        public void showFromRight(Babalex babalex) {

        }

        @Override
        public void showFromLeft(Babalex babalex) {
            textView.animate().translationX(0).alpha(1).setDuration(200).setInterpolator(decelerateInterpolator);
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
                Log.d("SuperBabalex", "onChildViewAttachedToWindow");
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                Log.d("SuperBabalex", "onChildViewDetachedFromWindow");
            }
        });

        superBabalex.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Log.d("SuperBabalex", "onScrollStateChanged newState = " + newState + ", " + state(newState));
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
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        textView = (TextView) findViewById(R.id.textView);

    }

}
