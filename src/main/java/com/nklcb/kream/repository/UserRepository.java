package com.nklcb.kream.repository;

import com.nklcb.kream.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);
}
