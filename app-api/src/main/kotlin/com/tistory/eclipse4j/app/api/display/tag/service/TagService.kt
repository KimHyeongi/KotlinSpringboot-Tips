package com.tistory.eclipse4j.app.api.display.tag.service

import com.tistory.eclipse4j.app.api.display.tag.data.Tag
import com.tistory.eclipse4j.domain.persist.tag.service.TagQueryService
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class TagService(val tagQueryService: TagQueryService) {

    @Cacheable(value = ["cached_tag_by_id"], key = "#id")
    fun findById(id: Long): Tag {
        val tagEntity = tagQueryService.findById(id)!!
        return tagEntity.let { Tag.from(it) }
    }

    fun findAllLimit(limit: Int): Page<Tag> {
        val page = tagQueryService.findAllPageable(PageRequest.of(0, limit))
        return page.map {
            Tag.from(it)
        }
    }
}
