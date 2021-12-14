package com.nklcb.kream.controller;

import com.nklcb.kream.form.BoardForm;
import com.nklcb.kream.model.Board;
import com.nklcb.kream.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardRepository boardRepository;

    @GetMapping("/list")
    public String list(Model model) {
        List<Board> boards = boardRepository.findAll();

        model.addAttribute("boards", boards);

        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        if (id == null) {
            BoardForm boardForm = new BoardForm();
            model.addAttribute("board",boardForm);
        } else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board",board);
        }
        return "board/form";
    }

    @PostMapping("/form")
    public String write(BoardForm boardForm){
        Board board = new Board(boardForm.getTitle(), boardForm.getContent());

        System.out.println("board = " + board);
        boardRepository.save(board);
        System.out.println("board.save = " + board);


        return "redirect:/board/list";

    }
}
