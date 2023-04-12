package com.kuzin.integration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {
    @GetMapping("/home")
    public String getGreetings() {
        return "name";
    }

    @GetMapping("/hi")
    public String getHi() {
        return "Hi";
    }
}
