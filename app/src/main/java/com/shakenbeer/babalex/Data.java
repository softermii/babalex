package com.shakenbeer.babalex;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Data {

    private static BabalexCollection animals = new BabalexCollection();

    private static List<Babalex> circles = new ArrayList<>();

    static {
        List<Babalex> cats = new LinkedList<>();
        cats.add(new Animal("Cat 0", R.drawable.cat_0_800px));
        cats.add(new Animal("Cat 1", R.drawable.cat_1_800px));
        cats.add(new Animal("Cat 2", R.drawable.cat_2_800px));
        cats.add(new Animal("Cat 3", R.drawable.cat_3_800px));
        cats.add(new Animal("Cat 4", R.drawable.cat_4_800px));
        cats.add(new Animal("Cat 5", R.drawable.cat_5_800px));
        cats.add(new Animal("Cat 6", R.drawable.cat_6_800px));
        cats.add(new Animal("Cat 7", R.drawable.cat_7_800px));
        animals.addCategory(cats);

        List<Babalex> dogs = new LinkedList<>();
        dogs.add(new Animal("Dog 0", R.drawable.dog_0_800px));
        dogs.add(new Animal("Dog 1", R.drawable.dog_1_800px));
        dogs.add(new Animal("Dog 2", R.drawable.dog_2_800px));
        dogs.add(new Animal("Dog 3", R.drawable.dog_3_800px));
        dogs.add(new Animal("Dog 4", R.drawable.dog_4_800px));
        animals.addCategory(dogs);

        List<Babalex> mouses = new LinkedList<>();
        mouses.add(new Animal("Mouse 0", R.drawable.mouse_0_800px));
        mouses.add(new Animal("Mouse 1", R.drawable.mouse_1_800px));
        mouses.add(new Animal("Mouse 2", R.drawable.mouse_2_800px));
        mouses.add(new Animal("Mouse 3", R.drawable.mouse_3_800px));
        mouses.add(new Animal("Mouse 4", R.drawable.mouse_4_800px));
        mouses.add(new Animal("Mouse 5", R.drawable.mouse_5_800px));
        mouses.add(new Animal("Mouse 6", R.drawable.mouse_6_800px));
        animals.addCategory(mouses);

        circles.add(new Animal("Circle 0", R.drawable.item_background_round));
        circles.add(new Animal("Circle 1", R.drawable.item_background_round));
        circles.add(new Animal("Circle 2", R.drawable.item_background_round));
        circles.add(new Animal("Circle 3", R.drawable.item_background_round));
        circles.add(new Animal("Circle 4", R.drawable.item_background_round));
    }

    public static BabalexCollection animals() {
        return animals;
    }

    public static List<Babalex> circles() {
        return circles;
    }
}
