object BuildPlugins {
    object Versions {
        const val dependency_management = "1.0.11.RELEASE"
        const val spring_boot = "2.5.1"
        const val spring_cloud = "2020.0.3" // 아직 모든 저장소 동기화 안됨.
//        const val spring_cloud = "Hoxton.SR11"
        const val flyway = "7.5.2"
        const val sonarqube = "3.3"
        const val ktlint = "10.1.0"
        const val node = "3.0.1"
    }

    const val spring_boot = "org.springframework.boot"
    const val spring_dependency_management = "io.spring.dependency-management"
    const val sonarqube = "org.sonarqube"
    const val flyway = "org.flywaydb.flyway"
    const val ktlint = "org.jlleitschuh.gradle.ktlint"
    const val node = "com.github.node-gradle.node"
}

object Versions {
    const val kotlin = "1.5.20"
    const val spring_boot = BuildPlugins.Versions.spring_boot
    const val spring_cloud = BuildPlugins.Versions.spring_cloud
    const val ktlint = BuildPlugins.Versions.ktlint
    const val kassava = "2.1.0"

    const val kotest = "4.6.1"
    const val kotest_ext_spring = "4.4.3"
    const val ninja_squad_springmockk = "3.0.1"
    const val mockk = "1.12.0"
    const val database_rider = "1.25.0"

    const val hibernate_jpa_api = "1.0.2.Final"
    const val querydsl = "4.2.2"

    const val slf4j = "1.7.30"
    const val logback_classic = "1.2.3"
    const val logstash_logback_encoder = "6.1"
    const val apache_commons_imaging = "1.0-alpha2"

    const val swagger = "3.0.0"

    const val elasticsearch = "7.9.3"
    const val resilience4j = "1.7.0"
    const val okhttp = "4.6.0"
    const val kotlin_logging = "2.0.10"

    const val guava = "30.1.1-jre"
    const val aws_sdk = "1.12.21"
//    const val aws_sdk = "1.11.1005"

    const val aws_spring_cloud_stater = "2.2.6.RELEASE"
}

