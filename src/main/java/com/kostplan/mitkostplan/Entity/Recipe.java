package com.kostplan.mitkostplan.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private byte mealType;
    private String method;
    private String protein;
    private int calories;
    @OneToMany(mappedBy = "recipeIngredients")
    private Set<Ingredient> ingredient;

    public Recipe(int id, String name, byte mealType, String method, String protein, int calories, Set<Ingredient> ingredient) {
        this.id = id;
        this.name = name;
        this.mealType = mealType;
        this.method = method;
        this.protein = protein;
        this.calories = calories;
        this.ingredient = ingredient;
    }



    // up stilling af angving informson  af Recipe
    public Recipe() {}

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

    public byte getMealType() {
        return mealType;
    }

    public void setMealType(byte mealType) {
        this.mealType = mealType;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Set<Ingredient> getIngredient() {
        return ingredient;
    }

    public void setIngredient(Set<Ingredient> ingredient) {
        this.ingredient = ingredient;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", method='" + method + '\'' +
                ", calories=" + calories +
                ", ingredient=" + ingredient +
                '}';
    }
}

