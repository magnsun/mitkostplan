package com.kostplan.mitkostplan.Controller;

import com.kostplan.mitkostplan.Entity.User;
import com.kostplan.mitkostplan.Service.UseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UseCase useCase;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private LoginController(UseCase useCase, PasswordEncoder passwordEncoder){
        this.useCase = useCase;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping()
    public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model){
        if (error != null){
            model.addAttribute("loginError", "Invalid email or password");
        }

        model.addAttribute("loginForm", new User());
        return "login";
    }

    @GetMapping("/create")
    public String createUser(Model model){
        model.addAttribute("createUser", new User());
        return "create";
    }

    @PostMapping("/createUser")
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
