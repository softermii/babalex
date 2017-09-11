package com.shakenbeer.babalex.cart;

import com.shakenbeer.babalex.App;
import com.shakenbeer.babalex.common.BasePresenter;
import com.shakenbeer.babalex.data.BabalexCartItem;
import com.shakenbeer.babalex.data.BabalexItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by onos on 08.09.17.
 */

public class CartPresenter extends BasePresenter<CartView> {

    private Map<Integer, BabalexItem> sweetsMap;
    private Map<Integer, Integer> order;

    @Override
    public void onResume() {
        super.onResume();
        order = App.getOrderInstance().getOrderMap();
        sweetsMap = App.getBabalexItemsInstance();
        loadAndShowOrder();

    }

    private void loadAndShowOrder() {
        List<BabalexCartItem> itemsToShow = new ArrayList<>();
        for (int itemId : order.keySet()) {
            itemsToShow.add(new BabalexCartItem(itemId, order.get(itemId),
                    sweetsMap.get(itemId)));
        }

        getMvpView().showOrder(itemsToShow);

        calculateTotalPrice(itemsToShow);
    }

    private void calculateTotalPrice(List<BabalexCartItem> itemsToShow) {
        double price = 0;

        for (BabalexCartItem item : itemsToShow) {
            double itemPrice = sweetsMap.get(item.getId()).getPrice();
            price += (itemPrice * item.getCount());
        }

        getMvpView().showTotalPrice(price);
    }

    void onItemAddedToCart(int itemId) {
        int prevAmount = order.get(itemId);
        order.put(itemId, ++prevAmount);

        loadAndShowOrder();
    }

    void onItemRemovedToCart(int itemId) {
        int amount = order.get(itemId);
        if (--amount <= 0) {
            order.remove(itemId);
        } else {
            order.put(itemId, amount);
        }

        loadAndShowOrder();
    }

}
