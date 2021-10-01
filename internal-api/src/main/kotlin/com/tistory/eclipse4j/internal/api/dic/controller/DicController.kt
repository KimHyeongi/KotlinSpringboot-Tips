package com.tistory.eclipse4j.internal.api.dic.controller

import com.tistory.eclipse4j.internal.api.dic.data.Dic
import com.tistory.eclipse4j.internal.api.dic.service.DicService
import com.tistory.eclipse4j.internal.api.response.InternalApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

// 예시 Controller
@RestController
class DicController(val dicService: DicService) {

    @GetMapping("/api/v1/dics/{id}")
    fun findById(@PathVariable("id") id: Long): InternalApiResponse<Dic> {
        return InternalApiResponse.success(Dic(id = 0, contents = "이 단어는 ...."))
    }
}
