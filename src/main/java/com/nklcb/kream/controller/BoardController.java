package com.nklcb.kream.controller;

import com.nklcb.kream.model.Board;
import com.nklcb.kream.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardRepository boardRepository;

    @GetMapping("/list")
    public String list(Model model){
        List<Board> boards = boardRepository.findAll();

        model.addAttribute("boards",boards);

        return "board/list";
    }
}
