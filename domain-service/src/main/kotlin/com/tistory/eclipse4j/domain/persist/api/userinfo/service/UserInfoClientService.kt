package com.tistory.eclipse4j.domain.persist.api.userinfo.service

import com.tistory.eclipse4j.domain.persist.api.userinfo.body.UserInfoBody
import com.tistory.eclipse4j.domain.persist.api.userinfo.body.UserInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Service
class UserInfoClientService(
    @Qualifier("userInfoWebClient")
    private val userInfoWebClient: WebClient
) {

    fun getRunBlockingUserInfo(userId: String): UserInfoResponse<UserInfoBody> {
        return runBlocking<UserInfoResponse<UserInfoBody>> {
            getUserInfoByUserId(userId)
        }
    }

    private suspend fun getUserInfoByUserId(userId: String): UserInfoResponse<UserInfoBody> {
        return withContext(Dispatchers.IO) {
            userInfoWebClient.get().uri("/users/{userId}/info", userId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .awaitBody<UserInfoResponse<UserInfoBody>>()
        }
    }
}
