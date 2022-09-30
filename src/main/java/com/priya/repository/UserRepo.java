package com.priya.repository;

import com.priya.entity.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    /*Optional<User> findByUserName(String userName);
    Optional<User> findByUserNameOrEmail(String userName,String email);
    Optional<User> findByEmail(String email);
    Boolean existByUserName(String userName);
    Boolean existByEmail(String email);*/
}
