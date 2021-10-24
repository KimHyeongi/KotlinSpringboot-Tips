package com.tistory.eclipse4j.commons.utils

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class JvmProductTest {
    @Test
    fun `상품 생성 - 기본 생성자로 생성`() {
        val product = JvmProduct(name = "상품명", price = 10001.2, ea = 0)
        product.name shouldBe "상품명"
        product.price shouldBe 10001.2
    }

    @Test
    fun `상품 생성 - 이름만으로 생성, price 는 기본값으로 처리 된다`() {
        val product = JvmProduct(name = "상품명")
        product.price shouldBe 0.0
    }

    @Test
    fun `발표자를 정하자`() {
        val names = listOf("현기", "중현", "문수", "순호", "성현", "제현", "병두", "경환")
        println(names.shuffled())
    }
}
