package com.shakenbeer.babalex.data;


import com.shakenbeer.babalex.R;

public class Storage {

    private static BabalexCollection sweets = new BabalexCollection();

    private static final Category cupcakes;

    private static final Category macaron;

    static {
        cupcakes = new Category("Cupcakes", R.drawable.background_cupcakes);
        cupcakes.add(new Candy("Verginius Pontius Rufinus", R.drawable.cupcake1));
        cupcakes.add(new Candy("Lucretia Claudius Rufinus", R.drawable.cupcake2));
        cupcakes.add(new Candy("Priscilla Marcella Cicero", R.drawable.cupcake3));
        cupcakes.add(new Candy("Quintillus Publius Balbus", R.drawable.cupcake4));
        cupcakes.add(new Candy("Maximinus Aulus Loukios", R.drawable.cupcake5));
        sweets.addCategory(cupcakes);

        macaron = new Category("Macarons", R.drawable.background_macarons);
        macaron.add(new Candy("Wandal Brunhilde Swanahilda", R.drawable.macaron1));
        macaron.add(new Candy("Ricohard Giltbert Otto", R.drawable.macaron2));
        macaron.add(new Candy("Alfher Hrodger Reinald", R.drawable.macaron3));
        macaron.add(new Candy("Ida Albertus Willahelm", R.drawable.macaron4));
        macaron.add(new Candy("Raginmund Hariman Auda", R.drawable.macaron5));
        sweets.addCategory(macaron);
        sweets.addCategory(cupcakes);
        sweets.addCategory(macaron);
        sweets.addCategory(cupcakes);
        sweets.addCategory(macaron);
        sweets.addCategory(cupcakes);
    }


    public static Category cupcakes() {
        return cupcakes;
    }

    public static Category macaron() {
        return macaron;
    }

    public static BabalexCollection animals() {
        return sweets;
    }
}
