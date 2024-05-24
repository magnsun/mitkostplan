package com.kostplan.mitkostplan.Service;

import com.kostplan.mitkostplan.Entity.Recipe;
import com.kostplan.mitkostplan.Entity.User;
import com.kostplan.mitkostplan.Repository.DbController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UseCase {

    private final DbController dbController;

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
  public Map<RecipeIngredient, Integer> calculateIngredientAmount(Recipe recipe, User user){

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
    // Do not make unit test of this
    public void getUserRecipes(User user){
        double bmr = user.getBmr();

        double breakfastCalories = bmr * 0.4;
        double lunchCalories = bmr * 0.3;
        double dinnerCalories = bmr * 0.3;

    }

    // Do not make unit test of this
    public double calculateAmountOfIngredient(double gramOfIngredient, double nutritionPr100Gram, double sumOfNutritionPr100Gram, double customersRequiredNutrition) {
        double requiredAmountOfIngredient;

        requiredAmountOfIngredient = 100 * (((nutritionPr100Gram / 100 * gramOfIngredient) / sumOfNutritionPr100Gram * 100) * customersRequiredNutrition);

        return requiredAmountOfIngredient;
    }
}
