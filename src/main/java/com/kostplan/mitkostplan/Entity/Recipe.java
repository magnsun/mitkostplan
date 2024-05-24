package com.kostplan.mitkostplan.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private byte mealType;
    private String method;
    private int protein;
    private int fat;
    private int calories;
    private int carbonhydrates;
    @OneToMany(mappedBy = "recipe")
    private Set<RecipeIngredient> recipeIngredients;

    public Recipe() {
    }

    public Recipe(int id, String name, byte mealType, String method, int protein, int fat, int calories, int carbonhydrates, Set<RecipeIngredient> recipeIngredients) {
        this.id = id;
        this.name = name;
        this.mealType = mealType;
        this.method = method;
        this.protein = protein;
        this.fat = fat;
        this.calories = calories;
        this.carbonhydrates = carbonhydrates;
        this.recipeIngredients = recipeIngredients;
    }

    public int calorieIntakeForIngredient(int caloriePr100Gram, int requiredIngredientAmount){
        return caloriePr100Gram/100 * requiredIngredientAmount;
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

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getCarbonhydrates() {
        return carbonhydrates;
    }

    public void setCarbonhydrates(int carbonhydrates) {
        this.carbonhydrates = carbonhydrates;
    }

    public Set<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(Set<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mealType=" + mealType +
                ", protein='" + protein + '\'' +
                ", calories=" + calories +
                ", recipeIngredients=" + recipeIngredients +
                '}';
    }
}