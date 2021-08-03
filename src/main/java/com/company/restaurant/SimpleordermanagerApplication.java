package com.company.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class SimpleordermanagerApplication {
    @RequestMapping("/")
    public String home() {
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleordermanagerApplication.class, args);
    }

}
