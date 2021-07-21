package com.tistory.eclipse4j.domain.rest.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfiguration : WebClientConfigurationResource() {

    @Bean("companyWebClient")
    fun webClientForCompany(): WebClient {
        return WebClient.builder()
            .baseUrl("https://eclipse4j.tistory.com") // TODO yml
            .clientConnector(ReactorClientHttpConnector(httpClient()))
            .filter(logRequest())
            .filter(logResponse())
            .build()
    }
}
