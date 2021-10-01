package com.tistory.eclipse4j.commons.utils

fun <T : Any> T?.notNull(f: (it: T) -> Unit) {
    if (this != null) f(this)
}
