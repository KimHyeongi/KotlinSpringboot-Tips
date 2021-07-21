package com.tistory.eclipse4j.domain.persist.card.service

import com.tistory.eclipse4j.domain.persist.card.entity.CardEntity
import com.tistory.eclipse4j.domain.persist.card.repository.CardRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CardQueryService(val cardRepository: CardRepository) {
    fun findById(id: Long): CardEntity? = cardRepository.findByIdOrNull(id)
}
