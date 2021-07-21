package com.tistory.eclipse4j.internal.api.product.card.controller

import com.ninjasquad.springmockk.MockkBean
import com.tistory.eclipse4j.domain.rest.api.doc.annotation.RestDocsResponseFieldGenerator
import com.tistory.eclipse4j.internal.api.api.doc.mock.MockMvcDocBaseTest
import com.tistory.eclipse4j.internal.api.product.card.data.Card
import com.tistory.eclipse4j.internal.api.product.card.service.CardService
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(CardController::class)
class CardControllerTest : MockMvcDocBaseTest() {

    @MockkBean
    lateinit var cardService: CardService

    private val generator = RestDocsResponseFieldGenerator()

    @Test
    fun `Card 정보 가져오기`() {
        this.mockMvc.perform(
            RestDocumentationRequestBuilders.get("/api/v1/cards/{id}", "1")
                .header("secret-client-key", "HEADER_VALUE")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("data").hasJsonPath())
            .andDo(
                MockMvcRestDocumentation.document(
                    DOCUMENT,
                    Preprocessors.preprocessRequest(Preprocessors.prettyPrint()), Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                    RequestDocumentation.pathParameters(
                        RequestDocumentation.parameterWithName("id").description("Card PK")
                    ),
                    RequestDocumentation.requestParameters(),
                    PayloadDocumentation.responseFields(
                        generator.buildObjectResponse(Card::class.java)
                    ),
                    HeaderDocumentation.responseHeaders(HeaderDocumentation.headerWithName("Content-Type").description("The Content-Type of the payload, e.g. `application/hal+json`"))
                )
            )
    }
}
