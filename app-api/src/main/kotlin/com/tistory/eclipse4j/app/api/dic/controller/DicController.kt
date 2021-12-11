package com.tistory.eclipse4j.app.api.dic.controller

import com.tistory.eclipse4j.app.api.dic.data.DicResponse
import com.tistory.eclipse4j.app.api.dic.service.DicService
import com.tistory.eclipse4j.app.api.response.AppApiResponse
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class DicController(val dicService: DicService) {

    @GetMapping("/api/v1/dics")
    fun findAllTag(@RequestParam("limit") limit: Int): AppApiResponse<Page<DicResponse>> {
        return AppApiResponse.success(dicService.findAllLimit(limit))
    }

    @GetMapping("/api/v1/dics/{id}")
    fun findById(@PathVariable("id") id: Long): AppApiResponse<DicResponse> {
        return AppApiResponse.success(dicService.findById(id))
    }
}
