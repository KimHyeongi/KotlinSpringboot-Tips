pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "KotlinSpringboot-Tips"

include(
    "domain-model",
    "domain-service",
    "batch",
    "app-api",
    "internal-api"
)
