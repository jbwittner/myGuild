package fr.opendoha.myguild.server.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller used to redirect all http call to "/"
 */
@Controller
public class RoutesController implements ErrorController{

    private static final String PATH = "/error";

    /**
     * Method used to redirect all http call to "/"
     */
    @RequestMapping(value = PATH)
    public String error(){
        return "redirect:/";
    }

    /**
     * Method used to redirect all http call to "/"
     */
    @Override
    public String getErrorPath() {
        return "redirect:/";
    }
    
}