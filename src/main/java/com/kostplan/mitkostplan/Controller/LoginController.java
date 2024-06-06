package com.kostplan.mitkostplan.Controller;

import com.kostplan.mitkostplan.Entity.User;
import com.kostplan.mitkostplan.Service.UseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/* RequestMapping er vores default sti
* @Controller definere klassen som en controller*/
@Controller
@RequestMapping("/login")
public class LoginController {

    private final UseCase useCase;
    private final PasswordEncoder passwordEncoder;

    /*@Autowired skal læses på.*/
    @Autowired
    private LoginController(UseCase useCase, PasswordEncoder passwordEncoder){
        this.useCase = useCase;
        this.passwordEncoder = passwordEncoder;
    }

/*
@RequestParam anmoder om værdien error fra login.html og required = false betyder at koden stadig kan kører hvis den ikke modtager en værdi
if (error != null) betyder at hvis der er en error værdi så bliver beskeden i model.addAttribute vist
"loginError" er pladsen i login.html og "Invalid email or password" er beskeden

model.addAttribute("loginForm", new User()) den søger efter en bruger og tjekker om login informationen matcher en bruger i databasen
return "login" skifter siden til login.html
*/
    @GetMapping()
    public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model){
        if (error != null){
            model.addAttribute("loginError", "Invalid email or password");
        }
        model.addAttribute("loginForm", new User());
        return "login";
    }

    /*
    Her skifter vi over til vores "create.html"
    Den opretter med det samme et User objekt
     */
    @GetMapping("/create")
    public String createUser(Model model){
        model.addAttribute("createUser", new User());
        return "create";
    }

    /*
    Den tager User objektet fra create.html
    Den bruger encodedPassword til at encode brugerens password
    Den kører så createUser metoden fra useCase klassen hvilket ender med at kører et SQL query som indsætter bruger informationen i vores database
    Til sidst sender den dig tilbage til login.html siden
     */
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
