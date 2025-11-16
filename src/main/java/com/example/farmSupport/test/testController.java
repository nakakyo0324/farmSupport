package com.example.farmSupport.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class testController {
    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "<h3>Hello World</h3>";
    }
}
