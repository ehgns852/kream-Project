package com.nklcb.kream.entity;

import com.nklcb.kream.entity.audit.AuditListener;
import com.nklcb.kream.entity.audit.Auditable;
import com.nklcb.kream.entity.embedded.TimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@ToString(of = {"id, title, content, createDate"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditListener.class)
public class Board implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;


    private String title;
    private String content;

    @Embedded
    private TimeEntity timeEntity;



    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    protected Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static Board createBoard(String title, String content) {
        Board board = new Board(title, content);

        return board;
    }


    /**
     * 연관관계 편의 메서드
     */
    public void addUser(User user) {
        this.user = user;
        user.getBoards().add(this);
    }


    /**
     *
     * @PrePersist TimeEntity
     */
    @Override
    public void setTimeEntity(TimeEntity timeEntity) {
        this.timeEntity = timeEntity;
    }
}
