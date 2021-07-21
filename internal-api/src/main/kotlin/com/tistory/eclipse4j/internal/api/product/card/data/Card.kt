package com.tistory.eclipse4j.internal.api.product.card.data

import com.tistory.eclipse4j.domain.rest.api.doc.annotation.RestDocClass
import com.tistory.eclipse4j.domain.rest.api.doc.annotation.RestDocProperty
import java.io.Serializable

@RestDocClass
data class Card(
    @field:RestDocProperty("Card PK") val id: Long,
    @field:RestDocProperty("Title") val title: String
) : Serializable
