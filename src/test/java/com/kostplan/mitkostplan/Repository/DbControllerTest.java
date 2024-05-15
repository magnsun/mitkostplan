package com.kostplan.mitkostplan.Repository;

import com.kostplan.mitkostplan.Entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DbControllerTest {

    @Mock
    private JdbcTemplate jdbcTemplate;


    @Test
    public void testgetAllRecipes() throws  SQLException{
            String sql = "SELECT * FROM recipe";
        System.out.println();
    }

    @Test
    public void testupdateUser()throws SQLException{

    }



    @Test
    public void testCreateUser( )throws SQLException{

        User user = new User ();
        user.setName("Hans");
        user.setEmail("hans@mail.dk");
        user.setPassword("1230");
        user.setBmr(Double.parseDouble("25,6"));
        user.setSex(Byte.parseByte("Male"));
        user.setDateBirth(Date.valueOf("2000-01-01"));
        user.setHeightCm(186);
        user.setWeightKg(75);
        user.setGoal(Byte.parseByte("Gain"));


    }

}