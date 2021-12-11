package com.tistory.eclipse4j.domain.persist.config

enum class LocalCacheKeyProperties(val description: String, val ttl: Long, val key: Boolean, val placeholder: String, val dpOrderNumber: Int) {
    cached_dic_id(
        "사전 캐시",
        60 * 60 * 24L,
        true,
        "KEY",
        0
    ), // second s * m * h
}
