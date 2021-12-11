

tasks.jar {
    enabled = false
}

tasks.bootJar {
    enabled = true
    mainClass.set("com.tistory.eclipse4j.batch.BatchApplicationKt")
    archiveFileName.set("${archiveBaseName.get()}.${archiveExtension.get()}")
}

plugins {
    kotlin("plugin.spring")
    id("org.springframework.boot")
}

dependencies {
    implementation(project(Projects.domain_model))
    implementation(project(Projects.domain_service))
    implementation(Libraries.spring_boot_starter_actuator)
    implementation(Libraries.spring_boot_starter_batch)
    testImplementation("org.springframework.batch:spring-batch-test")
}
