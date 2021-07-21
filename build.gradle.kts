import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/")
        mavenCentral()
    }

    dependencies {
        classpath(Libraries.sonarqube_gradle_plugin)
        classpath(Libraries.kotlin_gradle_plugin)
        classpath(Libraries.ktlint_gradle_plugin)
    }
}

plugins {
    id(BuildPlugins.spring_dependency_management) version BuildPlugins.Versions.dependency_management
    id(BuildPlugins.spring_boot) version BuildPlugins.Versions.spring_boot
    id(BuildPlugins.sonarqube) version BuildPlugins.Versions.sonarqube
    id(BuildPlugins.ktlint) version BuildPlugins.Versions.ktlint
    kotlin("jvm") version Versions.kotlin
    kotlin("plugin.allopen") version Versions.kotlin
    kotlin("plugin.noarg") version Versions.kotlin
    kotlin("kapt") version Versions.kotlin
    kotlin("plugin.spring") version Versions.kotlin apply false
    kotlin("plugin.jpa") version Versions.kotlin apply false

    idea
    jacoco
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

sonarqube {
    properties {
        property("sonar.host.url", project.findProperty("sonarHostUrl") ?: "http://")
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.exclusions", "**/*Test*.*,**/Q*.java")
        property("sonar.cpd.exclusions", "**/*Config.kt,**/*Configuration.kt")
        property("sonar.tests", "src/integration-test/kotlin,src/test/kotlin")
        property("sonar.test.inclusions", "**/*Test.kt,**/*TestConfig.kt")
        property("sonar.coverage.exclusions", "**/*Test*.*,**/Q*.java")
    }
}

allprojects {
    repositories {
        maven("https://plugins.gradle.org/m2/")
        maven("https://jitpack.io")
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("idea")
        plugin("java-library")
        plugin("kotlin")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.jetbrains.kotlin.plugin.allopen")
        plugin("org.jetbrains.kotlin.plugin.noarg")
        plugin("org.jetbrains.kotlin.plugin.jpa")
        plugin("org.jetbrains.kotlin.kapt")
        plugin("jacoco")
//        plugin("java-test-fixtures")
    }

    apply(plugin = BuildPlugins.ktlint)

    sourceSets {
        create("integration-test") {
            compileClasspath += sourceSets.main.get().output + sourceSets.test.get().output
            runtimeClasspath += sourceSets.main.get().output + sourceSets.test.get().output

            resources.srcDir(file("src/integration-test/resources"))
        }
    }
    val integrationTestImplementation by configurations.getting {
        extendsFrom(configurations.implementation.get(), configurations.testImplementation.get())
    }
    configurations {
        "integrationTestRuntimeOnly" {
            extendsFrom(configurations.testRuntimeOnly.get())
        }
    }

    dependencyManagement {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
            mavenBom(Libraries.spring_boot_dependencyManagement)
            mavenBom(Libraries.spring_cloud_dependencyManagement)
        }
    }

    tasks.jar {
        enabled = true
    }

    tasks.bootJar {
        enabled = false
    }

    jacoco {
        toolVersion = "0.8.7"
    }

    sonarqube {
        properties {
            property("sonar.coverage.jacoco.xmlReportPaths", "${project.buildDir}/reports/all-test/jacoco.xml")
        }
    }

    tasks.test {
        extensions.configure(JacocoTaskExtension::class) {
            setDestinationFile(file("$buildDir/jacoco/jacoco.exec"))
        }

        reports {
            html.isEnabled = true
            junitXml.isEnabled = true
        }
    }

    val integrationTest = task<Test>("integrationTest") {
        description = "Runs integration tests."
        group = "verification"

        testClassesDirs = sourceSets["integration-test"].output.classesDirs
        classpath = sourceSets["integration-test"].runtimeClasspath

        reports {
            html.isEnabled = true
            junitXml.isEnabled = true
        }

        extensions.configure(JacocoTaskExtension::class) {
            setDestinationFile(file("$buildDir/jacoco/jacoco-integration.exec"))
        }

        shouldRunAfter("test")
    }

    tasks.jacocoTestReport {
        executionData(fileTree(buildDir).include("/jacoco/*.exec"))

        reports {
            xml.isEnabled = true
            xml.destination = file("$buildDir/reports/all-test/jacoco.xml")
            html.isEnabled = true
            html.destination = file("$buildDir/reports/all-test/html")
        }

        mustRunAfter("test", "integrationTest")
    }

    tasks.check {
        dependsOn("integrationTest")
        dependsOn(tasks.jacocoTestReport)
    }

    tasks.withType<Test> {
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
        useJUnitPlatform()
        testLogging {
            events = setOf(FAILED, PASSED, SKIPPED)
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf(
                "-Xjsr305=strict",
                "-Xinline-classes",
                "-progressive"
            )
            jvmTarget = "11"
        }
    }

    tasks.withType<org.gradle.jvm.tasks.Jar> {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }

    allOpen {
        annotation("javax.persistence.Entity")
        annotation("javax.persistence.MappedSuperclass")
    }

    noArg {
        annotation("javax.persistence.Entity")
        annotation("javax.persistence.MappedSuperclass")
    }

    dependencies {
        implementation(Libraries.spring_boot_starter)
        implementation(Libraries.kotlin_reflect)
        implementation(Libraries.kotlin_stdlib_jdk8)
        implementation(Libraries.slf4j_api)
        implementation(Libraries.kassava)

        testImplementation(Libraries.kotest_runner_junit5)
        testImplementation(Libraries.kotest_assertions_core)
        testImplementation(Libraries.kotest_assertions_json)
        testImplementation(Libraries.kotest_property)
        testImplementation(Libraries.kotest_extensions_spring)
        testImplementation(Libraries.mockk)
        testImplementation(Libraries.ninja_squad_springmockk)
        testImplementation(Libraries.spring_boot_starter_test)

        kapt("org.springframework.boot:spring-boot-configuration-processor")
    }
}

dependencies {
}

tasks.jar {
    enabled = true
}

tasks.bootJar {
    enabled = false
}
