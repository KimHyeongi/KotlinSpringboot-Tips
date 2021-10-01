package com.tistory.eclipse4j.internal.api.dic.service

import com.tistory.eclipse4j.domain.persist.dic.service.DicFindService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DicService(val dicFindService: DicFindService) {
    fun findById(id: Long) = dicFindService.findById(id)
}
