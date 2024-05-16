package com.kostplan.mitkostplan.Service;

import com.kostplan.mitkostplan.Entity.Recipe;
import com.kostplan.mitkostplan.Entity.User;
import com.kostplan.mitkostplan.Repository.DbController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
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
       dbController.updateUser(user);
    }

    //delete items
    public void deleteUserById(int id){
        dbController.deleteUserById(id);
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
//    public List<User> getAllRecipes(){
//        return dbController.getAllRecipes();
//    }

    // Create user
    public void createUser(User user){
        user.setBmr(calculateBMR(user));
        dbController.createUser(user);
    }

    public double calculateBMR(User user){

        double bmr;
        int age = user.getAge();

        if (user.getSex() == 0) {
            bmr = 88.362 + (13.397 * user.getWeightKg()) + (4.799 * user.getHeightCm()) - (5.677 * age);
        } else if (user.getSex() == 1) {
            bmr = 447.593 + (9.247 * user.getWeightKg()) + (3.098 * user.getHeightCm()) - (4.330 * age);
        } else {
            bmr = 359.239 + (4.15 * user.getWeightKg()) + (1.701 * user.getHeightCm()) - (1.347 * age);
        }

        return bmr;
    }

}
