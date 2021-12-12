package com.tistory.eclipse4j.domain.persist.api.userinfo.service

import com.tistory.eclipse4j.domain.persist.api.userinfo.body.UserInfoBody
import com.tistory.eclipse4j.domain.persist.api.userinfo.body.UserInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import java.util.concurrent.Executors

@Service
class UserInfoClientService(
    @Qualifier("userInfoWebClient")
    private val userInfoWebClient: WebClient
) {

    fun getRunBlockingUserInfo(userId: String): UserInfoResponse<UserInfoBody> {
        return runBlocking<UserInfoResponse<UserInfoBody>> {
            getUserInfoByUserId_DispatchersIO(userId)
        }
    }

    // https://kt.academy/article/cc-dispatchers
    private suspend fun getUserInfoByUserId_DispatchersIO(userId: String): UserInfoResponse<UserInfoBody> {
        return withContext(Dispatchers.IO) {
            userInfoWebClient.get().uri("/users/{userId}/info", userId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .awaitBody<UserInfoResponse<UserInfoBody>>()
        }
    }

    private suspend fun getUserInfoByUserId_Executors(userId: String): UserInfoResponse<UserInfoBody> {
        val executor = Executors.newFixedThreadPool(3)
        return withContext(executor.asCoroutineDispatcher()) {
            userInfoWebClient.get().uri("/users/{userId}/info", userId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .awaitBody<UserInfoResponse<UserInfoBody>>()
        }
    }
}
