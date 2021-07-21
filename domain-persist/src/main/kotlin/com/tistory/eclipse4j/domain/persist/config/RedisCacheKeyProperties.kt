package com.tistory.eclipse4j.domain.persist.config

enum class RedisCacheKeyProperties(val description: String, val ttl: Long, val key: Boolean, val placeholder: String, val dpOrderNumber: Int) {
    cached_default(
        "시스템 유지 관리 캐시",
        60 * 60 * 24L,
        true,
        "KEY",
        0
    ), // second s * m * h
    cached_tag_id(
        "태그 캐시",
        60 * 60 * 24L,
        true,
        "KEY",
        0
    ), // second s * m * h
}
