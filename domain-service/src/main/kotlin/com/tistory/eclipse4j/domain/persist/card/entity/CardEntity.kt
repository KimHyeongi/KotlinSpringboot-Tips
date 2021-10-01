package com.tistory.eclipse4j.domain.persist.card.entity

import com.tistory.eclipse4j.domain.persist.base.entity.AuditingEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "card")
class CardEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Long? = null,

    @Column(nullable = false, name = "title", length = 250)
    val title: String

) : AuditingEntity()
