package com.example.lab02_gridview_spinner;

public class Dish {
    private String dishName;
    private Thumbnail thumbnail;
    private boolean promotion;

    public Dish() {
    }

    public Dish(String str, Thumbnail thumbnail, boolean bool) {
        this.dishName = str;
        this.thumbnail = thumbnail;
        this.promotion = bool;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }
}
