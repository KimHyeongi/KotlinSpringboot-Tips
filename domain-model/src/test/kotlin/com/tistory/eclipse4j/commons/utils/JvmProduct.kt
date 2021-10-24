package com.tistory.eclipse4j.commons.utils

data class JvmProduct @JvmOverloads constructor(
    val name: String,
    val price: Double = 0.0,
    val ea: Int = 0
)
