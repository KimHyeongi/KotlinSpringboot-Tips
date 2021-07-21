package com.tistory.eclipse4j.app.api.display.tag.service

import com.ninjasquad.springmockk.MockkBean
import com.tistory.eclipse4j.app.api.AppApiApplication
import com.tistory.eclipse4j.domain.persist.tag.entity.TagEntity
import com.tistory.eclipse4j.domain.persist.tag.service.TagQueryService
import io.kotest.core.annotation.Ignored
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.every
import org.springframework.boot.test.context.SpringBootTest

@Ignored
@SpringBootTest(classes = [AppApiApplication::class])
class TagServiceKoTest(val tagService: TagService) : StringSpec() {
    override fun extensions() = listOf(SpringExtension)

    @MockkBean
    lateinit var tagQueryService: TagQueryService

    init {
        "Tag Id로 가져오기" {
            val mockTag = TagEntity(id = 10, tag = "")
            every { tagQueryService.findById(any()) } returns mockTag
            val tag = tagService.findById(1)
            tag.id shouldBe 10
        }
    }
}
