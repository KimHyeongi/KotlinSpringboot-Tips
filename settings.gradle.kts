pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "KotlinSpringboot-Tips"

include(
    "domain-model",
    "domain-service",
    "app-api",
    "internal-api"
)
