package com.nklcb.kream.controller;

import com.nklcb.kream.entity.Board;
import com.nklcb.kream.form.BoardForm;
import com.nklcb.kream.repository.BoardRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardApiController {

    private final BoardRepository boardRepository;


    @GetMapping("/boards")
    public Result all() {
        List<Board> all = boardRepository.findAll();
        return new Result(all);
    }

    @PostMapping("/boards")
    public Board newBoard(@RequestBody Board newBoard) {
        return boardRepository.save(newBoard);
    }

    @GetMapping("boards/{id}")
    public Board one(@PathVariable Long id) {
        return boardRepository.findById(id).orElse(null);
    }


    @AllArgsConstructor
    @Getter
    static class Result<T>{
        private T data;


    }


}
