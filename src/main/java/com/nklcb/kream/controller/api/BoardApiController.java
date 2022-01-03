package com.nklcb.kream.controller.api;

import com.nklcb.kream.entity.Board;
import com.nklcb.kream.repository.BoardRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
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


    /**
     * ADMIN 권한만 삭제 가능
     */
    @Secured("ROLE_ADMIN")
    @DeleteMapping("boards/{id}")
    void deleteBoard(@PathVariable Long id) {
        boardRepository.deleteById(id);
    }


    @AllArgsConstructor
    @Getter
    static class Result<T>{
        private T data;


    }


}
