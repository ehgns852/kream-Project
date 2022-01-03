package com.nklcb.kream.repository.querydsl;

import com.nklcb.kream.dto.UserBoardDto;
import com.nklcb.kream.dto.UserDto;
import com.nklcb.kream.entity.security.User;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> findAllByWithBoardV1();

    List<UserBoardDto> findAllByWithBoardV2();
}
