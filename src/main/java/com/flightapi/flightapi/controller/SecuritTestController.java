package com.amadeuscasestudy.AmadeusCaseStudy.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class SecuritTestController {

    @GetMapping("/test")
    public String test(){
        return "Test";
    }

}
