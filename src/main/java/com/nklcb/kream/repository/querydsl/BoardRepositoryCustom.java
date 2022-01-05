package com.nklcb.kream.repository.querydsl;

import com.nklcb.kream.dto.BoardDto;
import com.nklcb.kream.dto.UserBoardDto;
import com.nklcb.kream.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {


    Page<Board> PageBoards(Pageable pageable);

    Page<UserBoardDto> findUserAndBoardList(Long id, Pageable pageable);

    Page<UserBoardDto> findByListAll(String title, String content, Pageable pageable);
}
