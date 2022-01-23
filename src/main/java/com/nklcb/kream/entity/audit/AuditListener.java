package com.nklcb.kream.entity.audit;

import com.nklcb.kream.entity.embedded.TimeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import javax.persistence.EntityListeners;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;


/**
 * TimeEntity PrePersist, PreUpdate
 */
@Slf4j
@Configuration
public class AuditListener {

    @PrePersist
    public void setCreateDate(Auditable auditable) {
        TimeEntity timeEntity = auditable.getTimeEntity();

        if(timeEntity == null) {
            timeEntity = new TimeEntity();
            auditable.setTimeEntity(timeEntity);
        }

        timeEntity.setCreateDate(LocalDateTime.now());
    }


    @PreUpdate
    public void setUpdateDate(Auditable auditable) {
        TimeEntity timeEntity = auditable.getTimeEntity();

        timeEntity.setUpdateDate(LocalDateTime.now());
    }




}