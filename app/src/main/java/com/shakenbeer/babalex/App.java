package com.shakenbeer.babalex;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shakenbeer.babalex.common.Utils;
import com.shakenbeer.babalex.data.BabalexItem;
import com.shakenbeer.babalex.data.Category;
import com.shakenbeer.babalex.data.Order;
import com.shakenbeer.babalex.data.Sweets;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by onos on 08.09.17.
 */

public class App extends Application {

    private static Order order;
    private static Sweets sweets;
    private static Map<Integer, BabalexItem> babalexItemMap;

    public static Order getOrderInstance() {
        return order;
    }

    public static Sweets getSweetsInstance() {
        return sweets;
    }

    public static Map<Integer, BabalexItem> getBabalexItemsInstance() {
        return babalexItemMap;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (order == null) {
            order = new Order();
            order.addItem(2);
            order.addItem(2);
            order.addItem(1);
            order.addItem(6);
        }

        String json = Utils.loadSweetsFromAssets(getAssets());
        Gson gson = new GsonBuilder().create();
        sweets = gson.fromJson(json, Sweets.class);

        babalexItemMap = new HashMap<>();
        for (Category category : sweets.getCategories()) {
            for (BabalexItem item : category.getItems()) {
                babalexItemMap.put(item.getId(), item);
            }
        }

    }
}
