package com.shakenbeer.babalex;


import java.util.ArrayList;
import java.util.List;

public class BabalexCollection {

    private final List<List<Babalex>> data = new ArrayList<>();

    private final List<Babalex> linearData = new ArrayList<>();

    public void addCategory(List<Babalex> category) {
        data.add(category);
        linearData.addAll(category);
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

    public List<Babalex> get(int category) {
        return data.get(category);
    }


    public int size() {
        return data.size();
    }

}
