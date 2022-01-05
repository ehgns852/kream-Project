package com.nklcb.kream.repository.querydsl;

import com.nklcb.kream.dto.BoardDto;
import com.nklcb.kream.dto.UserBoardDto;
import com.nklcb.kream.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {
    //내 게시글 조회
    Page<UserBoardDto> findMyBoardList(Long id, Pageable pageable);

    //전체 게시글 조회
    Page<UserBoardDto> findByListAll(String title, String content, Pageable pageable);

    //전체 게시글 조회 API
    Page<UserBoardDto> findAllBoardApi(Pageable pageable);
}
