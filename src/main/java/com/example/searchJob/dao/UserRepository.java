package com.example.asm2.dao;

import com.example.asm2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    User findByVerificationCode(String code);

}
