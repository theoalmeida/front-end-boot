package com.almeida.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("name", "Theo");
        return "index";
    }

    @RequestMapping(value = "/registeruser")
    public String registerUser(Model model) {
        return "user/registeruser";
    }

}
