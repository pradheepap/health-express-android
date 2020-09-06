package com.sg.healthexpress.main;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodResponse {

    String message;
    String time;
    FoodInfo foodInfo;

    public FoodResponse() {
    }

    public FoodResponse(String message, String time, FoodInfo foodInfo) {
        this.message = message;
        this.time = time;
        this.foodInfo = foodInfo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public FoodInfo getFoodInfo() {
        return foodInfo;
    }

    public void setFoodInfo(FoodInfo foodInfo) {
        this.foodInfo = foodInfo;
    }

    @Override
    public String toString() {
        return "FoodResponse{" +
                "message='" + message + '\'' +
                ", time='" + time + '\'' +
                ", foodInfo=" + foodInfo +
                '}';
    }
}
