package com.nklcb.kream.repository.querydsl;

import com.nklcb.kream.entity.security.User;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> findAllByWithBoard();
}
