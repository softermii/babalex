package com.shakenbeer.babalex;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by onos on 29.08.17.
 */

public class ExtendedBabalexActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extended_babalex_view);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();


        String babalexItemName = getIntent().getExtras().getString(MainActivity.SELECTED_ITEM_NAME);
        int babalexItemImageRes = getIntent().getExtras().getInt(MainActivity.SELECTED_ITEM_IMAGE_RES);


        SquareImageView imageView = (SquareImageView) findViewById(R.id.image);
        Button addToCartButton = (Button) findViewById(R.id.add_to_cart_button);
        TextView title = (TextView) findViewById(R.id.babalex_item_title);
        TextView description = (TextView) findViewById(R.id.babalex_item_description);
        TextView titleIngredients = (TextView) findViewById(R.id.field_title_ingredients);
        TextView ingredients = (TextView) findViewById(R.id.ingredients);
        TextView titleServingSize = (TextView) findViewById(R.id.field_serving_size);
        TextView servingSize = (TextView) findViewById(R.id.serving_size);
        TextView titleCalories = (TextView) findViewById(R.id.field_calories);
        TextView calories = (TextView) findViewById(R.id.calories);
        TextView titleTotalFat = (TextView) findViewById(R.id.field_total_fat);
        TextView totalFat = (TextView) findViewById(R.id.total_fat);
        TextView titleWeight = (TextView) findViewById(R.id.field_weight);
        TextView weight = (TextView) findViewById(R.id.weight);

        Typeface titleTypeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "BradHitc.ttf");
        Typeface textRegularLightTypeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "GillSansLight.ttf");
        Typeface textRegularTypeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "GillSans.ttf");

        title.setTypeface(titleTypeface, Typeface.BOLD);
        titleIngredients.setTypeface(textRegularTypeface, Typeface.BOLD);
        titleServingSize.setTypeface(textRegularTypeface, Typeface.BOLD);
        titleCalories.setTypeface(textRegularTypeface, Typeface.BOLD);
        titleTotalFat.setTypeface(textRegularTypeface, Typeface.BOLD);
        titleWeight.setTypeface(textRegularTypeface, Typeface.BOLD);
        addToCartButton.setTypeface(textRegularTypeface, Typeface.BOLD);
        description.setTypeface(textRegularLightTypeface);
        ingredients.setTypeface(textRegularLightTypeface);
        servingSize.setTypeface(textRegularLightTypeface);
        calories.setTypeface(textRegularLightTypeface);
        totalFat.setTypeface(textRegularLightTypeface);
        weight.setTypeface(textRegularLightTypeface);


        imageView.setImageResource(babalexItemImageRes);
        title.setText(babalexItemName);


    }


}
