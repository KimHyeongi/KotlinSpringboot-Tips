package com.tistory.eclipse4j.app.api.dic.data

import java.io.Serializable

data class DicResponse(
    val id: Long,
    val contents: String
) : Serializable
