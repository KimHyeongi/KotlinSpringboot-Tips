package com.tistory.eclipse4j.app.api.display.api.doc.mock

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@ExtendWith(RestDocumentationExtension::class, SpringExtension::class)
abstract class MockMvcDocBaseTest {

    protected lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp(webApplicationContext: WebApplicationContext?, restDocumentation: RestDocumentationContextProvider?) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext!!)
            .apply<DefaultMockMvcBuilder>(
                MockMvcRestDocumentation.documentationConfiguration(restDocumentation)
                    .uris()
                    .withHost("api.myhost.com")
                    .withPort(80)
                    .withScheme("http")
            )
            .alwaysDo<DefaultMockMvcBuilder>(
                MockMvcRestDocumentation.document(DOCUMENT, Preprocessors.preprocessRequest(Preprocessors.prettyPrint()), Preprocessors.preprocessResponse(Preprocessors.prettyPrint()))
            )
            .build()
    }

    companion object {
        const val DOCUMENT = "{class-name}/{method-name}"
    }
}
