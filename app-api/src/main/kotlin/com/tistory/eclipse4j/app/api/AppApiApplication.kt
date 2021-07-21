package com.tistory.eclipse4j.app.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["com.tistory.eclipse4j.app", "com.tistory.eclipse4j.domain"]
)
class AppApiApplication

fun main(args: Array<String>) {
    runApplication<AppApiApplication>(*args)
}
