package com.tistory.eclipse4j.domain.persist.dic.mock

import com.tistory.eclipse4j.domain.persist.dic.entity.Dic

object MockDic {

    fun dic(
        id: Long = 1,
        word: String = "Dic",
        contents: String = "Contents"
    ) = Dic(
        id = id, word = word, contents = contents
    )
}