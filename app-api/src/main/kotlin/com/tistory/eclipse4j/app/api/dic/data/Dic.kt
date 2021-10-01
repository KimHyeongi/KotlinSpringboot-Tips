package com.tistory.eclipse4j.app.api.dic.data

import com.tistory.eclipse4j.domain.persist.dic.entity.DicEntity
import java.io.Serializable

data class Dic(
    val id: Long,
    val contents: String
) : Serializable {
    companion object {
        fun from(dic: DicEntity): Dic = dic.let {
            return Dic(id = it.id!!, contents = it.contents)
        }
    }
}
