package com.shakenbeer.babalex.data;


import java.util.ArrayList;
import java.util.List;

public class Category {

    private String name;
    private int backgroundResId;
    private List<Babalex> items = new ArrayList<>();

    public Category(String name, int backgroundResId) {
        this.name = name;
        this.backgroundResId = backgroundResId;
    }

    public Category(String name) {
        this.name = name;
    }

    public int getBackgroundResId() {
        return backgroundResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Babalex> getItems() {
        return items;
    }

    public void setItems(List<Babalex> items) {
        this.items = items;
    }

    public int size() {
        return items.size();
    }

    public Babalex get(int index) {
        return items.get(index);
    }

    public void add(Babalex babalex) {
        items.add(babalex);
    }
}
