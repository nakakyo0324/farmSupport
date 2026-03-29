package com.example.farmSupport.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String loginForm(){
        return "auth/loginForm";
    }

    @GetMapping(value = "/login",params = "failure")
    public String loginFail(Model model) {
        model.addAttribute("failureMessage", "ログインに失敗しました");
        return "auth/loginForm";
    }

    @RequestMapping("/access-denied")
    public String accessDenied(){
        return "auth/denied";
    }


}
