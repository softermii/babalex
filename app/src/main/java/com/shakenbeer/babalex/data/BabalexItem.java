package com.shakenbeer.babalex.data;

/**
 * Created by onos on 07.09.17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BabalexItem {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image_name")
    @Expose
    private String imageName;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("detail_info")
    @Expose
    private DetailInfo detailInfo;

    /**
     * No args constructor for use in serialization
     */
    public BabalexItem() {
    }

    /**
     * @param id
     * @param title
     * @param price
     * @param imageName
     * @param description
     * @param detailInfo
     */
    public BabalexItem(Integer id, String title, String description, String imageName, Double price, DetailInfo detailInfo) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageName = imageName;
        this.price = price;
        this.detailInfo = detailInfo;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public DetailInfo getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(DetailInfo detailInfo) {
        this.detailInfo = detailInfo;
    }

}
