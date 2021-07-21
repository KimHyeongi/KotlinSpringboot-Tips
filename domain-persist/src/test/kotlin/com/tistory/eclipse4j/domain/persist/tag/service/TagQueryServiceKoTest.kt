package com.tistory.eclipse4j.domain.persist.tag.service

import com.tistory.eclipse4j.domain.persist.DomainTestAppliation
import io.kotest.core.annotation.Ignored
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest

@Ignored
@SpringBootTest(classes = [DomainTestAppliation::class])
class TagQueryServiceKoTest(val service: TagQueryService) : StringSpec() {

    override fun extensions() = listOf(SpringExtension)

    init {
        "ID로 정보가져오기" {
            service.findById(1)
        }
        "Limit 만큼 가져오기 : Page " {
            service.findAllPageable(PageRequest.of(0, 10))
        }
    }
}
