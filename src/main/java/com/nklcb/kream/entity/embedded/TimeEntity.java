package com.nklcb.kream.entity.embedded;

import com.nklcb.kream.entity.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EntityListeners;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Embeddable
public class TimeEntity{

    @Column(updatable = false)
    private LocalDateTime createDate;

    private LocalDateTime updateDate;


}