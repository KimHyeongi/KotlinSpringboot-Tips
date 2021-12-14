package com.tistory.eclipse4j.domain.persist.db.dic.entity

import com.tistory.eclipse4j.domain.persist.base.entity.AuditingEntity
import org.hibernate.annotations.Comment
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table

@Entity
@Table(
    name = "dic",
    indexes = [
        Index(name = "idx_dic_word", columnList = "word", unique = true)
        // ,Index(name = "idx_dic_multiple_columns", columnList = "word, xxxx")
    ]
)
@org.hibernate.annotations.Table(appliesTo = "dic", comment = "사전")
class DicEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Long? = null,

    @Column(nullable = false, name = "word", length = 150)
    @Comment("단어")
    var word: String,

    @Column(nullable = false, name = "contents", length = 2000)
    @Comment("설명")
    var contents: String

) : AuditingEntity()
