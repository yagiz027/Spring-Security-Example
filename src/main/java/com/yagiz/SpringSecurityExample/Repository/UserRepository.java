package com.yagiz.SpringSecurityExample.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yagiz.SpringSecurityExample.Entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    public Optional<User> findByUserEmail(String username);
    public boolean existsByUsername(String username); 
    public boolean existsByUserEmail(String userEmail);
}
