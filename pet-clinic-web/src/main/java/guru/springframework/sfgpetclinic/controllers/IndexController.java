package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String index(){
        return "index";
    }

    @RequestMapping({"/oups", "/oups/"})
    public String errorHandler(Model model){
        //model.addAttribute("owners", ownerService.findAll());
        return "coming-soon";
    }
}
