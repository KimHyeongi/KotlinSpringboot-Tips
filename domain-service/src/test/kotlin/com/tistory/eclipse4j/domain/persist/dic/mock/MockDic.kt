package com.tistory.eclipse4j.domain.persist.dic.mock

import com.tistory.eclipse4j.domain.persist.db.dic.entity.DicEntity

internal object MockDic {

    fun dic(
        id: Long = 1,
        word: String = "Dic",
        contents: String = "Contents"
    ) = DicEntity(
        id = id, word = word, contents = contents
    )
}
