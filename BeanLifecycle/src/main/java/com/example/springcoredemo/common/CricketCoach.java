package com.example.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component

public class CricketCoach implements Coach {
    public CricketCoach() {
        System.out.println("In Constructor: " + getClass().getSimpleName());
    }

    @PostConstruct
    public void doCustomInit() {
        System.out.println("In doCustomInit");
    }

    @PreDestroy
    public void doCleanUp() {
        System.out.println("In doCleanUp");
    }
    @Override
    public String getDailyWorkout() {
        return "Practice bowling!";
    }
}
