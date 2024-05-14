package com.kostplan.mitkostplan.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String method;
    private int protein;
    private int calories;
    private int fat;
    private int sugar;

    @OneToMany(mappedBy = "recipeIngredients")
    private Set<Ingredient> ingredient;

    public Recipe(int id, String name, String method, int protein, int calories, int fat, int sugar, Set<Ingredient> ingredient) {
        this.id = id;
        this.name = name;
        this.method = method;
        this.protein = protein;
        this.calories = calories;
        this.fat = fat;
        this.sugar = sugar;
        this.ingredient = ingredient;
    }

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

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
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

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", method='" + method + '\'' +
                ", protein=" + protein +
                ", calories=" + calories +
                ", fat=" + fat +
                ", sugar=" + sugar +
                ", ingredient=" + ingredient +
                '}';
    }
}

