package com.shakenbeer.babalex.data;

public class Candy implements Babalex {

    private String name;

    private String imageUri;

    private int imageRes;

    public Candy(String name, int image) {
        this.name = name;
        this.imageRes = image;
    }

    public Candy(String name, int imageRes, String imageUri) {
        this(name, imageRes);
        this.imageUri = imageUri;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getImageUri() {
        return imageUri;
    }

    @Override
    public String getName() {
        return name;
    }
}
