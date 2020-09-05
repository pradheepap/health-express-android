package com.sg.healthexpress.main;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodInfo {

    String fdcId;
    String description;
    String dataType;
    String brandOwner;
    String ingredients;
    @SerializedName("foodNutrients")
    List<Nutrients> nutrientsList;


    public FoodInfo() {
    }

    public FoodInfo(String fdcId, String description, String dataType, String brandOwner, String ingredients, List<Nutrients> nutrientsList) {
        this.fdcId = fdcId;
        this.description = description;
        this.dataType = dataType;
        this.brandOwner = brandOwner;
        this.ingredients = ingredients;
        this.nutrientsList = nutrientsList;
    }

    public String getFdcId() {
        return fdcId;
    }

    public void setFdcId(String fdcId) {
        this.fdcId = fdcId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getBrandOwner() {
        return brandOwner;
    }

    public void setBrandOwner(String brandOwner) {
        this.brandOwner = brandOwner;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public List<Nutrients> getNutrientsList() {
        return nutrientsList;
    }

    public void setNutrientsList(List<Nutrients> nutrientsList) {
        this.nutrientsList = nutrientsList;
    }

    @Override
    public String toString() {
        return "FoodInfo{" +
                "fdcId='" + fdcId + '\'' +
                ", description='" + description + '\'' +
                ", dataType='" + dataType + '\'' +
                ", brandOwner='" + brandOwner + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", nutrientsList=" + nutrientsList +
                '}';
    }
}
