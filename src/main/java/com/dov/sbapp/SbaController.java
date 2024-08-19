package com.dov.sbapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SbaController {

    @GetMapping("/")
    public String index(){
        return "Greetings from Sba Application!";
    }

}
