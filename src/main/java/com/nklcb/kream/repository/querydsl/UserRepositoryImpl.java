package com.nklcb.kream.repository.querydsl;


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
    public List<User> findAllByWithBoard() {

        return queryFactory
                .selectFrom(user)
                .innerJoin(user.boards, board)
                .fetch();


    }
}
