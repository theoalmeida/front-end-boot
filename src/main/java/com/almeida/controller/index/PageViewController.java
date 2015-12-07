package com.almeida.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageViewController {

    @RequestMapping(value = "/index")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/contacts")
    public String contacts(Model model) {
        return "contacts";
    }

    @RequestMapping(value = "/registeruser")
    public String registerUser(Model model) {
        return "user/registeruser";
    }

}
