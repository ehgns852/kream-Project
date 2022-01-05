package com.nklcb.kream.repository.querydsl;

import com.nklcb.kream.dto.BoardDto;
import com.nklcb.kream.dto.QUserBoardDto;
import com.nklcb.kream.dto.UserBoardDto;
import com.nklcb.kream.entity.Board;
import com.nklcb.kream.entity.security.QUser;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.nklcb.kream.entity.QBoard.*;
import static com.nklcb.kream.entity.security.QUser.*;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@Slf4j
public class BoardRepositoryImpl implements BoardRepositoryCustom{


    private final JPAQueryFactory queryFactory;


    @Override
    public Page<UserBoardDto> findByListAll(String title, String content, Pageable pageable) {
        List<UserBoardDto> result = queryFactory
                .select(new QUserBoardDto(
                        user.id,
                        user.username,
                        board.id,
                        board.title,
                        board.content))
                .from(board)
                .leftJoin(board.user, user)
                .where(board.title.contains(title).or(board.content.contains(content)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(board.id.desc())
                .fetch();

        long countQuery = queryFactory
                .select(new QUserBoardDto(
                        user.id,
                        user.username,
                        board.id,
                        board.title,
                        board.content))
                .from(board)
                .leftJoin(board.user, user)
                .where(board.title.contains(title).or(board.content.contains(content)))
                .fetchCount();

        log.info("countQuery = {}", countQuery);

        return PageableExecutionUtils.getPage(result, pageable, () -> countQuery);

    }


    /**
     * 전체 조회
     */
    @Override
    public Page<Board> PageBoards(Pageable pageable) {
        List<Board> result = queryFactory
                .selectFrom(board)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long countQuery = queryFactory
                .selectFrom(board)
                .fetchCount();



        return PageableExecutionUtils.getPage(result, pageable, () -> countQuery);

    }


    /**
     * 사용자가 작성한 글
     */
    @Override
    public Page<UserBoardDto> findUserAndBoardList(Long id, Pageable pageable) {

        List<UserBoardDto> result = queryFactory
                .select(new QUserBoardDto(
                        user.id,
                        user.username,
                        board.id,
                        board.title,
                        board.content))
                .from(board)
                .innerJoin(board.user, user)
                .where(user.id.eq(id))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long countQuery = queryFactory
                .selectFrom(board)
                .innerJoin(board.user, user)
                .where(user.id.eq(id))
                .fetchCount();

        return PageableExecutionUtils.getPage(result,pageable,() -> countQuery);
    }



//    private BooleanExpression titleEq(String title) {
//        return hasText(title) ? board.title.like(title) : null;
//    }
//
//    private BooleanExpression contentEq(String content){
//        return hasText(content) ? board.content.like(content) : null;
//    }
}
