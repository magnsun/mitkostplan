package com.kostplan.mitkostplan.Controller;
import com.kostplan.mitkostplan.Entity.User;
import com.kostplan.mitkostplan.Service.UseCase;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/settings")
public class SettingController {

    private final UseCase useCase;

    /*
    Skal undersøges
     */
    @Autowired
    private SettingController(UseCase useCase){
        this.useCase = useCase;
    }

    /*
    Bruger Principal til at få brugerens information med getUserByMail
    Tjekker om brugeren er subscribed og sætter infoen ind i "isSubscribed" pladsen i settings.html
    sender dig til settings.html siden
     */
    @GetMapping()
    public String showSettings(Model model, Principal principal){
        User user = useCase.getUserByMail(principal.getName());
        model.addAttribute("isSubscribed", user.isSubscribed());
        return "settings";
    }

    /*
    Finder user info med getUserByMail
    og insætter dem i updateUser pladsen i settings.html
     */
    @GetMapping("/update")
    public String switchUpdateMenu(Model model, Principal principal) {
        //Get the logged-in users username(Email)
        String username = principal.getName();

        //get the user details
        User user = useCase.getUserByMail(username);

        model.addAttribute("updateUser", user);
        return "updatePage";
    }

    /*
    Bruger brugerens brugernavn til at køre deleteUserByEmail metoden
    som ender med at sende et SQL query som sletter brugerens information fra databasen
    session.invalidate() fjerne din tilladelse til https sessionen
    Sender dig tilbage til login.html
     */
    @PostMapping("/deleteUser")
    public String deleteUser(Principal principal, HttpSession session){
        String email = principal.getName();
        useCase.deleteUserByEmail(email);
        session.invalidate();
        return "redirect:/main/logout";
    }

    /*
    Når du trykker på update knappen i updatePage.html
    kalder updateUser metoden fra UseCase klassen den bruger User objektet fra updatePage.html
    og sender User videre til UseCase som ender med at køre et SQL query som opdatere informationen
    i vores database
     */
    @PostMapping("/updateUser")
    public String updateUserButton(@ModelAttribute User user) {
        System.out.println(user);
        useCase.updateUser(user);
        return "redirect:/settings";
    }

    /*
    Finder brugeren med Principal og getUserByMail metoden
    bruger changeSub metoden fra UseCase klassen dette ender med at sende et SQL query som opdatere vores database
    Sender tilbage til settings.html
     */
    @PostMapping("/subscribe")
    public String changeSub(Principal principal){
        User user = useCase.getUserByMail(principal.getName());
        useCase.changeSub(user);
        return "redirect:/settings";
    }
}
