package com.nklcb.kream.entity.audit;

import com.nklcb.kream.entity.embedded.TimeEntity;

/**
 * TimeEntity
 */
public interface Auditable {

    TimeEntity getTimeEntity();

    void setTimeEntity(TimeEntity timeEntity);
}
