package com.tistory.eclipse4j.domain.persist.tag.service

import com.tistory.eclipse4j.domain.persist.tag.repository.TagRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class TagQueryService(private val tagRepository: TagRepository) {
    fun findById(id: Long) = tagRepository.findByIdOrNull(id)

    fun findAllPageable(pageable: Pageable) = tagRepository.findAllPageable(pageable)
}
