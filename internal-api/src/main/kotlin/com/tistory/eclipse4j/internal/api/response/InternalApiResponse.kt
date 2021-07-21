package com.tistory.eclipse4j.internal.api.response

data class InternalApiResponse<T>(
    val timestamp: Long,
    val errorCode: String,
    val statusCode: String,
    val statusMessage: String,
    val data: T? = null
) {
    companion object {
        fun <T> success(t: T): InternalApiResponse<T> = InternalApiResponse(
            timestamp = 0,
            errorCode = "",
            statusCode = "200",
            statusMessage = "OK",
            data = t
        )
    }
}
