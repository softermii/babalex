package com.shakenbeer.babalex.data;

/**
 * Created by onos on 08.09.17.
 */

public class BabalexCartItem {

    private int id;
    private int count;
    private BabalexItem babalexItem;

    public BabalexCartItem() {

    }

    public BabalexCartItem(int id, int count, BabalexItem babalexItem) {

        this.id = id;
        this.count = count;
        this.babalexItem = babalexItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BabalexItem getBabalexItem() {
        return babalexItem;
    }

    public void setBabalexItem(BabalexItem babalexItem) {
        this.babalexItem = babalexItem;
    }


}
