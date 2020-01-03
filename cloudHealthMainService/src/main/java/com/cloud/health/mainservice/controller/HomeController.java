package com.cloud.health.mainservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Tuesday
 * Date: 12/31/2019
 * Time: 7:07 PM
 * Project: cloudHealthMainService
 */

@RestController
public class HomeController {

    @RequestMapping("/")
    public String homePage(){
        return "Welcome to Main Service";
    }
}
