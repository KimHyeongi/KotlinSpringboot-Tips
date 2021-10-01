package com.tistory.eclipse4j.domain.persist.dic.service

import com.ninjasquad.springmockk.MockkBean
import com.tistory.eclipse4j.domain.persist.DomainTestAppliation
import com.tistory.eclipse4j.domain.persist.dic.repository.DicRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [DomainTestAppliation::class])
class DicQueryServiceKoTest(val sut: DicFindService) : StringSpec() {

    override fun extensions() = listOf(SpringExtension)

    @MockkBean
    lateinit var dicRepository: DicRepository

    init {
        "ID로 정보가져오기" {
        }
        "Limit 만큼 가져오기 : Page " {
        }
    }
}
