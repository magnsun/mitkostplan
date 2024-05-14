package com.kostplan.mitkostplan.Entity;

import jakarta.persistence.*;

@Entity
public class Ingredient {

    @Id
    private int id;
    private String name;
    private int amount;

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

    public Recipe getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(Recipe recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }
}
