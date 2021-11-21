package com.tistory.eclipse4j.domain.persist.dic.service

import com.tistory.eclipse4j.domain.persist.dic.mock.MockDic
import com.tistory.eclipse4j.domain.persist.dic.repository.DicRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import mu.KotlinLogging
import org.springframework.data.repository.findByIdOrNull

internal class DicQueryServiceMockTest : StringSpec() {
    private val log = KotlinLogging.logger { }

    init {
        "정상적으로 단어 조회 - 결과 반환" {
            every { dicRepository.findByIdOrNull(any()) } returns MockDic.dic()
            val resultMockDic = sut.findById(1)
            resultMockDic.word shouldBe "Dic"
            verify(exactly = 1) { dicRepository.findByIdOrNull(any()) }
        }
    }

    private val dicRepository = mockk<DicRepository>()
    private val sut = DicFindService(dicRepository)
}
