package com.nklcb.kream.entity.audit;

import com.nklcb.kream.entity.embedded.TimeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;


/**
 * TimeEntity PrePersist, PreUpdate
 */
@Slf4j
@Configuration
public class AuditListener {

    /**
     * Persist 전에 실행
     */
    @PrePersist
    public void setCreateDate(Auditable auditable) {
        TimeEntity timeEntity = auditable.getTimeEntity();

        if(timeEntity == null) {
            timeEntity = new TimeEntity();
            auditable.setTimeEntity(timeEntity);
        }

        timeEntity.setCreateDate(LocalDateTime.now());
    }


    /**
     * Update 전에 실행
     */
    @PreUpdate
    public void setUpdateDate(Auditable auditable) {
        TimeEntity timeEntity = auditable.getTimeEntity();

        timeEntity.setUpdateDate(LocalDateTime.now());
    }




}