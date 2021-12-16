package com.tistory.eclipse4j.app.api.dic.service

import com.tistory.eclipse4j.app.api.dic.data.DicResponse
import com.tistory.eclipse4j.app.api.dic.mapper.DicMapper
import com.tistory.eclipse4j.domain.persist.db.dic.service.DicFindService
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class DicLocalCacheableService(
    val dicService: DicFindService
) {

    @Cacheable(value = ["cached_dic_by_id"], key = "#id", cacheManager = "caffeineCacheManager")
    fun findById(id: Long): DicResponse =
        kotlin.runCatching { dicService.findById(id) }
            .map { DicMapper.toResponse(it) }
            .getOrThrow()
}
