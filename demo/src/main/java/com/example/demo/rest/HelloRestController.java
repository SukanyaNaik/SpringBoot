package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {
    @Value("${course.name}")
    private String courseName;

    @GetMapping("/")
    public String getGreeting() {
        return "Hello World!!!";
    }

    @GetMapping("/courseName")
    public String getProjectName() {
        return courseName;
    }
}
