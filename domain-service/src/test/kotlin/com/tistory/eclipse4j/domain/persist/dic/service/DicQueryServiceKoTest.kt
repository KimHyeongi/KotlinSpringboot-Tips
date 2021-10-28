package com.tistory.eclipse4j.domain.persist.dic.service

import com.ninjasquad.springmockk.MockkBean
import com.tistory.eclipse4j.domain.persist.DomainTestAppliation
import com.tistory.eclipse4j.domain.persist.dic.mock.MockDic
import com.tistory.eclipse4j.domain.persist.dic.repository.DicRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.verify
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest(classes = [DomainTestAppliation::class])
internal class DicQueryServiceKoTest(val sut: DicFindService) : StringSpec() {

    @MockkBean
    lateinit var dicRepository: DicRepository

    init {
        "ID로 정보가져오기 Null 의 경우 IllegalStateException" {
            every { dicRepository.findByIdOrNull(any()) } returns null
            shouldThrow<IllegalStateException> {
                sut.findById(1)
                verify(exactly = 1) { dicRepository.findByIdOrNull(any()) }
            }
        }

        "ID로 정보가져오기" {
            val mockDic = MockDic.dic()
            every { dicRepository.findByIdOrNull(any()) } returns mockDic
            val result = sut.findById(1)
            verify(exactly = 1) { dicRepository.findByIdOrNull(any()) }
            result.word shouldBe mockDic.word
        }
    }
}
