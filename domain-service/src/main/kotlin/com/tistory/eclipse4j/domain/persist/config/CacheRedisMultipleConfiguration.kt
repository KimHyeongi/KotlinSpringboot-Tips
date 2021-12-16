package com.tistory.eclipse4j.domain.persist.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.github.benmanes.caffeine.cache.Caffeine
import org.apache.commons.pool2.impl.GenericObjectPoolConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration
import java.util.concurrent.TimeUnit

/**
 * Application 여러 Redis 사용시
 * 캐시
 */
@EnableCaching
@Configuration(proxyBeanMethods = true)
class CacheRedisMultipleConfiguration {

    private fun createCacheConfiguration(timeoutInSeconds: Long): RedisCacheConfiguration {
        return RedisCacheConfiguration
            .defaultCacheConfig()
            .entryTtl(Duration.ofSeconds(timeoutInSeconds))
            .disableCachingNullValues()
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer()))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer(mapper())))
    }

    @Suppress("DEPRECATION")
    private fun mapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)
        objectMapper.registerModule(JavaTimeModule())
        return objectMapper
    }

    private fun getCacheKeyValuesMap() = RedisCacheKeyProperties.values()
        .associate {
            it.name to RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(it.ttl))
        }

    private fun genericObjectPoolConfig(): GenericObjectPoolConfig<Any?> {
        val poolConfig = GenericObjectPoolConfig<Any?>()
        poolConfig.maxIdle = maxIdle
        poolConfig.minIdle = minIdle
        poolConfig.maxWaitMillis = maxWait
        poolConfig.maxTotal = maxActive
        return poolConfig
    }

    @Primary
    @Bean("cacheRedisConnectionFactory")
    fun cacheRedisConnectionFactory(): RedisConnectionFactory {
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

    @Bean("cacheConfiguration")
    fun cacheConfiguration(): RedisCacheConfiguration =
        createCacheConfiguration(DEFAULT_EXP_TIME)

    @Primary
    @Bean(name = ["cacheManager"])
    fun cacheManager(redisConnectionFactory: RedisConnectionFactory?): RedisCacheManager? {
        return RedisCacheManager.builder(cacheRedisConnectionFactory())
            .cacheDefaults(cacheConfiguration())
            .withInitialCacheConfigurations(getCacheKeyValuesMap())
            .build()
    }

    @Primary
    @Bean("redisTemplate")
    fun cachedRedisStringTemplate(): StringRedisTemplate {
        val redisTemplate = StringRedisTemplate()
        redisTemplate.setConnectionFactory(cacheRedisConnectionFactory())
        return redisTemplate
    }

    companion object {
        private const val DEFAULT_EXP_TIME: Long = 600L // 10분캐시
    }

    @Value("\${spring.redis-cached.host}")
    private lateinit var redisHost: String

    @Value("\${spring.redis-cached.port}")
    private val redisPort = 0

    @Value("\${spring.redis-cached.lettuce.pool.max-active:32}")
    private val maxActive: Int = 32

    @Value("\${spring.redis-cached.lettuce.pool.min-idle:8}")
    private val minIdle: Int = 8

    @Value("\${spring.redis-cached.lettuce.pool.max-idle:16}")
    private val maxIdle: Int = 8

    @Value("\${spring.redis-cached.lettuce.pool.max-wait:-1}")
    private val maxWait: Long = -1

    /**
     * @EnableCircuitBreaker
     *
     * cachemanager secondry 처리 후 사용해야함.
     * https://programmer.group/caffeine-the-king-of-local-cache-performance.html
     * ex)
     * @Cacheable(cacheNames = "cachenames", cacheManager = "caffeineCacheManager")
     * public List<Object> getXxxxxxxxxx(Integer id) {
     </Object> */
    @Bean("caffeineCacheManager")
    fun caffeineCacheManager(): CacheManager {
        val cacheManager = SimpleCacheManager()
        val caches: List<CaffeineCache> = RedisCacheKeyProperties.values()
            .map { cache ->
                CaffeineCache(
                    cache.name,
                    Caffeine.newBuilder().recordStats()
                        .expireAfterWrite(cache.ttl, TimeUnit.SECONDS)
                        .maximumSize(500)
                        .build()
                )
            }
        cacheManager.setCaches(caches)
        return cacheManager
    }
}
