package com.nklcb.kream;


import com.nklcb.kream.model.Board;
import com.nklcb.kream.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitTestData {

    private final BoardRepository boardRepository;



    @PostConstruct
    public void saveBoard(){
        Board board = new Board("hello World", "Test Data");
        boardRepository.save(board);
        Board board2 = new Board("hello ", "Test Data2");
        boardRepository.save(board2);
    }
}
