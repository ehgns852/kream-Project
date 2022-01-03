package com.nklcb.kream.repository;

import com.nklcb.kream.entity.security.User;
import com.nklcb.kream.repository.querydsl.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

     User findByUsername(String username);

}
