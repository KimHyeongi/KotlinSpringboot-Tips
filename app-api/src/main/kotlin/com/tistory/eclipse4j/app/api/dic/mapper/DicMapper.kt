package com.tistory.eclipse4j.app.api.dic.mapper

import com.tistory.eclipse4j.app.api.dic.data.DicResponse
import com.tistory.eclipse4j.domain.persist.dic.entity.Dic

object DicMapper {
    fun toResponse(dic: Dic): DicResponse = dic.let {
        return DicResponse(id = it.id!!, contents = it.contents)
    }
}
