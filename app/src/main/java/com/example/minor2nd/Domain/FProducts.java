package com.example.minor2nd.Domain;

import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

public class FProducts implements Serializable {

    private int CategoryId;
    private int id;
    private String Description;
    private int LocationId;
    private double Price;
    private String ImagePath;
    private int PriceId;
    private boolean BestSell;
    private double StarRate;
    private  int TimeId;
    private int TimeValue;
    private String Title;
    private int numberInCart;

    public FProducts() {
    }

    @Override
    public String toString() {
        return Title ;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getLocationId() {
        return LocationId;
    }

    public void setLocationId(int locationId) {
        LocationId = locationId;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public int getPriceId() {
        return PriceId;
    }

    public void setPriceId(int priceId) {
        PriceId = priceId;
    }

    public boolean isBestSell() {
        return BestSell;
    }

    public void setBestSell(boolean bestSell) {
        BestSell = bestSell;
    }

    public double getStarRate() {
        return StarRate;
    }

    public void setStarRate(double starRate) {
        StarRate = starRate;
    }

    public int getTimeId() {
        return TimeId;
    }

    public void setTimeId(int timeId) {
        TimeId = timeId;
    }

    public int getTimeValue() {
        return TimeValue;
    }

    public void setTimeValue(int timeValue) {
        TimeValue = timeValue;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
