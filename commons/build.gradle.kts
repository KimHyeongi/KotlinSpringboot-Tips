plugins {
    id("org.asciidoctor.convert") version "1.5.9.2"
}

dependencies {
    testImplementation(Libraries.restdocs_mockmvc)
    testImplementation(Libraries.restdocs_restassured)

    asciidoctor(Libraries.annotation_asciidoctor)
}
