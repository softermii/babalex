package com.shakenbeer.babalex.cart;

import com.shakenbeer.babalex.common.BaseView;
import com.shakenbeer.babalex.data.BabalexCartItem;

import java.util.List;

/**
 * Created by onos on 08.09.17.
 */

interface CartView extends BaseView {

    void showOrder(List<BabalexCartItem> orderList);

    void showTotalPrice(double price);
}
