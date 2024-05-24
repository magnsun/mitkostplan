package com.kostplan.mitkostplan.Controller;

import com.kostplan.mitkostplan.Entity.User;
import com.kostplan.mitkostplan.Service.UseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/main")
public class MainController {

    private final UseCase useCase;

    @Autowired
    private MainController(UseCase useCase){
        this.useCase = useCase;
    }


    @GetMapping()
    public String showMain(Model model, Principal principal){
        User user = useCase.getUserByMail(principal.getName());
        model.addAttribute("userInfo", user);
        return "main";
    }

}
