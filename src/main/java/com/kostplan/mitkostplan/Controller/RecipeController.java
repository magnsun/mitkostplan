package com.kostplan.mitkostplan.Controller;

import com.kostplan.mitkostplan.Entity.Recipe;
import com.kostplan.mitkostplan.Entity.User;
import com.kostplan.mitkostplan.Service.UseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final UseCase useCase;

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
    public String recipeInfo(Model model, @PathVariable int id){
        Recipe recipe = useCase.getRecipe(id);
        model.addAttribute("recipe", recipe);
        model.addAttribute("recipeIngredient", useCase.recipeIngredient(recipe.getId()));
        return "recipeInfo";
    }
}
