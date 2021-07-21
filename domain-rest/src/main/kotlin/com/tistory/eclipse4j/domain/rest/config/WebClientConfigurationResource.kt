package com.tistory.eclipse4j.domain.rest.config

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import java.util.function.Consumer

@Configuration
abstract class WebClientConfigurationResource {
    private val logger: Logger = LoggerFactory.getLogger(WebClientConfigurationResource::class.java)

    protected fun httpClient(): HttpClient {
        return HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
            .doOnConnected { connection ->
                connection
                    .addHandlerLast(ReadTimeoutHandler(2))
                    .addHandlerLast(WriteTimeoutHandler(2))
            }
    }
    protected fun logRequest(): ExchangeFilterFunction {
        return ExchangeFilterFunction { clientRequest, next ->
            logger.info("WebClient Log] Request: {} {}", clientRequest.method(), clientRequest.url())
            logger.info("--- WebClient Log] Http Headers: ---")
            clientRequest.headers().forEach { name: String, values: List<String> -> logHeader(name, values) }
            logger.info("--- WebClient Log] Http Cookies: ---")
            clientRequest.cookies().forEach { (name: String, values: List<String>) -> logHeader(name, values) }
            next.exchange(clientRequest)
        }
    }

    protected fun logResponse(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofResponseProcessor { clientResponse ->
            logger.info("WebClient Log] Response: {}", clientResponse.statusCode())
            clientResponse.headers().asHttpHeaders()
                .forEach {
                    name, values ->
                    values.forEach { value -> logger.info("WebClient Log] {} = {}", name, value) }
                }
            Mono.just(clientResponse)
        }
    }

    protected fun logHeader(name: String, values: List<String>) {
        values.forEach(Consumer { value: String? -> logger.info("WebClient Log] {} = {}", name, value) })
    }
}
