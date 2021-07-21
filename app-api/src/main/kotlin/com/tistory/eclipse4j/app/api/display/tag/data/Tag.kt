package com.tistory.eclipse4j.app.api.display.tag.data

import com.tistory.eclipse4j.domain.persist.tag.entity.TagEntity
import com.tistory.eclipse4j.domain.rest.api.doc.annotation.RestDocClass
import com.tistory.eclipse4j.domain.rest.api.doc.annotation.RestDocProperty
import java.io.Serializable

@RestDocClass
data class Tag(
    @field:RestDocProperty("Tag PK") val id: Long,
    @field:RestDocProperty("Tag") val tag: String
) : Serializable {
    companion object {
        fun from(tagEntity: TagEntity): Tag = tagEntity.let {
            return Tag(id = it.id!!, tag = it.tag)
        }
    }
}
