package com.shakenbeer.babalex;

import android.support.v7.widget.RecyclerView;

/**
 * Created by onos on 18.08.17.
 */

class CategoriesRecyclerViewManager {

    private RecyclerView recyclerView;
    private int itemsSize;
    private int selectedPosition;

    CategoriesRecyclerViewManager(RecyclerView recyclerView, int itemsSize) {
        this.recyclerView = recyclerView;
        this.itemsSize = itemsSize;
    }

    void onCategoryChanged(int activePos) {
        int prevPosition = selectedPosition;
        selectedPosition = activePos;

        if (itemsSize > 5) {
            if (selectedPosition > prevPosition && selectedPosition > 2) {
                recyclerView.smoothScrollToPosition(selectedPosition + 2);
            } else if (selectedPosition < prevPosition && selectedPosition > 1 && selectedPosition < itemsSize - 3) {
                recyclerView.smoothScrollToPosition(selectedPosition - 2);
            }
        }
    }

}
