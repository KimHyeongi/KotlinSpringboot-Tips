package com.tistory.eclipse4j.domain.persist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DomainTestAppliation

fun main(args: Array<String>) {
    runApplication<DomainTestAppliation>(*args)
}
