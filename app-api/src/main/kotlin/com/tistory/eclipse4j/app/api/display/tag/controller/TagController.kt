package com.tistory.eclipse4j.app.api.display.tag.controller

import com.tistory.eclipse4j.app.api.display.response.AppApiResponse
import com.tistory.eclipse4j.app.api.display.tag.data.Tag
import com.tistory.eclipse4j.app.api.display.tag.service.TagService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TagController(val tagService: TagService) {

    @GetMapping("/api/v1/tags")
    fun findAllTag(@RequestParam("limit") limit: Int): AppApiResponse<Page<Tag>> {
        return AppApiResponse.success(tagService.findAllLimit(limit))
    }

    @GetMapping("/api/v1/tags/{id}")
    fun findById(@PathVariable("id") id: Long): AppApiResponse<Tag?> {
        return AppApiResponse.success(tagService.findById(id))
    }
}
