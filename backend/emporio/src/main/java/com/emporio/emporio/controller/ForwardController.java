package com.emporio.emporio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForwardController {
    @RequestMapping("/app/**")
    public String redirect() {
        return "forward:/index.html";
    }
}