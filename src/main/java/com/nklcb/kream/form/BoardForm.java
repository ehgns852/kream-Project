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
    @Size(min = 2, max = 20)
    private String title;

    private String content;

    public BoardForm() {
    }

    public BoardForm(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
