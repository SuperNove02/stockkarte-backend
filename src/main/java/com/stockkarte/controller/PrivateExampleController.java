package com.stockkarte.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Secured("ROLE_USER")
public class PrivateExampleController {
    @RequestMapping("/private")
    public String index () {
        return "Private hello World!";
    }
}
