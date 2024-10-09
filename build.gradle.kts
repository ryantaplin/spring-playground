allprojects {
    group = "github.ryantaplin"
    version = System.getenv("VERSION") ?: "DEV-SNAPSHOT"

    repositories {
        mavenLocal()
    }
}

subprojects {
    apply(plugin = "java")
}