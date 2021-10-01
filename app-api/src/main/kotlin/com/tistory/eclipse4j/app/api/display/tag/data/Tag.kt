package com.tistory.eclipse4j.app.api.display.tag.data

import com.tistory.eclipse4j.domain.persist.tag.entity.TagEntity
import java.io.Serializable

data class Tag(
    val id: Long,
    val tag: String
) : Serializable {
    companion object {
        fun from(tagEntity: TagEntity): Tag = tagEntity.let {
            return Tag(id = it.id!!, tag = it.tag)
        }
    }
}
