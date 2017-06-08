package com.shakenbeer.babalex;


public class Animal implements Babalex {

    private String name;

    private String imageUri;

    private int image;

    public Animal(String name, int image) {
        this.name = name;
        this.image = image;
    }

    @Override
    public int getImage() {
        return image;
    }

    public String getImageUri() {
        return imageUri;
    }

    @Override
    public String getName() {
        return name;
    }
}
