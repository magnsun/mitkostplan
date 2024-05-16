package com.kostplan.mitkostplan.Controller;
import com.kostplan.mitkostplan.Entity.User;
import com.kostplan.mitkostplan.Entity.Recipe;
import com.kostplan.mitkostplan.Entity.Ingredient;
import com.kostplan.mitkostplan.Service.UseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

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
    public String showLoginForm(Model model){
        model.addAttribute("loginForm", new User());
        return "login";
    }

    @GetMapping("main")
    public String showMain(Model model){

        return "main";
    }

    @GetMapping("/settings")
    public String showSettings(){
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

    // Press the update button
    @PostMapping("updatePage/updateUser")
    public String updateUserButton(@ModelAttribute User user) {
        System.out.println(user);
        useCase.updateUser(user);
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
}
