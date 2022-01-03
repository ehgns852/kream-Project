package com.nklcb.kream.repository.querydsl;


import com.nklcb.kream.dto.QUserBoardDto;
import com.nklcb.kream.dto.UserBoardDto;
import com.nklcb.kream.dto.UserDto;
import com.nklcb.kream.entity.QBoard;
import com.nklcb.kream.entity.security.QUser;
import com.nklcb.kream.entity.security.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static com.nklcb.kream.entity.QBoard.*;
import static com.nklcb.kream.entity.security.QUser.*;


@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{


    private final JPAQueryFactory queryFactory;



    @Override
    public List<User> findAllByWithBoardV1() {

        return queryFactory
                .select(user)
                .distinct()
                .from(user)
                .innerJoin(user.boards,board)
                .fetch();

    }

    @Override
    public List<UserBoardDto> findAllByWithBoardV2() {
        return queryFactory
                .select(new QUserBoardDto(
                        user.id,
                        user.username,
                        user.password,
                        user.enabled,
                        user.email,
                        user.createDate,
                        board.id,
                        board.title,
                        board.content))
                .from(user)
                .innerJoin(user.boards,board)
                .fetch();
    }
}
