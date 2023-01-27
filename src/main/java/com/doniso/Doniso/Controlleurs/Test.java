package com.doniso.Doniso.Controlleurs;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* In TestController.java */
// @CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/test")
public class Test {

    @GetMapping("/salutation")
    public String saluer(){
        return "Bonjour Djiguiba ";
    }
}
