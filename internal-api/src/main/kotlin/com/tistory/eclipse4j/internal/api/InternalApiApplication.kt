package com.tistory.eclipse4j.internal.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["com.tistory.eclipse4j.internal", "com.tistory.eclipse4j.domain.persist"]
)
class InternalApiApplication

fun main(args: Array<String>) {
    runApplication<InternalApiApplication>(*args)
}
