package com.shakenbeer.babalex.data;

import android.util.SparseIntArray;

/**
 * Created by onos on 08.09.17.
 */

public class Order {

    private SparseIntArray orderArray = new SparseIntArray();

    public SparseIntArray getOrderArray() {
        return orderArray;
    }

    public void addItem(int id) {
        int itemsCount = orderArray.get(id);
        if (itemsCount == 0) {
            orderArray.put(id, 1);
        } else if (itemsCount > 0) {
            orderArray.put(id, ++itemsCount);
        }
    }

    public void removeItemById(int id) {
        orderArray.delete(id);
    }

    public int getItemCountById(int id) {
        return orderArray.get(id);
    }

}
