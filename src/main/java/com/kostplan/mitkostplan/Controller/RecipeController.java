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

    /*
    Skal undersøges
     */
    @Autowired
    private RecipeController(UseCase useCase){
        this.useCase = useCase;
    }

    /*
    Principal objektet er en del af spring security hvilket husker hvilken bruger som er logget ind
    Et User objekt bliver oprettet med getUserByMail metoden fra UseCase klassen med principal værdien til at finde brugeren
    model.addAttribute giver så User objektets information til "userInfo" pladsen i recipes.html
    Den bruger så getAllRecipes metoden fra UseCase klassen til at få et list objekt som indenholder
    alle obskrifter fra vores database og sætter dem i "recipes" pladsen i recipes.html filen
     */
    @GetMapping()
    public String showAllRecipe(Model model, Principal principal){
        User user = useCase.getUserByMail(principal.getName());
        model.addAttribute("userInfo", user);
        model.addAttribute("recipes", useCase.getAllRecipes());
        return "recipes";
    }

    /*
    @GetMapping("/recipe/{id}") dette gør at vores url er anderledes basseret på opskriften
    @PathVariable referere til stien i @GetMapping og giver url et unikt id
    Et User objekt bliver oprettet med getUserByMail metoden fra UseCase klassen med principal
    værdien til at finde brugeren
    Et Recipe objekt bliver oprettet med det id du får når du trykker på en opskrift i recipes.html
    Der bliver så oprettet et Map objekt som indenholder en liste af ingredienser som er justeret til brugeren
    for løkken indsætter ingrediensernes information som så bliver sat ind i
    recipe og adjustedIngredients pladsen i recipeInfo.html
    Den sender dig så ind i recipeInfo.html siden
     */
    @GetMapping("/recipe/{id}")
    public String recipeInfo(Model model, @PathVariable int id, Principal principal){
        User user = useCase.getUserByMail(principal.getName());
        Recipe recipe = useCase.getRecipe(id);
        Map<RecipeIngredient, Integer> adjustedIngredients = useCase.calculateIngredientAmount(recipe, user);

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

        List<Integer> nutritionList = new ArrayList<>();
        nutritionList = useCase.calculateIngredientAmountNutrients(recipe, user);

        model.addAttribute("nutritionCalories", nutritionList.get(0));
        model.addAttribute("nutritionFat", nutritionList.get(1));
        model.addAttribute("nutritionProtein", nutritionList.get(2));
        model.addAttribute("nutritionCarbohydrates", nutritionList.get(3));

        return "recipeInfo";
    }

}
