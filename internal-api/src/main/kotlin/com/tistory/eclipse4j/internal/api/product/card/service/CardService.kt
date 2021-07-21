package com.tistory.eclipse4j.internal.api.product.card.service

import com.tistory.eclipse4j.domain.persist.card.service.CardQueryService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CardService(val cardQueryService: CardQueryService) {
    fun findById(id: Long) = cardQueryService.findById(id)
}
