package com.kostplan.mitkostplan.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForsideController {

   @RequestMapping("/")
    public String redirectToForside(){
        return "redirect:/forside";
    }

    @GetMapping("forside")
    public String showForside(){
       return "forside";
    }

}
