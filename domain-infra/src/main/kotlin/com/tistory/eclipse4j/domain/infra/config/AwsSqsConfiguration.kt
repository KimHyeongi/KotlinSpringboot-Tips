package com.tistory.eclipse4j.domain.infra.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsyncClient
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import com.amazonaws.services.sqs.buffered.AmazonSQSBufferedAsyncClient
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.cloud.aws.core.config.AmazonWebserviceClientFactoryBean
import org.springframework.cloud.aws.core.env.ResourceIdResolver
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.messaging.converter.DefaultContentTypeResolver
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.converter.MessageConverter
import org.springframework.util.MimeType
import java.nio.charset.StandardCharsets

@Configuration
class AwsSqsConfiguration {

    @Primary
    @Bean
    @Profile(value = ["local", "test"])
    fun amazonSqsLocalStack(): AmazonSQSBufferedAsyncClient {
        val amazonSQSAsync = AmazonSQSAsyncClientBuilder.standard()
            .withEndpointConfiguration(
                AwsClientBuilder.EndpointConfiguration(
                    "http://localhost:4576",
                    "us-east-1"
                )
            )
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials("accesskey", "secretkey")))
            .build()
        return AmazonSQSBufferedAsyncClient(amazonSQSAsync)
    }

    @Bean
    @Profile(value = ["local", "test"])
    fun messageTemplateLocalStack(amazonSQS: AmazonSQSBufferedAsyncClient, eventMessageConverter: MessageConverter): MessagingTemplate {
        val objectMapper = ObjectMapper()
        return object : MessagingTemplate {
            override fun sendMessage(destinationName: String, message: Any) {
                amazonSQS.sendMessage(destinationName, objectMapper.writeValueAsString(message))
            }
        }
    }

    @Primary
    @Bean
    @Profile(value = ["beta", "prod"])
    fun amazonSqs(): AmazonSQSBufferedAsyncClient {
        val clientFactoryBean =
            AmazonWebserviceClientFactoryBean(
                AmazonSQSAsyncClient::class.java, null, null
            )
        clientFactoryBean.afterPropertiesSet()
        return AmazonSQSBufferedAsyncClient(clientFactoryBean.getObject())
    }

    @Bean
    @Profile(value = ["beta", "prod"])
    fun messageTemplate(amazonSQS: AmazonSQSBufferedAsyncClient, eventMessageConverter: MessageConverter): MessagingTemplate {
        val queueMessagingTemplate = QueueMessagingTemplate(amazonSQS, null as ResourceIdResolver?, eventMessageConverter)
        return object : MessagingTemplate {
            override fun sendMessage(destinationName: String, message: Any) {
                queueMessagingTemplate.convertAndSend(destinationName, message)
            }
        }
    }

    @Bean
    fun eventMessageConverter(): MappingJackson2MessageConverter {
        val objectMapper: ObjectMapper = Jackson2ObjectMapperBuilder()
            .modules(KotlinModule(), JavaTimeModule())
            .build()
        val contentTypeResolver = DefaultContentTypeResolver()
        contentTypeResolver.defaultMimeType = MimeType("application", "json", StandardCharsets.UTF_8)
        val converter = MappingJackson2MessageConverter()
        converter.contentTypeResolver = contentTypeResolver
        converter.objectMapper = objectMapper
        converter.serializedPayloadClass = String::class.java
        return converter
    }
}

interface MessagingTemplate {
    fun sendMessage(destinationName: String, message: Any)
}
