package com.tistory.eclipse4j.app.api.display.tag.controller

import com.tistory.eclipse4j.app.api.display.tag.data.Tag
import com.tistory.eclipse4j.app.api.display.tag.service.TagService
import com.tistory.eclipse4j.domain.rest.api.doc.annotation.RestDocsResponseFieldGenerator
import com.tistory.eclipse4j.domain.rest.api.doc.annotation.RestDocsUtils
import io.restassured.config.EncoderConfig
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.config.RestAssuredMockMvcConfig
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyLong
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class, RestDocumentationExtension::class)
class TagControllerTest {

    @InjectMocks
    lateinit var controller: TagController
    @Mock
    lateinit var tagService: TagService

    private val generator = RestDocsResponseFieldGenerator()

    @BeforeEach
    fun setUp(restDocumentation: RestDocumentationContextProvider) {
        RestAssuredMockMvc.standaloneSetup(
            MockMvcBuilders.standaloneSetup(controller)
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
        )
    }

    @Test
    fun `TAG 단건 가져오기`() {
        `when`(tagService.findById(anyLong())).thenReturn(Tag(id = 0, tag = ""))
        RestAssuredMockMvc.given()
            .config(
                RestAssuredMockMvcConfig.config()
                    .encoderConfig(
                        EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)
                    )
            )
            .contentType(ContentType.JSON)
            .log()
            .body()
            .get("/api/v1/tags/{id}", "1")
            .then()
            .log()
            .all()
            .statusCode(200)
            .apply(
                MockMvcRestDocumentation.document(
                    "card-info",
                    RestDocsUtils.preprocessRequest(),
                    RestDocsUtils.preprocessResponse(),
                    HeaderDocumentation.requestHeaders(),
                    RequestDocumentation.pathParameters(
                        RequestDocumentation.parameterWithName("id").description("카드 ID")
                    ),
                    PayloadDocumentation.responseFields(
                        generator.buildObjectResponse(Tag::class.java)
                    )
                )
            )
    }
}
