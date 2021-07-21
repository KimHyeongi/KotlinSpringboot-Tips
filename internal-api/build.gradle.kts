import org.asciidoctor.gradle.jvm.AsciidoctorTask
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.asciidoctor.jvm.convert") version "3.3.2"
}

dependencies {
    api(project(Projects.commons))
//    api(project(Projects.domain_infra))
    api(project(Projects.domain_persist))
    api(project(Projects.domain_rest))

    implementation(Libraries.spring_boot_starter_actuator)
    implementation(Libraries.spring_boot_starter_web)
    implementation(Libraries.spring_boot_starter_webflux)
}

val snippetsDir = file("build/generated-snippets")
val asciiDoctor = tasks.withType<AsciidoctorTask> {
    inputs.dir(snippetsDir)
    dependsOn(tasks.test)
}

tasks.getByName<Jar>("jar") {
    enabled = false
}

tasks.named<BootJar>("bootJar") {
    enabled = true
    dependsOn(asciiDoctor)
    from("${asciiDoctor.first().outputDir}") {
        into("static/docs")
    }
    mainClass.set("com.tistory.eclipse4j.internal.api.InternalApiApplicationKt")
    archiveFileName.set("${archiveBaseName.get()}.${archiveExtension.get()}")
}
