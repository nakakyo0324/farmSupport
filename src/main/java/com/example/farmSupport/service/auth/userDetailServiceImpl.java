package com.example.farmSupport.service.auth;

import com.example.farmSupport.entity.user.UserData;
import com.example.farmSupport.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class userDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username )throws UsernameNotFoundException{
        UserData user = userRepository.findByUsername(username).orElseThrow(() ->{
            System.out.println("UserNotFound");
            return new UsernameNotFoundException("ユーザが見つかりませんでした");});
        System.out.println("--- ログイン試行 ---");
        System.out.println("入力された名前: " + username);
        System.out.println("DBから取得した名前: " + user.getUsername());
        System.out.println("DBから取得したパスワード: " + user.getPassword());
        System.out.println("DBから取得した権限: " + user.getUserRole());
        return User.builder().username(user.getUsername()).password(user.getPassword()).roles(user.getUserRole()).build();
    }
}
