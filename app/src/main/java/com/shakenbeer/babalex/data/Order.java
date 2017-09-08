package com.shakenbeer.babalex.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by onos on 08.09.17.
 */

public class Order {

    // stands for itemId and itemCounter
    private Map<Integer, Integer> orderMap = new HashMap<>();

    public Map<Integer, Integer> getOrderMap() {
        return orderMap;
    }

    public void addItem(Integer id) {
        Integer itemCounter = orderMap.get(id);
        if (itemCounter == null) {
            orderMap.put(id, 1);
        } else if (itemCounter > 0) {
            orderMap.put(id, ++itemCounter);
        }
    }

    public void removeItemById(Integer id) {
        orderMap.remove(id);
    }

    public int getItemCountById(Integer id) {
        return orderMap.get(id);
    }

}
