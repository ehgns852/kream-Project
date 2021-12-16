package com.nklcb.kream.repository;


import com.nklcb.kream.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);


}
