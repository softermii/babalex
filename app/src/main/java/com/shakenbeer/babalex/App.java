package com.shakenbeer.babalex;

import android.app.Application;

import com.shakenbeer.babalex.data.Order;

/**
 * Created by onos on 08.09.17.
 */

public class App extends Application {

    private static Order order;

    public static Order getOrderInstance() {
        return order;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (order == null) {
            order = new Order();
        }

    }
}
