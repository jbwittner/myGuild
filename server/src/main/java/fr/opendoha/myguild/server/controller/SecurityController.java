package fr.opendoha.myguild.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("security")
public class SecurityController extends MotherController {

    /**
     * Endpoint used check if the user are connected
     */
    @GetMapping("/connectionTest")
    public void getAccountInfo() {};

}
