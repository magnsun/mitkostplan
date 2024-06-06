package com.kostplan.mitkostplan.Service;

import com.kostplan.mitkostplan.Controller.RecipeController;
import com.kostplan.mitkostplan.Entity.Ingredient;
import com.kostplan.mitkostplan.Entity.Recipe;
import com.kostplan.mitkostplan.Entity.RecipeIngredient;
import com.kostplan.mitkostplan.Entity.User;
import com.kostplan.mitkostplan.Repository.DbController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.logging.Logger;

@Service
public class UseCase {

    private final DbController dbController;

    @Autowired
    public UseCase(DbController dbController){
        this.dbController = dbController;
    }
    private static  final Logger LOGGER = Logger.getLogger(RecipeController.class.getName());

    /*
    BMR bliver udregnet i User klassen
    updateUser bliver kørt som sender et SQL query som opdatere databasen
     */
    public void updateUser(User user) {
        user.calculateBMR();
        dbController.updateUser(user);
    }

    /*
    Kalder en metode som sender et SQL query som opdatere vores database
     */
    public void changeSub(User user){
        dbController.changeSub(user);
    }


    /*
    Kalder en metode som sender et SQL query som opdatere vores database
     */
    public void deleteUserByEmail(String email){
        dbController.deleteUserByEmail(email);
    }

    /*
    Optional betyder at det godt må være NULL
    if statementen tjekker om vi fandt en bruger og hvis den ikke gør sender den fejlbeskeden
     */
    public User getUserByMail(String email){
        Optional<User>userOptional = dbController.findByMail(email);
        if (userOptional.isPresent()){
            return userOptional.get();
        }else {
            throw new RuntimeException("User not found");
        }
    }

    /*
    Optional betyder at det godt må være NULL
    optionalRecipe.orElse(null) hvis der ikke bliver fundet noget returnere den NULL
     */
    public Recipe getRecipe(int id) {
        Optional<Recipe> optionalRecipe = dbController.getRecipe(id);
        return optionalRecipe.orElse(null);
    }

    /*
    Optional betyder at det godt må være NULL
    optionalRecipe.orElse(null) hvis der ikke bliver fundet noget returnere den NULL
    */
    public Ingredient getIngredient(int id) {
        Optional<Ingredient> optionalIngredient = dbController.getIngredient(id);
        return optionalIngredient.orElse(null);
    }

    /*
    Henter ingredienser ud fra et recipe 5id
     */
    public List<RecipeIngredient>recipeIngredient(int recipeId){
      return dbController.getRecipeIngredient(recipeId);
    }

    //get all items
    public List<Recipe> getAllRecipes(){
        return dbController.getAllRecipes();
    }

    /*
    bruger User objektet fra controlleren
    kører calculateBMR metoden fra User klassen
    kører createUser metoden som sender et SQL query som opretter en bruger i vores database
     */
    public void createUser(User user){
        user.calculateBMR();
        dbController.createUser(user);
    }

    // Do not make unit test of this
    /*
    Vi bruger metoden splitDailyCalories fra User klassen og sætter det ind i et map hvor den har udregnet kaloriefordelingen
    Så udregner den hvor mange kalorier der er i den individuelle opskrift
    Opretter totalRecipeCalories parameteren
    Henter alle ingredienser som er forbundet med opskriften vi regner på
    kører on for løkke som henter mængden fra alle ingredienserne og kalorierne fra alle ingredienserne
    og til sidst laver totalRecipeCalories parameteren som er alle ingrediensers kalorier samlet
    scaleFactor scalleringen som hvor meget ingredienserne skal øges eller sænkes med
    det bliver udregnet med mealTypeCalories/totalRecipeCalories eg. 0.8, 1.2
    laver et map som indenholder ingrediensens id og hvor meget der skal være i opskriften
    laver en for løkke som skalere vores ingredienser til brugeren og indsætter værdierne i et map
    vi returnere derefter det map
     */
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

    public List<Double> calculateIngredientAmountNutrients(Recipe recipe, User user){

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

        List<Double> caloriesList = new ArrayList<>();
        List<Double> fatList = new ArrayList<>();
        List<Double> proteinList = new ArrayList<>();
        List<Double> carbohydratesList = new ArrayList<>();

        for (RecipeIngredient ingredient : ingredientList) {
            caloriesList.add((ingredient.getIngredient().getCalories() / 100 * ingredient.getQuantity()) * scaleFactor);
            fatList.add((ingredient.getIngredient().getFat() / 100 * (ingredient.getQuantity() * scaleFactor)));
            proteinList.add((ingredient.getIngredient().getProtein() / 100 * (ingredient.getQuantity() * scaleFactor)));
            carbohydratesList.add((ingredient.getIngredient().getCarbohydrates() / 100 * (ingredient.getQuantity() * scaleFactor)));
        }

        LOGGER.info("calorieList = " + caloriesList + " FatList = " + fatList + " proteinList = " + proteinList + " carbohydratesList = " + carbohydratesList);

        double calories = 0;
        double fat = 0;
        double protein = 0;
        double carbohydrates = 0;

        for (int i = 0; i < caloriesList.size(); i++) {
            calories += caloriesList.get(i);
            fat += fatList.get(i);
            protein += proteinList.get(i);
            carbohydrates += carbohydratesList.get(i);
            LOGGER.info("calories = " + calories + " fat = " + fat + " protein = " +  protein + " carbohydrates = " + carbohydrates);
        }

        BigDecimal caloriesBD = new BigDecimal(calories).setScale(1, RoundingMode.HALF_UP);
        BigDecimal fatBD = new BigDecimal(fat).setScale(1, RoundingMode.HALF_UP);
        BigDecimal proteinBD = new BigDecimal(protein).setScale(1, RoundingMode.HALF_UP);
        BigDecimal carbohydratesBD = new BigDecimal(carbohydrates).setScale(1, RoundingMode.HALF_UP);

        List<Double> nutritionList = new ArrayList<>();
        nutritionList.add(caloriesBD.doubleValue());
        nutritionList.add(fatBD.doubleValue());
        nutritionList.add(proteinBD.doubleValue());
        nutritionList.add(carbohydratesBD.doubleValue());

        return nutritionList;
    }
}
