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

    @InjectMocks
    private DbControllerTest dbControllerTest;


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
        user.setBmr(25.6);
        user.setSex((byte) 1);
        user.setDateBirth(Date.valueOf("2000-01-01"));
        user.setHeightCm(186);
        user.setWeightKg(75);
        user.setGoal((byte)2);

        assertNotNull(user);
        assertEquals("Hans", user.getName());
        assertEquals("hans@mail.dk", user.getEmail());


    }

}