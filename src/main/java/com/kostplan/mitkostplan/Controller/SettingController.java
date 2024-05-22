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

    @Autowired
    private SettingController(UseCase useCase){
        this.useCase = useCase;
    }


    @GetMapping()
    public String showSettings(Model model, Principal principal){
        User user = useCase.getUserByMail(principal.getName());
        model.addAttribute("isSubscribed", user.isSubscribed());
        return "settings";
    }

    // Switch to update page
    @GetMapping("/update")
    public String switchUpdateMenu(Model model, Principal principal) {
        //Get the logged-in users username(Email)
        String username = principal.getName();

        //get the user details
        User user = useCase.getUserByMail(username);

        model.addAttribute("updateUser", user);
        return "updatePage";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(Principal principal, HttpSession session){
        String email = principal.getName();
        useCase.deleteUserByEmail(email);
        session.invalidate();
        return "redirect:/main/logout";
    }

    // Press the update button
    @PostMapping("/updateUser")
    public String updateUserButton(@ModelAttribute User user) {
        System.out.println(user);
        useCase.updateUser(user);
        return "redirect:/settings";
    }
    @PostMapping("/subscribe")
    public String changeSub(Principal principal){
        User user = useCase.getUserByMail(principal.getName());
        useCase.changeSub(user);
        return "redirect:/settings";
    }
}