object Libraries {
    const val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect"
    const val kotlin_stdlib_jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
    const val kotlinx_coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core"
    const val kotlin_allopen = "org.jetbrains.kotlin:kotlin-allopen:${Versions.kotlin}"
    const val kotlin_noarg = "org.jetbrains.kotlin:kotlin-noarg:${Versions.kotlin}"
    const val sonarqube_gradle_plugin = "org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:2.7.1"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val ktlint_gradle_plugin = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktlint}"
    const val kassava = "au.com.console:kassava:${Versions.kassava}"

    const val spring_boot_dependencyManagement =
        "org.springframework.boot:spring-boot-dependencies:${Versions.spring_boot}"
    const val spring_cloud_dependencyManagement =
        "org.springframework.cloud:spring-cloud-dependencies:${Versions.spring_cloud}"
    const val spring_boot_starter = "org.springframework.boot:spring-boot-starter"
    const val spring_boot_starter_aop = "org.springframework.boot:spring-boot-starter-aop"
    const val spring_boot_starter_data_jpa = "org.springframework.boot:spring-boot-starter-data-jpa"
    const val spring_boot_starter_webflux = "org.springframework.boot:spring-boot-starter-webflux"
    const val spring_boot_starter_web = "org.springframework.boot:spring-boot-starter-web"
    const val spring_boot_starter_batch = "org.springframework.boot:spring-boot-starter-batch"
    const val spring_boot_starter_test = "org.springframework.boot:spring-boot-starter-test"
    const val spring_boot_starter_cache = "org.springframework.boot:spring-boot-starter-cache"
    const val spring_boot_starter_redis = "org.springframework.boot:spring-boot-starter-data-redis"
    const val spring_boot_starter_actuator = "org.springframework.boot:spring-boot-starter-actuator"
    const val spring_boot_starter_gateway = "org.springframework.cloud:spring-cloud-starter-gateway"
    const val spring_batch_test = "org.springframework.batch:spring-batch-test"

    const val spring_cloud_starter_openfeign = "org.springframework.cloud:spring-cloud-starter-openfeign"
    const val spring_cloud_starter_sleuth = "org.springframework.cloud:spring-cloud-starter-sleuth"

    const val spring_cloud_starter_aws = "org.springframework.cloud:spring-cloud-starter-aws:${Versions.aws_spring_cloud_stater}"
    const val spring_cloud_starter_aws_messaging = "org.springframework.cloud:spring-cloud-starter-aws-messaging:${Versions.aws_spring_cloud_stater}"

    const val spring_retry = "org.springframework.retry:spring-retry"

    // java modules
    const val jakarta_persistence_api = "jakarta.persistence:jakarta.persistence-api"
    const val jakarta_annotation_api = "jakarta.annotation:jakarta.annotation-api"

    // database
    const val database_rider_core = "com.github.database-rider:rider-core:${Versions.database_rider}"
    const val database_rider_spring = "com.github.database-rider:rider-spring:${Versions.database_rider}"
    const val hibernate_validator = "org.hibernate.validator:hibernate-validator"

    const val mariadb_java_client = "org.mariadb.jdbc:mariadb-java-client"
    const val mysqldb_java_client = "mysql:mysql-connector-java"

    const val commons_pool2 = "org.apache.commons:commons-pool2"

    const val hibernate_envers = "org.hibernate:hibernate-envers"
    const val querydsl_jpa = "com.querydsl:querydsl-jpa" // version 을 명시하지 말고, spring boot dependency management 를 따른다
    const val querydsl_apt = "com.querydsl:querydsl-apt::jpa" // specifier 로 ":jpa"를 꼭 붙여줘야 한다

    const val slf4j_api = "org.slf4j:slf4j-api:${Versions.slf4j}"
    const val kotlin_logging = "io.github.microutils:kotlin-logging:${Versions.kotlin_logging}"

    const val logback_classic = "ch.qos.logback:logback-classic:${Versions.logback_classic}"
    const val logstash_logback_encoder =
        "net.logstash.logback:logstash-logback-encoder:${Versions.logstash_logback_encoder}"
    const val micrometer_registry_prometheus = "io.micrometer:micrometer-registry-prometheus"
    const val jackson_datatype_hibernate5 = "com.fasterxml.jackson.datatype:jackson-datatype-hibernate5"

    const val jackson_databind = "com.fasterxml.jackson.core:jackson-databind"
    const val jackson_datatype_jdk8 = "com.fasterxml.jackson.datatype:jackson-datatype-jdk8"
    const val jackson_datatype_jsr310 = "com.fasterxml.jackson.datatype:jackson-datatype-jsr310"
    const val jackson_module_kotlin = "com.fasterxml.jackson.module:jackson-module-kotlin"

    const val caffeine = "com.github.ben-manes.caffeine:caffeine"
    const val apache_commons_imaging = "org.apache.commons:commons-imaging:${Versions.apache_commons_imaging}"

    const val swagger_starter = "io.springfox:springfox-boot-starter:${Versions.swagger}"

    const val elasticsearch = "org.elasticsearch:elasticsearch:${Versions.elasticsearch}"
    const val elasticsearch_client =
        "org.elasticsearch.client:elasticsearch-rest-high-level-client:${Versions.elasticsearch}"

    const val resilience4j_spring_starter = "io.github.resilience4j:resilience4j-spring-boot2:${Versions.resilience4j}"
    const val feign_okhttp = "io.github.openfeign:feign-okhttp:11.2"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val guava = "com.google.guava:guava:${Versions.guava}"

    // aws
    const val aws_java_sdk_s3 = "com.amazonaws:aws-java-sdk-s3:${Versions.aws_sdk}"
    const val aws_java_sdk_core = "com.amazonaws:aws-java-sdk-core:${Versions.aws_sdk}"
    const val aws_java_sdk_sns = "com.amazonaws:aws-java-sdk-sns:${Versions.aws_sdk}"
    const val aws_java_sdk_sqs = "com.amazonaws:aws-java-sdk-sqs:${Versions.aws_sdk}"

    // test
    const val h2_database = "com.h2database:h2"
    const val kotest_runner_junit5 = "io.kotest:kotest-runner-junit5:${Versions.kotest}"
    const val kotest_assertions_core = "io.kotest:kotest-assertions-core:${Versions.kotest}"
    const val kotest_assertions_json = "io.kotest:kotest-assertions-json:${Versions.kotest}"
    const val kotest_property = "io.kotest:kotest-property:${Versions.kotest}"
    const val kotest_extensions_spring = "io.kotest:kotest-extensions-spring:${Versions.kotest_ext_spring}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val ninja_squad_springmockk = "com.ninja-squad:springmockk:${Versions.ninja_squad_springmockk}"

    const val restdocs_mockmvc = "org.springframework.restdocs:spring-restdocs-mockmvc"
    const val restdocs_restassured = "org.springframework.restdocs:spring-restdocs-restassured"
    const val restdocs_restassured_mvc = "io.rest-assured:spring-mock-mvc"
    const val restdocs_restassured_rest = "io.rest-assured:rest-assured"

    const val annotation_asciidoctor_test = "org.springframework.restdocs:spring-restdocs-webtestclient"
    const val annotation_asciidoctor = "org.springframework.restdocs:spring-restdocs-asciidoctor"
}

object Projects {
    const val domain_model = ":domain-model"
    const val domain_service = ":domain-service"
}
