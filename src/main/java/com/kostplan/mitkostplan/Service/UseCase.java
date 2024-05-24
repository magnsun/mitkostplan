package com.kostplan.mitkostplan.Service;

import com.kostplan.mitkostplan.Controller.RecipeController;
import com.kostplan.mitkostplan.Entity.Ingredient;
import com.kostplan.mitkostplan.Entity.Recipe;
import com.kostplan.mitkostplan.Entity.RecipeIngredient;
import com.kostplan.mitkostplan.Entity.User;
import com.kostplan.mitkostplan.Repository.DbController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.logging.Logger;

@Service
public class UseCase {

    private final DbController dbController;
    private static  final Logger LOGGER = Logger.getLogger(RecipeController.class.getName());
    @Autowired
    public UseCase(DbController dbController){
        this.dbController = dbController;
    }

    //update items
    public void updateUser(User user) {
        user.calculateBMR();
        dbController.updateUser(user);
    }

    public void changeSub(User user){
        dbController.changeSub(user);
    }


    //delete items
    public void deleteUserByEmail(String email){
        dbController.deleteUserByEmail(email);
    }

    //find item by x item
    public User getUserByMail(String email){
        Optional<User>userOptional = dbController.findByMail(email);
        if (userOptional.isPresent()){
            return userOptional.get();
        }else {
            throw new RuntimeException("User not found");
        }
    }
    public Recipe getRecipe(int id) {
        Optional<Recipe> optionalRecipe = dbController.getRecipe(id);
        return optionalRecipe.orElse(null);
    }

    public Ingredient getIngredient(int id) {
        Optional<Ingredient> optionalIngredient = dbController.getIngredient(id);
        return optionalIngredient.orElse(null);
    }

    public List<RecipeIngredient>recipeIngredient(int recipeId){
      return dbController.getRecipeIngredient(recipeId);
    }

    //get all items
    public List<Recipe> getAllRecipes(){
        return dbController.getAllRecipes();
    }

    // Create user
    public void createUser(User user){
        user.calculateBMR();
        dbController.createUser(user);
    }

    // Do not make unit test of this
  public Map<RecipeIngredient, Integer> calculateAdjustedRecipe(Recipe recipe, User user){

        LOGGER.info("Calculating adjusted recipe for Recipe ID: " + recipe.getId() + " and User ID: " + user.getId());

        Map<Byte, Double> mealCalories = user.splitDailyCalories();
        double mealTypeCalories = mealCalories.get(recipe.getMealType());

        LOGGER.info("mealType Calories: " + mealTypeCalories);

        double totalRecipeCalories = 0;


        List<RecipeIngredient> ingredientList = dbController.getRecipeIngredient(recipe.getId());

        for (RecipeIngredient recipeIngredient : ingredientList){
            double gramOfIngredient = recipeIngredient.getQuantity();
            double caloriesPer100Gram = recipeIngredient.getIngredient().getCalories();
            totalRecipeCalories += (caloriesPer100Gram/100)*gramOfIngredient;
        }

        LOGGER.info("Total Recipe Calories: " + totalRecipeCalories);

        double scaleFactor = mealTypeCalories/totalRecipeCalories;

        LOGGER.info("Scale Factor: " + scaleFactor);

        Map<RecipeIngredient, Integer> adjustedIngredients = new HashMap<>();

        for (RecipeIngredient ingredient : ingredientList){
            double originalQuantity = ingredient.getQuantity();
            double adjustedQuantity = originalQuantity * scaleFactor;
            adjustedIngredients.put(ingredient, (int) adjustedQuantity);
        }
        LOGGER.info("Adjusted ingredients: " + adjustedIngredients);
        return adjustedIngredients;
  }
}
