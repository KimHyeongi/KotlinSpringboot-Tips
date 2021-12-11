package com.tistory.eclipse4j.domain.persist.config

import org.apache.commons.pool2.impl.GenericObjectPoolConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import java.time.Duration

/**
 * Application 여러 Redis 사용시
 * 데이터 저장용 (Spring Data Redis)
 */
@Configuration
@EnableRedisRepositories(
    basePackages = ["com.tistory.eclipse4j.domain.persist.redis"],
    redisTemplateRef = "storedRedisTemplate"
)
class StoredRedisMultipleConfiguration {

    @Bean("redisStockConnectionFactory")
    fun redisStockConnectionFactory(): RedisConnectionFactory {
        val redisStandaloneConfiguration = RedisStandaloneConfiguration(redisHost, redisPort)
        val poolConfig = genericObjectPoolConfig()
        val lettuceConnectionFactory = LettuceConnectionFactory(
            redisStandaloneConfiguration,
            LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofSeconds(10))
                .shutdownTimeout(Duration.ZERO)
                .poolConfig(poolConfig)
                .build()
        )
        lettuceConnectionFactory.shareNativeConnection = false
        return lettuceConnectionFactory
    }

    @Bean("storedRedisTemplate")
    fun storedRedisTemplate(): RedisTemplate<*, *> {
        val redisTemplate = RedisTemplate<ByteArray, ByteArray>()
        redisTemplate.setConnectionFactory(redisStockConnectionFactory())
        return redisTemplate
    }

    private fun genericObjectPoolConfig(): GenericObjectPoolConfig<Any?> {
        val poolConfig = GenericObjectPoolConfig<Any?>()
        poolConfig.maxIdle = maxIdle
        poolConfig.minIdle = minIdle
        poolConfig.maxWaitMillis = maxWait
        poolConfig.maxTotal = maxActive
        return poolConfig
    }

    @Value("\${spring.redis-stored.host}")
    private lateinit var redisHost: String

    @Value("\${spring.redis-stored.port}")
    private val redisPort = 0

    @Value("\${spring.redis-stored.lettuce.pool.max-active:32}")
    private val maxActive: Int = 32

    @Value("\${spring.redis-stored.lettuce.pool.min-idle:8}")
    private val minIdle: Int = 8

    @Value("\${spring.redis-stored.lettuce.pool.max-idle:16}")
    private val maxIdle: Int = 8

    @Value("\${spring.redis-stored.lettuce.pool.max-wait:-1}")
    private val maxWait: Long = -1
}
