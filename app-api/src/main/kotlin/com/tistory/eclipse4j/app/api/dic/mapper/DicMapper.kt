package com.tistory.eclipse4j.app.api.dic.mapper

import com.tistory.eclipse4j.app.api.dic.data.DicResponse
import com.tistory.eclipse4j.domain.persist.db.dic.entity.DicEntity

object DicMapper {
    fun toResponse(dic: DicEntity): DicResponse = dic.let {
        return DicResponse(id = it.id!!, contents = it.contents)
    }
}
