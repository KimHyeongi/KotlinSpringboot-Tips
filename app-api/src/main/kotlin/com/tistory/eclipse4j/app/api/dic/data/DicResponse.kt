package com.tistory.eclipse4j.app.api.dic.data

import com.tistory.eclipse4j.domain.persist.db.dic.entity.Dic
import java.io.Serializable

data class DicResponse(
    val id: Long,
    val contents: String
) : Serializable {
    companion object {
        fun from(dic: Dic): DicResponse = dic.let {
            return DicResponse(id = it.id!!, contents = it.contents)
        }
    }
}
