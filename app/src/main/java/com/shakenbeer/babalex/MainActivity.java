package com.shakenbeer.babalex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.shakenbeer.babalex.data.Babalex;
import com.shakenbeer.babalex.data.CategoryItemAnimator;
import com.shakenbeer.babalex.data.Storage;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class MainActivity extends AppCompatActivity {

    private SuperBabalexView superBabalex;
    private SuperBabalexAdapter superBabalexAdapter;
    private TextView textView;
    private CategoriesRecyclerViewManager categoriesManager;
    private RecyclerView categoriesRecyclerView;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        superBabalex = (SuperBabalexView) findViewById(R.id.super_babalex);

        superBabalexAdapter = new SuperBabalexAdapter(Storage.animals(), horizontalScrollListener);
        superBabalex.setAdapter(superBabalexAdapter);

        superBabalex.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == SCROLL_STATE_IDLE && superBabalex.getChildCount() == 1) {
                    ((BabalexView) superBabalex.getChildAt(0)).processTargetChild();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                int childCount = superBabalex.getChildCount();
//                BabalexView babalexView = (BabalexView) superBabalex.getChildAt(0);
//                Log.d("SuperBabalex", "dy = " + dy + ", childCount = " + childCount + ", first child y = " + babalexView.getY());
            }
        });

        superBabalex.setScrollListener(verticalScrollListener);

        textView = (TextView) findViewById(R.id.textView);

        categoryAdapter = new CategoryAdapter(Storage.animals().getCategories());
        categoriesRecyclerView = (RecyclerView) findViewById(R.id.categories);
        categoriesManager = new CategoriesRecyclerViewManager(categoriesRecyclerView,
                Storage.animals().getCategoriesCount());
        categoriesRecyclerView.setLayoutManager(
                new SmoothScrollLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        categoriesRecyclerView.setItemAnimator(new CategoryItemAnimator());
        categoriesRecyclerView.setAdapter(categoryAdapter);

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

    private BabalexView.ScrollListener horizontalScrollListener = new BabalexView.ScrollListener() {
        @Override
        public void onScroll(float shiftByX, float alpha) {
            textView.setTranslationX(shiftByX);
            textView.setAlpha(alpha);
        }

        @Override
        public void onScroll(float shiftByX, float alpha, Babalex babalex) {
            //Don't change babalex item data while scrolling
            if (superBabalex.getScrollState() == SCROLL_STATE_IDLE) {
                textView.setText(babalex.getName());
            }
            onScroll(shiftByX, alpha);
        }
    };

    private SuperBabalexView.ScrollListener verticalScrollListener = new SuperBabalexView.ScrollListener() {

        @Override
        public void onScroll(int shiftByY, int imageHeight) {
            /*
                0.4f stands for the distance from the edge of the screen we want the categories to be shifted
                In the end of swipe shiftByY will be equal to imageHeight, therefore for acceleration of the animation
                I used this dependency:  ((shiftByY * 1.2f) / imageHeight).
                1.2f is a little adjustment for the animation acceleration.
             */
            float translationY = shiftByY * 0.4f + ((shiftByY * 1.2f) / imageHeight);
            categoriesRecyclerView.setTranslationY(translationY);
            float scaleFactor = 1 + .25f * ((float) shiftByY / imageHeight);
            categoriesRecyclerView.setScaleX(scaleFactor);
            categoriesRecyclerView.setScaleY(scaleFactor);
        }

        @Override
        public void categoryChanged(int activePosition) {
            categoryAdapter.setSelected(activePosition);
            categoriesManager.onCategoryChanged(activePosition);
        }
    };

}
