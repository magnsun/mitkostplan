package com.kostplan.mitkostplan.Repository;

import com.kostplan.mitkostplan.Entity.User;
import com.kostplan.mitkostplan.Entity.Recipe;
import com.kostplan.mitkostplan.Entity.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Repository
public class DbController {

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public DbController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Optional <User> findByMail(String email){
        String sql = "SELECT * FROM user WHERE email =?";
        try {
          User user = jdbcTemplate.queryForObject(sql, userRowMapper(), email);
          return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    //Update item
    public void updateUser(User user) {
        try {
            String sql = "UPDATE user set name = ?, email = ?, password = ?, sex = ?, datebirth = ?, heightcm = ?, weightKg = ?, bmr = ?, goal = ?, subscribed = ? where id = ?";
            jdbcTemplate.update(sql, user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getSex(), user.getDateBirth(), user.getHeightCm(), user.getWeightKg(), user.getBmr(), user.getGoal(), user.isSubscribed());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    //Create item
    public void createUser(User user) {
        String sql = "INSERT INTO user (name, email, password, sex, dateBirth, heightCm, weightKg, goal) VALUES (?,?,?,?,?,?,?,?)";
        try{
            System.out.println(user);
            jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword(), user.getSex(),user.getDateBirth(),user.getHeightCm(), user.getWeightKg(),user.getGoal());
        } catch (DataAccessException e) {
            throw new RuntimeException("Error creating user: " + e.getMessage(), e);
        }
    }

    // Delete item
    public void deleteUserById(int id) {
        try {
            String sql = "DELETE FROM User WHERE id =?";
            jdbcTemplate.update(sql, id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("error 404 ");
        }
    }

    //changeSubscribtion
    /*
    public boolean changeSubscribtion(){
        String sql = "SELECT subscribed FROM User WHERE id = ?";
        try{
            String storedSubscribtion = jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
            reture storedSubscribtion != null;
        }catch (DataAccessException e){
            reture false;
        }
    }*/

    //Get all Recipes
    public List<Recipe> getAllRecipes() {
        try {
            String sql = "SELECT * FROM recipe";
            return jdbcTemplate.query(sql, recipeRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
    }
    }

    // get recipe
    public Optional<Recipe> getRecipe(int id){
        String sql = "SELECT * FROM recipe WHERE id=?";
        try{
            Recipe recipe = jdbcTemplate.queryForObject(sql, new Object[]{id}, recipeRowMapper());
            return Optional.ofNullable(recipe);
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    // RowMapper
        private RowMapper<Recipe> recipeRowMapper(){
            return (rs, rowNum) -> {
                Recipe recipe = new Recipe();
                recipe.setId(rs.getInt("id"));
                recipe.setName(rs.getString("Name"));
                recipe.setMethod(rs.getString("Method"));
                recipe.setProtein(rs.getInt("protein"));
                recipe.setCalories(rs.getInt("calories"));
                recipe.setFat(rs.getInt("fat"));
                recipe.setSugar(rs.getInt("sugar"));
                return recipe;
            };
        }

        private RowMapper<User> userRowMapper(){
            return (rs, rowNum) -> {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setSex(rs.getByte("sex"));
                user.setDateBirth(rs.getDate("dateBirth"));
                user.setHeightCm(rs.getInt("heightCm"));
                user.setWeightKg(rs.getInt("weightKg"));
                user.setBmr(rs.getDouble("bmr"));
                user.setGoal(rs.getByte("goal"));
                user.setSubscribed(rs.getBoolean("subscribed"));

                return user;
            };
        }


}
