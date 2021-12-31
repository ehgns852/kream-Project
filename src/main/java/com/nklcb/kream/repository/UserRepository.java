package com.nklcb.kream.repository;

import com.nklcb.kream.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

     User findByUsername(String username);

    @Query("select u from User u inner join fetch u.boards b")
    List<User> findAllByWithBoard();


}
