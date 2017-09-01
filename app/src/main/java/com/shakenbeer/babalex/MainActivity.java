package com.shakenbeer.babalex;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shakenbeer.babalex.data.Babalex;
import com.shakenbeer.babalex.data.Storage;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class MainActivity extends AppCompatActivity {

    public static final String SELECTED_ITEM_NAME = "selected_item_name";
    public static final String SELECTED_ITEM_IMAGE_RES = "selected_item_image_res";
    public static final String SELECTED_ITEM_CATEGORY_BACKGROUND = "selected_item_category_background";
    private static final String TAG = "MainActivity";
    private SuperBabalexView superBabalex;
    private SuperBabalexAdapter superBabalexAdapter;
    private TextView babalexItemTitle;
    private TextView babalexItemDescription;
    private TextView babalexItemPrice;
    private TextView babalexCurrencySign;
    private Button addToCartButton;
    private TextView nextCategory;
    private LinearLayout babalexItemDataLayout;
    private CategoriesRecyclerViewManager categoriesManager;
    private RecyclerView categoriesRecyclerView;
    private RecyclerView backgroundRecyclerView;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();

        superBabalex = (SuperBabalexView) findViewById(R.id.super_babalex);

        superBabalexAdapter = new SuperBabalexAdapter(Storage.sweets(), horizontalScrollListener,
                onItemSelectedCallback);
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
            }
        });
        superBabalex.attachParallaxBackgroundScroll(parallaxBackgroundScrollListener);
        superBabalex.setScrollListener(verticalScrollListener);

        babalexItemDataLayout = (LinearLayout) findViewById(R.id.babalex_item_data_layout);
        babalexItemTitle = (TextView) findViewById(R.id.babalex_item_title);
        babalexItemDescription = (TextView) findViewById(R.id.babalex_item_description);
        babalexItemPrice = (TextView) findViewById(R.id.babalex_item_price);
        babalexCurrencySign = (TextView) findViewById(R.id.currency_sign);
        addToCartButton = (Button) findViewById(R.id.add_to_cart_button);
        nextCategory = (TextView) findViewById(R.id.swipe_up_text_view);

        Typeface titleTypeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "BradHitc.ttf");
        Typeface textRegularLightTypeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "GillSansLight.ttf");
        Typeface textRegularTypeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "GillSans.ttf");
        babalexItemTitle.setTypeface(titleTypeface, Typeface.BOLD);
        babalexItemDescription.setTypeface(textRegularLightTypeface);
        babalexCurrencySign.setTypeface(textRegularLightTypeface);
        babalexItemPrice.setTypeface(textRegularTypeface, Typeface.BOLD);
        nextCategory.setTypeface(textRegularTypeface, Typeface.BOLD);
        addToCartButton.setTypeface(textRegularTypeface, Typeface.BOLD);

        categoryAdapter = new CategoryAdapter(this, Storage.sweets().getCategories());
        SmoothScrollLinearLayoutManager scrollLinearLayoutManager =
                new SmoothScrollLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        categoriesRecyclerView = (RecyclerView) findViewById(R.id.categories);
        categoriesManager = new CategoriesRecyclerViewManager(categoriesRecyclerView,
                Storage.sweets().getCategoriesCount());
        categoriesRecyclerView.setLayoutManager(scrollLinearLayoutManager);
        categoriesRecyclerView.setAdapter(categoryAdapter);
        showNextCategoryText();


        backgroundRecyclerView = (RecyclerView) findViewById(R.id.background_recycler_view);
        backgroundRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        backgroundRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Integer> list = new ArrayList<>();
        list.add(Storage.macaron().getBackgroundResId());
        list.add(Storage.cupcakes().getBackgroundResId());
        backgroundRecyclerView.setAdapter(new BackgroundAdapter(list));

    }

    private void showNextCategoryText() {
        int size = categoryAdapter.getItemCount();
        int selected = categoryAdapter.getSelectedPosition();
        if (selected < size - 1) {
            nextCategory.setVisibility(View.VISIBLE);
            nextCategory.setText(Storage.sweets().get(selected + 1).getName());
        } else {
            // hide next category text if the last item is selected
            nextCategory.setVisibility(View.INVISIBLE);
        }
    }

    private BabalexView.ScrollListener horizontalScrollListener = new BabalexView.ScrollListener() {
        @Override
        public void onScroll(float shiftByX, float alpha) {
            babalexItemDataLayout.setTranslationX(shiftByX);
            babalexItemDataLayout.setAlpha(alpha);
        }

        @Override
        public void onScroll(float shiftByX, float alpha, Babalex babalex) {
            //Don't change babalex item data while scrolling
            if (superBabalex.getScrollState() == SCROLL_STATE_IDLE) {
                babalexItemTitle.setText(babalex.getName());
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
            float translationY = shiftByY * .7f + ((shiftByY * 1.2f) / imageHeight);
            categoriesRecyclerView.setTranslationY(translationY);
            categoriesRecyclerView.setAlpha(shiftByY * 1.08f / imageHeight);
            babalexItemDataLayout.setAlpha(1f - (shiftByY * 3f / imageHeight));
            nextCategory.setAlpha(1f - (shiftByY * 3f / imageHeight));
            float scaleFactor = 1 + .25f * ((float) shiftByY / imageHeight);
            categoriesRecyclerView.setScaleX(scaleFactor);
            categoriesRecyclerView.setScaleY(scaleFactor);
        }

        @Override
        public void categoryChanged(int activePosition) {
            Log.d(TAG, "categoryChanged: " + activePosition);
            categoriesManager.onCategoryChanged(activePosition);
            categoryAdapter.setSelected(activePosition);
            showNextCategoryText();
        }
    };

    private final BabalexAdapter.OnItemSelectedCallback onItemSelectedCallback = new BabalexAdapter.OnItemSelectedCallback() {
        @Override
        public void onItemSelected(int position, Babalex item, View imageView) {
            Intent intent = new Intent(MainActivity.this, ExtendedBabalexActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(SELECTED_ITEM_NAME, item.getName());
            bundle.putInt(SELECTED_ITEM_IMAGE_RES, item.getImageRes());
            bundle.putInt(SELECTED_ITEM_CATEGORY_BACKGROUND,
                    Storage.sweets().get(categoryAdapter.getSelectedPosition()).getBackgroundResId()); // temporary solution

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(MainActivity.this, imageView, imageView.getTransitionName());
                intent.putExtras(bundle);
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent, bundle);
            }

        }
    };

    private final SuperBabalexView.ParallaxBackgroundScrollListener parallaxBackgroundScrollListener =
            new SuperBabalexView.ParallaxBackgroundScrollListener() {
                @Override
                public void onScrolled(int dx, int dy) {
                    backgroundRecyclerView.scrollBy(dx, (int) (dy * 0.85f));
                }
            };

}
