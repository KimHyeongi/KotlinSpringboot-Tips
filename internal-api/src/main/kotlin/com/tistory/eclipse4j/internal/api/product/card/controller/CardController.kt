package com.tistory.eclipse4j.internal.api.product.card.controller

import com.tistory.eclipse4j.internal.api.product.card.data.Card
import com.tistory.eclipse4j.internal.api.product.card.service.CardService
import com.tistory.eclipse4j.internal.api.response.InternalApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

// 예시 Controller
@RestController
class CardController(val cardService: CardService) {

    @GetMapping("/api/v1/cards/{id}")
    fun findById(@PathVariable("id") id: Long): InternalApiResponse<Card> {
        return InternalApiResponse.success(Card(id = 0, title = "card title"))
    }
}
