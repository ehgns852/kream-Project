package com.nklcb.kream.repository.querydsl;

import com.nklcb.kream.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {

    Page<Board> findByTitleOrContent(String title, String content, Pageable pageable);

    Page<Board> PageBoards(Pageable pageable);
}
