package com.arilsondev.publictaxmanagement.domains;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@Data
@MappedSuperclass
public abstract class AuditDomain {

    @UpdateTimestamp
    @Column(name = "dt_updated", insertable = false)
    protected ZonedDateTime updateTime;

    @CreationTimestamp
    @Column(name = "dt_created", updatable = false, columnDefinition = "DATETIME(3) DEFAULT NOW(3)")
    protected ZonedDateTime createdTime;
}
