package com.tistory.eclipse4j.domain.infra.config

import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy

@Configuration
class AwsSqsListenerConfiguration {
    @Bean
    fun eventThreadPoolExecutor(): ThreadPoolTaskExecutor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 100
        executor.maxPoolSize = 500
        executor.setQueueCapacity(0)
        executor.setRejectedExecutionHandler(CallerRunsPolicy())
        executor.setWaitForTasksToCompleteOnShutdown(true)
        executor.setAwaitTerminationSeconds(60)
        return executor
    }

    @Bean
    fun simpleMessageListenerContainerFactory(eventThreadPoolExecutor: ThreadPoolTaskExecutor): SimpleMessageListenerContainerFactory {
        val factory = SimpleMessageListenerContainerFactory()
        factory.setMaxNumberOfMessages(10)
        factory.setTaskExecutor(eventThreadPoolExecutor)
        return factory
    }
}
