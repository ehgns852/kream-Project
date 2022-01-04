package com.nklcb.kream.repository.querydsl;

import com.nklcb.kream.entity.Board;
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
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@Slf4j
public class BoardRepositoryImpl implements BoardRepositoryCustom{


    private final JPAQueryFactory queryFactory;


    /**
     * 검색조건 동적쿼리
     */
    @Override
    public Page<Board> findByTitleOrContent(String title, String content, Pageable pageable) {
        /**
         * content query
         */
        List<Board> result = queryFactory
                .selectFrom(board)
                .where(board.title.contains(title).or(board.content.contains(content)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(board.id.desc())
                .fetch();


        /**
         * count query
         */
        long countQuery = queryFactory
                .selectFrom(board)
                .where(board.title.contains(title).or(board.content.contains(content)))
                .fetchCount();


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

//    private BooleanExpression titleEq(String title) {
//        return hasText(title) ? board.title.like(title) : null;
//    }
//
//    private BooleanExpression contentEq(String content){
//        return hasText(content) ? board.content.like(content) : null;
//    }
}
