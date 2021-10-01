package com.tistory.eclipse4j.app.api.response

data class AppApiResponse<T>(
    val timestamp: Long,
    val errorCode: String,
    val statusCode: String,
    val statusMessage: String,
    val data: T? = null
) {
    companion object {
        fun <T> success(t: T): AppApiResponse<T> = AppApiResponse(
            timestamp = 0,
            errorCode = "",
            statusCode = "200",
            statusMessage = "OK",
            data = t
        )
    }
}
