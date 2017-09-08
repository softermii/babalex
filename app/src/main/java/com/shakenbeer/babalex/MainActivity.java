package com.shakenbeer.babalex;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shakenbeer.babalex.cart.CartActivity;
import com.shakenbeer.babalex.common.Utils;
import com.shakenbeer.babalex.data.BabalexItem;
import com.shakenbeer.babalex.data.Category;
import com.shakenbeer.babalex.data.Sweets;

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
    private ImageButton cartImageButton;
    private TextView nextCategory;
    private LinearLayout babalexItemDataLayout;
    private CategoriesRecyclerViewManager categoriesManager;
    private RecyclerView categoriesRecyclerView;
    private RecyclerView backgroundRecyclerView;
    private CategoryAdapter categoryAdapter;

    private Sweets sweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();

        superBabalex = (SuperBabalexView) findViewById(R.id.super_babalex);
        superBabalex.setLayoutManager(new LinearLayoutManager(this));
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

        backgroundRecyclerView = (RecyclerView) findViewById(R.id.background_recycler_view);
        babalexItemDataLayout = (LinearLayout) findViewById(R.id.babalex_item_data_layout);
        babalexItemTitle = (TextView) findViewById(R.id.babalex_item_title);
        babalexItemDescription = (TextView) findViewById(R.id.babalex_item_description);
        babalexItemPrice = (TextView) findViewById(R.id.babalex_item_price);
        babalexCurrencySign = (TextView) findViewById(R.id.currency_sign);
        addToCartButton = (Button) findViewById(R.id.add_to_cart_button);
        nextCategory = (TextView) findViewById(R.id.swipe_up_text_view);
        cartImageButton = (ImageButton) findViewById(R.id.cart_icon);

        Typeface titleTypeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "BradHitc.ttf");
        Typeface textRegularLightTypeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "GillSansLight.ttf");
        Typeface textRegularTypeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "GillSans.ttf");
        babalexItemTitle.setTypeface(titleTypeface, Typeface.BOLD);
        babalexItemDescription.setTypeface(textRegularLightTypeface);
        babalexCurrencySign.setTypeface(textRegularLightTypeface);
        babalexItemPrice.setTypeface(textRegularTypeface, Typeface.BOLD);
        nextCategory.setTypeface(textRegularTypeface, Typeface.BOLD);
        addToCartButton.setTypeface(textRegularTypeface, Typeface.BOLD);

        backgroundRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        cartImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });

        updateUI();
    }

    // should be called after initial API response to update UI
    private void updateUI() {
        sweets = App.getSweetsInstance();

        categoryAdapter = new CategoryAdapter(this, sweets.getCategories());
        SmoothScrollLinearLayoutManager scrollLinearLayoutManager =
                new SmoothScrollLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        categoriesRecyclerView = (RecyclerView) findViewById(R.id.categories);
        categoriesManager = new CategoriesRecyclerViewManager(categoriesRecyclerView,
                sweets.getCategoriesCount());
        categoriesRecyclerView.setLayoutManager(scrollLinearLayoutManager);
        categoriesRecyclerView.setAdapter(categoryAdapter);
        showNextCategoryText();

        superBabalexAdapter = new SuperBabalexAdapter(sweets.getCategories(), horizontalScrollListener,
                onItemSelectedCallback);
        superBabalex.setAdapter(superBabalexAdapter);

        backgroundRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Integer> list = new ArrayList<>();
        for (Category c : sweets.getCategories()) {
            list.add(Utils.getDrawableResIdByImageTitle(this, c.getImageName()));
        }
        backgroundRecyclerView.setAdapter(new BackgroundAdapter(list));

    }

    private void showNextCategoryText() {
        int size = categoryAdapter.getItemCount();
        int selected = categoryAdapter.getSelectedPosition();
        if (selected < size - 1) {
            nextCategory.setVisibility(View.VISIBLE);
            nextCategory.setText(sweets.getCategory(selected + 1).getTitle());
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
        public void onScroll(float shiftByX, float alpha, BabalexItem babalex) {
            //Don't change babalex item data while scrolling
            if (superBabalex.getScrollState() == SCROLL_STATE_IDLE) {
                babalexItemTitle.setText(babalex.getTitle());
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
            float alpha = 1f - (shiftByY * 3f / imageHeight);
            babalexItemDataLayout.setAlpha(alpha);
            nextCategory.setAlpha(alpha);
            float scaleFactor = 1 + .25f * ((float) shiftByY / imageHeight);
            categoriesRecyclerView.setScaleX(scaleFactor);
            categoriesRecyclerView.setScaleY(scaleFactor);
        }

        @Override
        public void categoryChanged(int activePosition) {
            categoriesManager.onCategoryChanged(activePosition);
            categoryAdapter.setSelected(activePosition);
            showNextCategoryText();
        }
    };

    private final BabalexAdapter.OnItemSelectedCallback onItemSelectedCallback = new BabalexAdapter.OnItemSelectedCallback() {
        @Override
        public void onItemSelected(int position, BabalexItem item, View imageView) {
            Intent intent = new Intent(MainActivity.this, ExtendedBabalexActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(SELECTED_ITEM_NAME, item.getTitle());
            bundle.putInt(SELECTED_ITEM_IMAGE_RES, Utils.getDrawableResIdByImageTitle(
                    MainActivity.this, item.getImageName()));
            bundle.putInt(SELECTED_ITEM_CATEGORY_BACKGROUND, Utils.getDrawableResIdByImageTitle(MainActivity.this,
                    sweets.getCategory(categoryAdapter.getSelectedPosition()).getImageName()));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Pair<View, String> animatedImage = new Pair<>(imageView, imageView.getTransitionName());
                Pair<View, String> sharedButton = new Pair<>((View) addToCartButton, addToCartButton.getTransitionName());

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(MainActivity.this, animatedImage, sharedButton);
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
