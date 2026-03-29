package com.example.farmSupport.repository;


import com.example.farmSupport.entity.user.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByUsername(String username);

    @Query("SELECT COUNT(u.email) FROM UserData u WHERE u.email LIKE :email")
    long countByEmailSearch(@Param("email") String email);
}
