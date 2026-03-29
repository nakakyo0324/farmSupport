package com.example.farmSupport.service.auth;

import com.example.farmSupport.entity.user.UserData;
import com.example.farmSupport.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public void registerUser(String username,String password1,String password2,String email,String role) throws Exception {
        UserData userData = new UserData();
        userData.setUsername(username);
        userData.setEmail(email);
        userData.setPassword(password1);
        userData.setUserRole(role);
        if(!password1.equals(password2)){
            throw new Exception("パスワードが異なります");
        }
        if(userRepository.countByEmailSearch(email) > 0){
            throw new Exception("このメールアドレスは登録されています");
        }
        try{
            userRepository.save(userData);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("すでにこのユーザ名は使用されています");
        }
    }

}
