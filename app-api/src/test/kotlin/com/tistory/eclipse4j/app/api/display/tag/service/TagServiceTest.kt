package com.tistory.eclipse4j.app.api.display.tag.service

import com.tistory.eclipse4j.domain.persist.tag.entity.TagEntity
import com.tistory.eclipse4j.domain.persist.tag.service.TagQueryService
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class TagServiceTest {

    @InjectMockKs
    lateinit var tagService: TagService
    @MockK
    lateinit var tagQueryService: TagQueryService

    @Test
    fun `Tag Id로 가져오기`() {
        val mockTag = TagEntity(id = 10, tag = "")
        every { tagQueryService.findById(any()) } returns mockTag
        val tag = tagService.findById(1)
        tag.id shouldBe 10
    }
}
