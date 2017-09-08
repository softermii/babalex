package com.shakenbeer.babalex.cart;

import android.content.Context;

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

    private Context context;
    private Map<Integer, BabalexItem> sweetsMap;
    private List<BabalexCartItem> itemsToShow;
    private Map<Integer, Integer> order;

    CartPresenter(Context context) {
        // this context field must be removed with API integration
        this.context = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        itemsToShow = new ArrayList<>();
        order = App.getOrderInstance().getOrderMap();
        sweetsMap = App.getBabalexItemsInstance();
        loadAndShowOrder();

    }

    private void loadAndShowOrder() {
        for (int itemId : order.keySet()) {
            itemsToShow.add(new BabalexCartItem(itemId, order.get(itemId),
                    sweetsMap.get(itemId)));
        }

        getMvpView().showOrder(itemsToShow);
    }

}
