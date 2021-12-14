package com.tistory.eclipse4j.app.api.user.controller

import com.tistory.eclipse4j.app.api.response.AppApiResponse
import com.tistory.eclipse4j.domain.persist.api.userinfo.body.UserInfoBody
import com.tistory.eclipse4j.domain.persist.api.userinfo.body.UserInfoResponse
import com.tistory.eclipse4j.domain.persist.api.userinfo.service.UserInfoClientService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userInfoClientService: UserInfoClientService) {
    @GetMapping("/api/v1/users/{userId}")
    fun findAllTag(@PathVariable("userId") userId: String): AppApiResponse<UserInfoResponse<UserInfoBody>> {
        return AppApiResponse.success(userInfoClientService.getRunBlockingUserInfo(userId))
    }
}
