package com.tistory.eclipse4j.app.api.dic.service

import com.tistory.eclipse4j.app.api.dic.data.DicResponse
import com.tistory.eclipse4j.app.api.dic.mapper.DicMapper
import com.tistory.eclipse4j.domain.persist.db.dic.service.DicFindService
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import mu.KotlinLogging
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class DicService(
    val dicService: DicFindService,
    val dicLocalCacheableService: DicLocalCacheableService
) {

    val log = KotlinLogging.logger { }

    @CircuitBreaker(name = "default_circuit_cachedFindById", fallbackMethod = "localCachedFindByIdFallback")
    @Cacheable(value = ["cached_dic_by_id"], key = "#id")
    fun findById(id: Long): DicResponse {
        log.info { "레디스캐시로 저장됩니다." }
        return kotlin.runCatching { dicService.findById(id) }
            .map { DicMapper.toResponse(it) }
            .getOrThrow()
    }

    private fun localCachedFindByIdFallback(id: Long, e: Exception): DicResponse {
        log.info { "로컬캐시로 전환되었습니다." }
        return dicLocalCacheableService.findById(id)
    }

    fun findAllLimit(limit: Int): Page<DicResponse> {
        val page = dicService.findAllPageable(PageRequest.of(0, limit))
        return page.map {
            DicMapper.toResponse(it)
        }
    }
}
