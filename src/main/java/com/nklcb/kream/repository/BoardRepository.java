package com.nklcb.kream.repository;


import com.nklcb.kream.entity.Board;
import com.nklcb.kream.repository.querydsl.BoardRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {


}
