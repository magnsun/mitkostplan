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
    public void createUser(User user) throws SQLException {
        try{
            String sql = "INSERT INTO user (name, email, password, sex, dateBirth, heightCm, weightKg, bmr, goal, subscribed) VALUES (?,?,?,?,?,?,?,?,?,?)";
            jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword(), user.getSex(),user.getDateBirth(),user.getHeightCm()+
            user.getWeightKg(),user.getBmr(),user.getGoal(),user.isSubscribed());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
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

    // Authenticate log in
    public Boolean authenticateLogin(String email){
        String sql = "SELECT password FROM User WHERE email = ?";
        try {
            String storedPassword = jdbcTemplate.queryForObject(sql, new Object[]{email}, String.class);
            return storedPassword != null && storedPassword.equals(email);
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    //changeSubscribtion
    /*
    public boolean changeSubscribtion(){
        String sql = "SELECT subscribed FROM User WHERE id = ?";
        try{
            String storedSubscribtion = jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
            reture storedSubscribtion != null;
        }(DataAccessException e){
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


}
