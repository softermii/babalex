package com.shakenbeer.babalex.data;

/**
 * Created by onos on 07.09.17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Sweets {

    @SerializedName("categories")
    @Expose
    private List<Category> categories = new ArrayList<>();

    /**
     * No args constructor for use in serialization
     */
    public Sweets() {
    }

    /**
     * @param categories
     */
    public Sweets(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public int getCategoriesCount() {
        return categories.size();
    }

    public Category getCategory(int position) {
        return categories.get(position);
    }
}
