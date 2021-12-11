package com.tistory.eclipse4j.domain.persist.db.dic.service

import com.tistory.eclipse4j.domain.persist.db.dic.entity.Dic
import com.tistory.eclipse4j.domain.persist.db.dic.repository.DicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class DicFindService(val dicRepository: DicRepository) {

    fun findById(id: Long): Dic = checkNotNull(dicRepository.findByIdOrNull(id)) { "검색된 정보가 없습니다." }

    fun findAllPageable(of: PageRequest): Page<Dic> {
        return dicRepository.findAll(of)
    }
}
