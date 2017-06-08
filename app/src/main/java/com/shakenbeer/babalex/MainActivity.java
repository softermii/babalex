package com.shakenbeer.babalex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class MainActivity extends AppCompatActivity {

    RecyclerView superBabalex;

    private SuperBabalexAdapter superBabalexAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        babalexView = (BabalexView) findViewById(R.id.babalexView);
//        babalexView.setItems(Data.dogs());
        superBabalex = (RecyclerView) findViewById(R.id.super_babalex);
        superBabalexAdapter = new SuperBabalexAdapter(Data.animals());
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
                } else /*if (state == SCROLL_STATE_SETTLING)*/{
                    return "settling";
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

}
