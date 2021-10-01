package com.tistory.eclipse4j.domain.persist.base.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@EntityListeners(value = [AuditingEntityListener::class])
@MappedSuperclass
abstract class AuditingEntity {

    @Column(name = "deleted", nullable = false, columnDefinition = "tinyint(1) comment '삭제여부'")
    var deleted: Boolean? = false

    @CreatedDate
    @Column(nullable = true, columnDefinition = "datetime(6) comment '생성일자'")
    var createdAt: LocalDateTime? = null
        protected set

    @LastModifiedDate
    @Column(nullable = true, columnDefinition = "datetime(6) comment '수정일자'")
    var modifiedAt: LocalDateTime? = null
        protected set

    @CreatedBy
    @Column(nullable = true, columnDefinition = "varchar(64) comment '생성자'")
    var createdBy: String? = null
        protected set

    @LastModifiedBy
    @Column(nullable = true, columnDefinition = "varchar(64) comment '수정자'")
    var modifiedBy: String? = null
        protected set
}
