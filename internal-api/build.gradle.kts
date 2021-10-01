
plugins {
}

dependencies {
    implementation(project(Projects.domain_model))
    implementation(project(Projects.domain_service))

    implementation(Libraries.spring_boot_starter_actuator)
//    api(Libraries.spring_retry)
    implementation(Libraries.spring_boot_starter_web)
    implementation(Libraries.spring_boot_starter_webflux)

    implementation(Libraries.kotlinx_coroutines_core)

    implementation(Libraries.jackson_module_kotlin)
    implementation(Libraries.jackson_databind)
    implementation(Libraries.jackson_datatype_jdk8)
    implementation(Libraries.jackson_datatype_jsr310)

    implementation(Libraries.logstash_logback_encoder)
    implementation(Libraries.guava)
    implementation(Libraries.spring_boot_starter_aop)
}

tasks.jar {
    enabled = false
}

tasks.bootJar {
    enabled = true
    mainClass.set("com.tistory.eclipse4j.internal.api.InternalApiApplicationKt")
    archiveFileName.set("${archiveBaseName.get()}.${archiveExtension.get()}")
}
