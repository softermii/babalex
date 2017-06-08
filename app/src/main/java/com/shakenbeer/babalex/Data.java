package com.shakenbeer.babalex;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Data {

    private static BabalexCollection animals = new BabalexCollection();

    private static List<Babalex> circles = new ArrayList<>();

    private static final List<Babalex> cats;

    private static final List<Babalex> dogs;

    private static final List<Babalex> mouses;

    static {
        cats = new LinkedList<>();
        cats.add(new Animal("Verginius Pontius Rufinus", R.drawable.cat_0_800px));
        cats.add(new Animal("Lucretia Claudius Rufinus", R.drawable.cat_0_800px));
        cats.add(new Animal("Priscilla Marcella Cicero", R.drawable.cat_0_800px));
        cats.add(new Animal("Quintillus Publius Balbus", R.drawable.cat_0_800px));
        cats.add(new Animal("Maximinus Aulus Loukios", R.drawable.cat_0_800px));
        cats.add(new Animal("Duilius Valens Marcius", R.drawable.cat_0_800px));
        cats.add(new Animal("Aelius Severinus Sabinus", R.drawable.cat_0_800px));
        cats.add(new Animal("Publius Crispinus Rufina", R.drawable.cat_0_800px));
        animals.addCategory(cats);

        dogs = new LinkedList<>();
        dogs.add(new Animal("Wandal Brunhilde Swanahilda", R.drawable.dog_1_800px));
        dogs.add(new Animal("Ricohard Giltbert Otto", R.drawable.dog_1_800px));
        dogs.add(new Animal("Alfher Hrodger Reinald", R.drawable.dog_1_800px));
        dogs.add(new Animal("Ida Albertus Willahelm", R.drawable.dog_1_800px));
        dogs.add(new Animal("Raginmund Hariman Auda", R.drawable.dog_1_800px));
        animals.addCategory(dogs);

        mouses = new LinkedList<>();
        mouses.add(new Animal("Leontios Olympias Olympos", R.drawable.mouse_6_800px));
        mouses.add(new Animal("Charmion Kleitos Andreas", R.drawable.mouse_6_800px));
        mouses.add(new Animal("Plato Hyacinthus Cleitus", R.drawable.mouse_6_800px));
        mouses.add(new Animal("Dionysius Euphemios Eugenios", R.drawable.mouse_6_800px));
        mouses.add(new Animal("Aristides Linos Kleopatros", R.drawable.mouse_6_800px));
        mouses.add(new Animal("Basilius Herakleides Eudoxia", R.drawable.mouse_6_800px));
        mouses.add(new Animal("Nikomachos Pericles Antiochus", R.drawable.mouse_6_800px));
        animals.addCategory(mouses);

        circles.add(new Animal("Nahor", R.drawable.item_background_round));
        circles.add(new Animal("Kenanyahu", R.drawable.item_background_round));
        circles.add(new Animal("Jabin", R.drawable.item_background_round));
        circles.add(new Animal("Golyat", R.drawable.item_background_round));
        circles.add(new Animal("Meshach", R.drawable.item_background_round));

        animals.addCategory(circles);
    }


    public static List<Babalex> cts() {
        return cats;
    }

    public static List<Babalex> dogs() {
        return dogs;
    }

    public static List<Babalex> mouses() {
        return mouses;
    }

    public static BabalexCollection animals() {
        return animals;
    }

    public static List<Babalex> circles() {
        return circles;
    }
}
