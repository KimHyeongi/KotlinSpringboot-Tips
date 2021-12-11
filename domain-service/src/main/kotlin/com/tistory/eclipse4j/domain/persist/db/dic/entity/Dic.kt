package com.tistory.eclipse4j.domain.persist.db.dic.entity

import com.tistory.eclipse4j.domain.persist.base.entity.AuditingEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "dic")
class Dic(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Long? = null,

    @Column(nullable = false, name = "word", length = 150)
    var word: String,

    @Column(nullable = false, name = "contents", length = 2000)
    var contents: String

) : AuditingEntity()
