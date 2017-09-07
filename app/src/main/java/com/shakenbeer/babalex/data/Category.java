package com.shakenbeer.babalex.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by onos on 07.09.17.
 */

public class Category {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image_name")
    @Expose
    private String imageName;
    @SerializedName("items")
    @Expose
    private List<BabalexItem> items = new ArrayList<>();

    /**
     * No args constructor for use in serialization
     */
    public Category() {
    }

    /**
     * @param id
     * @param title
     * @param items
     * @param imageName
     */
    public Category(Integer id, String title, String imageName, List<BabalexItem> items) {
        super();
        this.id = id;
        this.title = title;
        this.imageName = imageName;
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public List<BabalexItem> getItems() {
        return items;
    }

    public void setItems(List<BabalexItem> items) {
        this.items = items;
    }

}
