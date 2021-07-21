import org.asciidoctor.gradle.jvm.AsciidoctorTask
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
//    id("org.asciidoctor.jvm.convert") version "2.4.0"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
}

dependencies {
    api(project(Projects.commons))
//    api(project(Projects.domain_infra))
    api(project(Projects.domain_persist))
    api(project(Projects.domain_rest))

    implementation(Libraries.spring_boot_starter_web)
    implementation(Libraries.spring_boot_starter_webflux)

//    implementation(Libraries.annotation_asciidoctor_test)
//    asciidoctor(Libraries.annotation_asciidoctor)
}

val snippetsDir = file("build/generated-snippets")
val asciiDoctor = tasks.withType<AsciidoctorTask> {
    inputs.dir(snippetsDir)
    dependsOn(tasks.test)
}

tasks.getByName<Jar>("jar") {
    enabled = false
//    archiveFileName.set("xxx.jar")
//    archiveClassifier.set("library")
}

tasks.named<BootJar>("bootJar") {
    enabled = true
    dependsOn(asciiDoctor)
    from("${asciiDoctor.first().outputDir}") {
        into("static/docs")
    }
    mainClass.set("com.tistory.eclipse4j.app.api.AppApiApplicationKt")
    archiveFileName.set("${archiveBaseName.get()}.${archiveExtension.get()}")
}
