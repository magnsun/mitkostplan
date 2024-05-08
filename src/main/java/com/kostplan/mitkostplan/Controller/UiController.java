package com.kostplan.mitkostplan.Controller;

import com.kostplan.mitkostplan.Service.UseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UiController {

    private final UseCase useCase;

    @Autowired
    private UiController(UseCase useCase){
        this.useCase = useCase;
    }



}
