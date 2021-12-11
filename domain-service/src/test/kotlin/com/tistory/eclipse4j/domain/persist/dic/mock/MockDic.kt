package com.tistory.eclipse4j.domain.persist.dic.mock

import com.tistory.eclipse4j.domain.persist.db.dic.entity.Dic

internal object MockDic {

    fun dic(
        id: Long = 1,
        word: String = "Dic",
        contents: String = "Contents"
    ) = Dic(
        id = id, word = word, contents = contents
    )
}
