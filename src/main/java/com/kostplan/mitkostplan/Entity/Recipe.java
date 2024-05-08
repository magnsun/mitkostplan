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

    @OneToMany(mappedBy = "recipeingredients")
    private Set<Ingredient> ingredient;

    public Recipe(int id, String name, String method, Set<Ingredient> ingredient) {
        this.id = id;
        this.name = name;
        this.method = method;
        this.ingredient = ingredient;
    }
    public Recipe() {}
}
