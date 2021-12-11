package com.tistory.eclipse4j.domain.study

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import mu.KotlinLogging
import java.lang.IllegalStateException

internal class PreconditionsTest : StringSpec() {
    private val log = KotlinLogging.logger { }
    init {
        "NULL 을 넣어 IllegalStateException 예외를 내보자 " {
            val nullTxt: String? = null
            val e = shouldThrow<IllegalStateException> {
                val result = checkNotNull(nullTxt) { "Null 이에요" }
                log.debug { result }
            }
            e.message shouldBe "Null 이에요"
            e shouldBe beInstanceOf<IllegalStateException>()
        }

        "NULL이 아닌 경우 정상 값 반환 " {
            val txt: String? = "TEST"
            val result = checkNotNull(txt) { "Null 이에요" }

            result shouldBe "TEST"
        }
    }
}
