package com.nklcb.kream.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BoardForm {



    private Long id;

    @NotNull
    @Size(min = 2, max = 20, message = "제목은 2자이상 30자 이하입니다.")
    private String title;

    @NotNull
    @Size(min = 1, max = 500)
    private String content;


    public BoardForm() {
    }

    public BoardForm(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
