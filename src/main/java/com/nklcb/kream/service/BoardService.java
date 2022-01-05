package com.nklcb.kream.service;

import com.nklcb.kream.dto.BoardDto;
import com.nklcb.kream.dto.UserBoardDto;
import com.nklcb.kream.entity.Board;
import com.nklcb.kream.entity.security.User;
import com.nklcb.kream.repository.BoardRepository;
import com.nklcb.kream.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BoardService {

    private  final BoardRepository boardRepository;

    private final UserRepository userRepository;


    public List<Board> findAll(){
        return boardRepository.findAll();
    }

    public Optional<Board> findById(Long id){
        return boardRepository.findById(id);
    }


    /**
     * board + user 연관관계 매핑
     */
    @Transactional
    public Board save(String username, Board board) {

        User findUsername = userRepository.findByUsername(username);

        board.addUser(findUsername);

        return boardRepository.save(board);

    }


    @Transactional
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }


    /**
     * Board 전체조회 Paging
     */
    public Page<Board> PageBoards(Pageable pageable) {
        return boardRepository.PageBoards(pageable);
    }



    public Page<UserBoardDto> findUserAndBoardList(Long userId, Pageable pageable) {
        return boardRepository.findUserAndBoardList(userId, pageable);
    }

    public Page<UserBoardDto> findAllList(String title, String content, Pageable pageable) {
        return boardRepository.findByListAll(title, content, pageable);
    }
}
