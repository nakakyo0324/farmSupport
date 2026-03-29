package com.example.farmSupport.controller.auth;

import com.example.farmSupport.form.SignupForm;
import com.example.farmSupport.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String signupForm(Model model){
        model.addAttribute("signupForm",new SignupForm());
        return "auth/signup";}

    @PostMapping("/signup")
    public String signupStart(@Validated @ModelAttribute SignupForm signupForm, BindingResult result){
        if(result.hasErrors()){
            return "auth/signup";
        }
        try{
            userService.registerUser(signupForm.getUsername(),signupForm.getPassword1(),signupForm.getPassword2(),signupForm.getEmail(),"User");
        }catch(Exception e){
            result.rejectValue("username","error.user",e.getLocalizedMessage());
            return "auth/signup";
        }

        return "auth/loginForm";
    }
}
