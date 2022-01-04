package com.nklcb.kream.repository;

import com.nklcb.kream.entity.Board;
import com.nklcb.kream.entity.QBoard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
@Transactional
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    JPAQueryFactory queryFactory;

    @Test
    public void findByTitleOrContent(){
        //given
        Board board = Board.createBoard("dobi", "dobi", LocalDateTime.now());
        boardRepository.save(board);

        //when
        List<Board> result = queryFactory
                .selectFrom(QBoard.board)
                .where(QBoard.board.title.eq(board.getTitle()).or(QBoard.board.content.eq(board.getContent())))
                .offset(0)
                .limit(3)
                .orderBy(QBoard.board.id.desc())
                .fetch();


        JPAQuery<Board> countQuery = queryFactory
                .selectFrom(QBoard.board)
                .where(QBoard.board.title.eq(board.getTitle()).or(QBoard.board.content.eq(board.getContent())));
        //then


    }

}