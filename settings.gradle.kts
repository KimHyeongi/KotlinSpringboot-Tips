pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

rootProject.name = "KotlinSpringboot-Tips"

include(
    "commons",
    "domain-infra",
    "domain-persist",
    "domain-rest",
    "app-api",
    "internal-api"
)
