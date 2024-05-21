package com.kostplan.mitkostplan.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Ingredient {

    @Id
    private int id;
    private String name;
    private int amount;
    private int calories;
    private int fat;
    private int protein;
    private int carbonhydrates;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipeIngredients;


    public Ingredient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbonhydrates() {
        return carbonhydrates;
    }

    public void setCarbonhydrates(int carbonhydrates) {
        this.carbonhydrates = carbonhydrates;
    }
}
