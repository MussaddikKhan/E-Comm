package com.clg.project.semVI.Ecommerce.Project.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {
    @GetMapping("/")
    public  String test(){
        return "Java Ecommerse Backend";
    }
}
