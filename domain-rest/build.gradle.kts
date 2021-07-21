plugins {
}

dependencies {
    implementation(project(Projects.commons))
//    api(Libraries.spring_retry)
    implementation(Libraries.spring_boot_starter_web)
    implementation(Libraries.spring_boot_starter_webflux)

//    api(Libraries.spring_cloud_starter_openfeign)
//    api(Libraries.spring_boot_starter_actuator)
//    api(Libraries.micrometer_registry_prometheus)

    api(Libraries.kotlin_logging)
    api(Libraries.jackson_module_kotlin)
    api(Libraries.jackson_databind)
    api(Libraries.jackson_datatype_jdk8)
    api(Libraries.jackson_datatype_jsr310)

    api(Libraries.logstash_logback_encoder)
    api(Libraries.logback_classic)
//    api(Libraries.swagger_starter)

    api(Libraries.guava)
    api(Libraries.restdocs_mockmvc)
    api(Libraries.restdocs_restassured)
    api(Libraries.restdocs_restassured_mvc)
    api(Libraries.restdocs_restassured_rest)
}
