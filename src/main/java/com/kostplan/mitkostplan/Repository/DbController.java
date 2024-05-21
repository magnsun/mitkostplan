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
            String sql = "UPDATE user set name = ?, sex = ?, datebirth = ?, heightcm = ?, weightKg = ?, bmr = ?, goal = ?, activity = ? where email = ?";
            jdbcTemplate.update(sql, user.getName(), user.getSex(), user.getDateBirth(), user.getHeightCm(), user.getWeightKg(), user.getBmr(), user.getGoal(), user.getActivity(), user.getEmail());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeSub(User user){
        try {
            String sql = "UPDATE user SET subscribed = NOT (subscribed) WHERE email = ?";
            jdbcTemplate.update(sql, user.getEmail());
        } catch (DataAccessException e){
            throw new RuntimeException(e);
        }
    }

    //Create item
    public void createUser(User user) {
        String sql = "INSERT INTO user (name, email, password, sex, dateBirth, heightCm, weightKg, bmr, goal, activity) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try{
            jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword(), user.getSex(),user.getDateBirth(),user.getHeightCm(), user.getWeightKg(),user.getBmr(), user.getGoal(),user.getActivity());
        } catch (DataAccessException e) {
            throw new RuntimeException("Error creating user: " + e.getMessage(), e);
        }
        System.out.println(user.getAge());
    }

    // Delete item
    public void deleteUserByEmail(String email) {
        try {
            String sql = "DELETE FROM User WHERE email =?";
            jdbcTemplate.update(sql, email);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("error 404 ");
        }
    }


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
                recipe.setCalories(rs.getInt("calories"));
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
                user.setBmr((int) rs.getDouble("bmr"));
                user.setGoal(rs.getByte("goal"));
                user.setActivity(rs.getByte("activity"));
                user.setSubscribed(rs.getBoolean("subscribed"));

                return user;
            };
        }


}
