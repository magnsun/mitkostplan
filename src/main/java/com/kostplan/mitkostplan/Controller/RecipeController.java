package com.kostplan.mitkostplan.Controller;

import com.kostplan.mitkostplan.Entity.Ingredient;
import com.kostplan.mitkostplan.Entity.Recipe;
import com.kostplan.mitkostplan.Entity.RecipeIngredient;
import com.kostplan.mitkostplan.Entity.User;
import com.kostplan.mitkostplan.Service.UseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final UseCase useCase;
    private static  final Logger LOGGER = Logger.getLogger(RecipeController.class.getName());

    @Autowired
    private RecipeController(UseCase useCase){
        this.useCase = useCase;
    }
    @GetMapping()
    public String showAllRecipe(Model model, Principal principal){
        User user = useCase.getUserByMail(principal.getName());
        model.addAttribute("userInfo", user);
        model.addAttribute("recipes", useCase.getAllRecipes());
        return "recipes";
    }
    @GetMapping("/recipe/{id}")
    public String recipeInfo(Model model, @PathVariable int id, Principal principal){
        User user = useCase.getUserByMail(principal.getName());
        Recipe recipe = useCase.getRecipe(id);
        Map<RecipeIngredient, Integer> adjustedIngredients = useCase.calculateAdjustedRecipe(recipe, user);

        for (Map.Entry<RecipeIngredient, Integer> entry : adjustedIngredients.entrySet()){
            RecipeIngredient ingredient = entry.getKey();
            Integer quantity = entry.getValue();
            LOGGER.info("Ingredient: " + ingredient.getIngredient().getName() + ", Adjusted Quantity: " + quantity + " grams");
        }

        String method = recipe.getMethod();
        String formattedMethod = method.replace("/n","<p>") + "</p>";
        model.addAttribute("formattedMethod", formattedMethod);

        model.addAttribute("userInfo", user);
        model.addAttribute("recipe", recipe);
        model.addAttribute("adjustedIngredients", adjustedIngredients);

        return "recipeInfo";
    }

}
