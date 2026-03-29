package com.example.farmSupport.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Entity
@Table(name="USERS", schema="FARMSUPPORT")
public class UserData {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Getter
    @Column(name="email")
    private String email;

    @Getter
    @Column(name="user_name")
    private String username;

    @Getter
    @Column(name="user_role")
    private String userRole;

    @Column(name="pass_hash")
    private String password;


    public String getPassword() {
        return password;
    }

}
