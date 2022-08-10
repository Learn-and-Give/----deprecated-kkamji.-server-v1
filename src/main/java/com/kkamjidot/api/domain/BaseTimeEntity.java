package com.kkamjidot.api.domain;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    @Column(name = "created_date", nullable = false, updatable = false, insertable = false, columnDefinition = "timestamp")
    @ColumnDefault("CURRENT_TIMESTAMP")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date", columnDefinition = "timestamp null on update CURRENT_TIMESTAMP")
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}

/*
    @Column(name = "created_date", nullable = false, updatable = false, insertable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP not null")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Instant createdDate;

    @Column(name = "modified_date", columnDefinition = "timestamp null on update CURRENT_TIMESTAMP")
    private Instant modifiedDate;
 */
