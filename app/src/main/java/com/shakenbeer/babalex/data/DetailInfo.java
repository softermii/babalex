package com.shakenbeer.babalex.data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by onos on 07.09.17.
 */

public class DetailInfo {

    @SerializedName("serving_size")
    @Expose
    private String servingSize;
    @SerializedName("calories")
    @Expose
    private String calories;
    @SerializedName("total_fat")
    @Expose
    private String totalFat;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("ingredients")
    @Expose
    private String ingredients;

    /**
     * No args constructor for use in serialization
     */
    public DetailInfo() {
    }

    /**
     * @param ingredients
     * @param weight
     * @param calories
     * @param totalFat
     * @param servingSize
     */
    public DetailInfo(String servingSize, String calories, String totalFat, String weight, String ingredients) {
        super();
        this.servingSize = servingSize;
        this.calories = calories;
        this.totalFat = totalFat;
        this.weight = weight;
        this.ingredients = ingredients;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(String totalFat) {
        this.totalFat = totalFat;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

}
