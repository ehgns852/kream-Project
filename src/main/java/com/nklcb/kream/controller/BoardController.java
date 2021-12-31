package com.nklcb.kream.controller;

import com.nklcb.kream.entity.Board;
import com.nklcb.kream.entity.security.User;
import com.nklcb.kream.form.BoardForm;
import com.nklcb.kream.repository.BoardRepository;
import com.nklcb.kream.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {

    private final BoardRepository boardRepository;

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(page = 0, size = 10) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText,
                       Authentication authentication) {
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText,pageable);
        String username = AuthenticationName(authentication);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        model.addAttribute("username", username);

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
    public String postForm(@Valid @ModelAttribute(name = "board") BoardForm boardForm, BindingResult bindingResult, Authentication authentication){
        if (bindingResult.hasErrors()) {
            log.info("write has errors!");

            return "board/form";
        }


        String username = AuthenticationName(authentication);

        Board board = Board.createBoard(boardForm.getTitle(), boardForm.getContent(), LocalDateTime.now());

        boardService.save(username,board);

//        boardRepository.save(board);

        return "redirect:/board/list";

    }

    /**
     *  User의 권한
     */
    private String AuthenticationName(Authentication authentication) {
        return authentication.getName();
    }
}
