package com.kostplan.mitkostplan.Service;

import com.kostplan.mitkostplan.Entity.Recipe;
import com.kostplan.mitkostplan.Entity.User;
import com.kostplan.mitkostplan.Repository.DbController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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
    public Optional<User>findByMail(String email){
          return dbController.findByMail(email);
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
        dbController.createUser(user);
    }

}
