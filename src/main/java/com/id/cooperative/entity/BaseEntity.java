package com.id.cooperative.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.OffsetDateTime;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity {

    @Column(name = "created_date")
    private OffsetDateTime createdDate;

    @Column(name = "modified_date")
    private OffsetDateTime modifiedDate;

    @PrePersist
    private void prePersist() {
        this.createdDate = OffsetDateTime.now();
        this.modifiedDate = OffsetDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        this.modifiedDate = OffsetDateTime.now();
    }

}
