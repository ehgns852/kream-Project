package com.nklcb.kream.service;

import com.nklcb.kream.entity.Board;
import com.nklcb.kream.entity.security.User;
import com.nklcb.kream.repository.BoardRepository;
import com.nklcb.kream.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private  final BoardRepository boardRepository;

    private final UserRepository userRepository;


    /**
     * board + user 연관관계 매핑
     */
    public Board save(String username, Board board) {

        User findUsername = userRepository.findByUsername(username);

        board.addUser(findUsername);

        return boardRepository.save(board);

    }


    public void delete(Board board) {
        boardRepository.delete(board);
    }
}
