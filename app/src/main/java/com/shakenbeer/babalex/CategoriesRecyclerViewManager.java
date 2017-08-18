package com.shakenbeer.babalex;

import android.support.v7.widget.RecyclerView;

/**
 * Created by onos on 18.08.17.
 */

class CategoriesRecyclerViewManager {

    private RecyclerView recyclerView;
    private int itemsSize;
    private int selectedPosition;
    private int firstVisiblePosition;
    private int lastVisiblePosition = 4; // by default only 5 items should be visible (see view's height in res)

    CategoriesRecyclerViewManager(RecyclerView recyclerView, int itemsSize) {
        this.recyclerView = recyclerView;
        this.itemsSize = itemsSize;
    }

    void onCategoryChanged(int activePos) {
        int prevPosition = selectedPosition;
        selectedPosition = activePos;
        if (itemsSize > 5) {
            if (selectedPosition > prevPosition && selectedPosition > 2 && selectedPosition < itemsSize - 2) {
                scrollDown();
            } else if (selectedPosition < prevPosition && selectedPosition > 1 && selectedPosition < itemsSize - 3) {
                scrollUp();
            }
        }
    }

    private void scrollDown() {
        firstVisiblePosition++;
        lastVisiblePosition++;
        recyclerView.smoothScrollToPosition(lastVisiblePosition);
    }

    private void scrollUp() {
        firstVisiblePosition--;
        lastVisiblePosition--;
        recyclerView.smoothScrollToPosition(firstVisiblePosition);
    }
}
