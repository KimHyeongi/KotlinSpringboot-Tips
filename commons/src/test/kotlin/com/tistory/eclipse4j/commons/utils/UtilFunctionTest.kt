package com.tistory.eclipse4j.commons.utils

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class UtilFunctionTest : StringSpec() {

    init {
        "Don't just use LET for null check - is null" {
            val text: String? = null
            text.notNull {
                val resultText = doWork(it)
                resultText shouldBe "OK"
            }
        }

        "Don't just use LET for null check" {
            val text = "TEST"
            text.notNull {
                val resultText = doWork(it)
                resultText shouldBe "OK"
            }
        }
    }
}

fun doWork(text: String): String {
    println(text)
    return "OK"
}
