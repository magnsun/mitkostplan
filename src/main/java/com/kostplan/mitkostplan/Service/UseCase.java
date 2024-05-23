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

    public Optional<Recipe>getRecipe(int id){
        return dbController.getRecipe(id);
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
