package com.tistory.eclipse4j.app.api.dic.service

import com.tistory.eclipse4j.app.api.dic.data.Dic
import com.tistory.eclipse4j.domain.persist.dic.service.DicFindService
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class DicService(val dicService: DicFindService) {

    @Cacheable(value = ["cached_dic_by_id"], key = "#id")
    fun findById(id: Long): Dic {
        val dicEntity = dicService.findById(id)
        return dicEntity.let { Dic.from(it) }
    }

    fun findAllLimit(limit: Int): Page<Dic> {
        val page = dicService.findAllPageable(PageRequest.of(0, limit))
        return page.map {
            Dic.from(it)
        }
    }
}
