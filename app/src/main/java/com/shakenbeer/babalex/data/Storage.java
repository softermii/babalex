package com.shakenbeer.babalex.data;


import com.shakenbeer.babalex.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Storage {

    private static BabalexCollection animals = new BabalexCollection();

    private static Category circles;

    private static final Category cats;

    private static final Category dogs;

    private static final Category mouses;

    private static final Category hamsters;

    private static final Category ducks;

    private static final Category pandas;

    static {
        cats = new Category("Cats");
        cats.add(new Animal("Verginius Pontius Rufinus", R.drawable.cat_0_800px));
        cats.add(new Animal("Lucretia Claudius Rufinus", R.drawable.cat_0_800px));
        cats.add(new Animal("Priscilla Marcella Cicero", R.drawable.cat_0_800px));
        cats.add(new Animal("Quintillus Publius Balbus", R.drawable.cat_0_800px));
        cats.add(new Animal("Maximinus Aulus Loukios", R.drawable.cat_0_800px));
        cats.add(new Animal("Duilius Valens Marcius", R.drawable.cat_0_800px));
        cats.add(new Animal("Aelius Severinus Sabinus", R.drawable.cat_0_800px));
        cats.add(new Animal("Publius Crispinus Rufina", R.drawable.cat_0_800px));
        animals.addCategory(cats);

        dogs = new Category("Dogs");
        dogs.add(new Animal("Wandal Brunhilde Swanahilda", R.drawable.dog_1_800px));
        dogs.add(new Animal("Ricohard Giltbert Otto", R.drawable.dog_1_800px));
        dogs.add(new Animal("Alfher Hrodger Reinald", R.drawable.dog_1_800px));
        dogs.add(new Animal("Ida Albertus Willahelm", R.drawable.dog_1_800px));
        dogs.add(new Animal("Raginmund Hariman Auda", R.drawable.dog_1_800px));
        animals.addCategory(dogs);

        mouses = new Category("Mouses");
        mouses.add(new Animal("Leontios Olympias Olympos", R.drawable.mouse_6_800px));
        mouses.add(new Animal("Charmion Kleitos Andreas", R.drawable.mouse_6_800px));
        mouses.add(new Animal("Plato Hyacinthus Cleitus", R.drawable.mouse_6_800px));
        mouses.add(new Animal("Dionysius Euphemios Eugenios", R.drawable.mouse_6_800px));
        mouses.add(new Animal("Aristides Linos Kleopatros", R.drawable.mouse_6_800px));
        mouses.add(new Animal("Basilius Herakleides Eudoxia", R.drawable.mouse_6_800px));
        mouses.add(new Animal("Nikomachos Pericles Antiochus", R.drawable.mouse_6_800px));
        animals.addCategory(mouses);

        circles = new Category("Circles");
        circles.add(new Animal("Nahor", R.drawable.item_background_round));
        circles.add(new Animal("Kenanyahu", R.drawable.item_background_round));
        circles.add(new Animal("Jabin", R.drawable.item_background_round));
        circles.add(new Animal("Golyat", R.drawable.item_background_round));
        circles.add(new Animal("Meshach", R.drawable.item_background_round));
        animals.addCategory(circles);

        hamsters = new Category("Hamsters");
        hamsters.add(new Animal("Dilip Odin", R.drawable.hamster_0_800px));
        hamsters.add(new Animal("Boniface Marko", R.drawable.hamster_0_800px));
        hamsters.add(new Animal("Eseoghene Dwyn", R.drawable.hamster_0_800px));
        hamsters.add(new Animal("Koralo Trey", R.drawable.hamster_0_800px));
        animals.addCategory(hamsters);

        ducks = new Category("ducks");
        ducks.add(new Animal("Guanting Li", R.drawable.duck_0_800px));
        ducks.add(new Animal("Xia Dong", R.drawable.duck_0_800px));
        ducks.add(new Animal("He Shun", R.drawable.duck_0_800px));
        ducks.add(new Animal("Xun Chun", R.drawable.duck_0_800px));
        ducks.add(new Animal("Tai Min", R.drawable.duck_0_800px));
        animals.addCategory(ducks);

        pandas = new Category("Pandas");
        pandas.add(new Animal("Qiang Ru", R.drawable.panda_0_800px));
        pandas.add(new Animal("Yan Yi", R.drawable.panda_0_800px));
        pandas.add(new Animal("Hong Wu", R.drawable.panda_0_800px));
        animals.addCategory(pandas);
    }


    public static Category cts() {
        return cats;
    }

    public static Category dogs() {
        return dogs;
    }

    public static Category mouses() {
        return mouses;
    }

    public static BabalexCollection animals() {
        return animals;
    }

    public static Category circles() {
        return circles;
    }
}
