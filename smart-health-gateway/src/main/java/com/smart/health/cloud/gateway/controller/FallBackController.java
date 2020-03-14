package com.smart.health.cloud.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kibe Joseph Wambugu
 * User: Joseph
 * Day: Saturday
 * Date: 3/14/2020
 * Time: 12:52 PM
 * Project: smart-health-gateway
 */

@RestController
@RequestMapping(value = "/fallback")
public class FallBackController {

    @GetMapping("/message")
    public String getMedicalRecordsMainServiceFallbackMessage(){
        return "Smart Medical Record Service is Not available. Please try again  latter";
    }
}
