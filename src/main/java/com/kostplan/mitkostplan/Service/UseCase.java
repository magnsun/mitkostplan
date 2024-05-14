package com.kostplan.mitkostplan.Service;

import com.kostplan.mitkostplan.Entity.User;
import com.kostplan.mitkostplan.Repository.DbController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UseCase {

    private final DbController dbController;

    @Autowired
    public UseCase(DbController dbController){
        this.dbController = dbController;
    }

    public void updateUser(User user) {
        dbController.updateUser(user);
    }

    public void createUser(User user){
        try {
            dbController.createUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
