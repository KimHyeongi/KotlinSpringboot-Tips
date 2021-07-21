package com.tistory.eclipse4j.app.api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {

    @GetMapping("/", "/elb-health", "/health")
    fun health() = "OK"
}
