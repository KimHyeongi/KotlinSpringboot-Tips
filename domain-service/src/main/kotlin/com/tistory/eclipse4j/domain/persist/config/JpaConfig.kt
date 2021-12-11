package com.tistory.eclipse4j.domain.persist.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(
    basePackages = ["com.tistory.eclipse4j.domain.persist.db"]
)
@EntityScan(
    basePackages = ["com.tistory.eclipse4j.domain.persist.db"]
)
class JpaConfig
