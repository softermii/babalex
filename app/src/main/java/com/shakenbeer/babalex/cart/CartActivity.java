package com.shakenbeer.babalex.cart;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shakenbeer.babalex.R;
import com.shakenbeer.babalex.common.BaseMVPActivity;
import com.shakenbeer.babalex.common.BasePresenter;
import com.shakenbeer.babalex.data.BabalexCartItem;

import java.util.List;

/**
 * Created by onos on 06.09.17.
 */

public class CartActivity extends BaseMVPActivity implements CartView {

    private static final String TAG = "CartActivity";

    private ImageButton backImageButton;
    private Button checkoutButton;
    private TextView totalPrice;
    private TextView dollarSignView;
    private TextView totalPriceTitleView;
    private TextView cartTextView;
    private RecyclerView cartRecyclerView;
    private CartOrderAdapter cartOrderAdapter;

    private CartPresenter presenter = new CartPresenter(this);

    @Override
    protected BasePresenter getBasePresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();

        Typeface titleTypeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "BradHitc.ttf");
        Typeface textRegularLightTypeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "GillSansLight.ttf");
        Typeface textRegularTypeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "GillSans.ttf");

        backImageButton = (ImageButton) findViewById(R.id.back_image_button);
        checkoutButton = (Button) findViewById(R.id.checkout_button);
        totalPrice = (TextView) findViewById(R.id.total_price);
        dollarSignView = (TextView) findViewById(R.id.dollar_sign);
        totalPriceTitleView = (TextView) findViewById(R.id.total_price_title);
        cartTextView = (TextView) findViewById(R.id.cart_textView);
        cartRecyclerView = (RecyclerView) findViewById(R.id.cart_recycler_view);

        totalPriceTitleView.setTypeface(titleTypeface, Typeface.BOLD);
        cartTextView.setTypeface(titleTypeface, Typeface.BOLD);
        checkoutButton.setTypeface(textRegularTypeface, Typeface.BOLD);
        totalPrice.setTypeface(textRegularTypeface, Typeface.BOLD);
        dollarSignView.setTypeface(textRegularLightTypeface);

        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cartOrderAdapter = new CartOrderAdapter(CartActivity.this, null);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartOrderAdapter);

    }


    @Override
    public void showOrder(List<BabalexCartItem> orderList) {

        cartOrderAdapter.setOrderList(orderList);

    }
}
