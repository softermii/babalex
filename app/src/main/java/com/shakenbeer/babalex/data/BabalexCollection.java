package com.shakenbeer.babalex.data;


import java.util.ArrayList;
import java.util.List;

public class BabalexCollection {

    private final List<Category> data = new ArrayList<>();

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCategory(Category category) {
        data.add(category);
    }

    public int getCategoriesCount() {
        return data.size();
    }

    public int getCategorySize(int category) {
        return data.get(category).size();
    }

    public Babalex get(int category, int index) {
        return data.get(category).get(index);
    }

    public Category get(int index) {
        return data.get(index);
    }

    public int size() {
        return data.size();
    }

}
