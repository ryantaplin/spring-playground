plugins {
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.flywaydb.flyway") version "6.4.3"
}

group = "github.ryantaplin"
version = "DEV-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    /** Prod Dependencies **/
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")

    // database
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-oracle")
    runtimeOnly("com.oracle.database.jdbc:ojdbc11")

    /** Test Dependencies **/
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // in-mem database
    testImplementation("com.h2database:h2:2.3.232")

    // test containers
    testImplementation("org.testcontainers:oracle-free")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:testcontainers")

}

tasks.withType<Test> {
    useJUnitPlatform()
}