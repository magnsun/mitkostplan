package com.kostplan.mitkostplan.Controller;
import com.kostplan.mitkostplan.Entity.User;
import com.kostplan.mitkostplan.Service.UseCase;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class UiController {

    private final UseCase useCase;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UiController(UseCase useCase, PasswordEncoder passwordEncoder){
        this.useCase = useCase;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model){
        if (error != null){
            model.addAttribute("loginError", "Invalid email or password");
        }

        model.addAttribute("loginForm", new User());
        return "login";
    }


    @GetMapping("main")
    public String showMain(Model model){

        return "main";
    }

    @GetMapping("/settings")
    public String showSettings(Model model, Principal principal){
        User user = useCase.getUserByMail(principal.getName());
        model.addAttribute("isSubscribed", user.isSubscribed());
        return "settings";
    }

    // Switch to update page
    @GetMapping("settings/update")
    public String switchUpdateMenu(Model model, Principal principal) {
        //Get the logged-in users username(Email)
        String username = principal.getName();

        //get the user details
        User user = useCase.getUserByMail(username);

        model.addAttribute("updateUser", user);
        return "updatePage";
    }

    @PostMapping("settings/deleteUser")
    public String deleteUser(Principal principal, HttpSession session){
        String email = principal.getName();
        useCase.deleteUserByEmail(email);
        session.invalidate();
        return "redirect:/main/logout";
    }

    // Press the update button
    @PostMapping("updatePage/updateUser")
    public String updateUserButton(@ModelAttribute User user) {
        System.out.println(user);
        useCase.updateUser(user);
        return "redirect:/settings";
    }
    @PostMapping("settings/subscribe")
    public String changeSub(Principal principal){
        User user = useCase.getUserByMail(principal.getName());
        useCase.changeSub(user);
        return "redirect:/settings";
    }
    @GetMapping("login/create")
    public String createUser(Model model){
        model.addAttribute("createUser", new User());
        return "create";
    }

    @PostMapping("login/createUser")
    public String createUser(@ModelAttribute User user, BindingResult result){
        //If the email or password does not exist
        /*
        if (result.hasErrors()){
            return "create";
        }

         */
        //makes the password hashed
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        useCase.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/recipes")
    public String showAllRecipe(Model model){
        model.addAttribute("recipes", useCase.getAllRecipes());


        return "recipes";
    }
}
