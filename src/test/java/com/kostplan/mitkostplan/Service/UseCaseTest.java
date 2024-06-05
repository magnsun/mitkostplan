package com.kostplan.mitkostplan.Service;

import com.kostplan.mitkostplan.Entity.Ingredient;
import com.kostplan.mitkostplan.Entity.Recipe;
import com.kostplan.mitkostplan.Entity.RecipeIngredient;
import com.kostplan.mitkostplan.Entity.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UseCaseTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    UseCase useCase

    ;

    private User user;

    private Recipe recipe;

    private RecipeIngredient recipeIngredient0;
    private RecipeIngredient recipeIngredient1;
    private RecipeIngredient recipeIngredient2;
    private RecipeIngredient recipeIngredient3;

    private Ingredient ing0;
    private Ingredient ing1;
    private Ingredient ing2;
    private Ingredient ing3;

    @BeforeEach

    void setUp(){
    user = new User ();
        MockitoAnnotations.openMocks(this);


        user.setSex((byte) 0);
        user.setDateBirth(Date.valueOf("2000-01-01"));
        user.setHeightCm(186);
        user.setWeightKg(75);
        user.setGoal((byte)2);
        user.setActivity((byte)3);

    recipe = new Recipe();

        recipe.setId(1);
        recipe.setMealType((byte)0);

    recipeIngredient0 = new RecipeIngredient();
        recipeIngredient0.setQuantity(200);
    ing0 = new Ingredient();
        ing0.setCalories(50);
        recipeIngredient0.setIngredient(ing0);


    recipeIngredient1 = new RecipeIngredient();
        recipeIngredient1.setQuantity(50);
    ing1 = new Ingredient();
        ing1.setCalories(85);
        recipeIngredient1.setIngredient(ing1);


    recipeIngredient2 = new RecipeIngredient();
        recipeIngredient2.setQuantity(25);
    ing2 = new Ingredient();
        ing2.setCalories(100);
        recipeIngredient2.setIngredient(ing2);


    recipeIngredient3 = new RecipeIngredient();
        recipeIngredient3.setQuantity(35);
    ing3 = new Ingredient();
        ing3.setCalories(86);
        recipeIngredient3.setIngredient(ing3);

//    when(jdbcTemplate.query(
//            "SELECT * FROM RecipeIngredient WHERE recipe_id = ?",
//            new Object[]{1},
//            new RecipeIngredientRowMapper())
//            ).thenReturn(Arrays.asList(recipeIngredient0, recipeIngredient1, recipeIngredient2, recipeIngredient3));


    }


    @Test
    void testRecipeIngredient(){




        Map<Byte, Double> mealCalories = new HashMap<>();
        mealCalories.put((byte)0, 2000.0);
        when(user.splitDailyCalories()).thenReturn(mealCalories);

        Map<RecipeIngredient, Integer> resultCalories = useCase.calculateIngredientAmount(recipe, user);

        double totalRecipeCalories = ((50.0 / 100) * 200) + ((85.0 / 100) * 50) + ((100.0 / 100) * 25) + ((86.0 / 100) * 35);
        double scalefactor = 2000.0/totalRecipeCalories;

        Map<RecipeIngredient, Integer> expectedCalories = new HashMap<>();
        expectedCalories.put(recipeIngredient0, (int) (200*scalefactor));
        expectedCalories.put(recipeIngredient1, (int) (50*scalefactor));
        expectedCalories.put(recipeIngredient2, (int) (25*scalefactor));
        expectedCalories.put(recipeIngredient3, (int) (35*scalefactor));

        assertEquals(expectedCalories,resultCalories);






    }




    }



