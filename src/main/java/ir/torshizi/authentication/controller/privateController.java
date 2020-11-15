package ir.torshizi.authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private/api")
public class privateController {

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello Private World.";
    }
}
