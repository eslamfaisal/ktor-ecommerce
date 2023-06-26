val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.8.22"
    id("io.ktor.plugin") version "2.3.1"
    id("com.github.johnrengelman.shadow") version "7.1.1" // available version as of 2021
}

group = "site.bluethunder"
version = "0.0.1"
application {
    mainClass.set("site.bluethunder.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    // authentication
    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jwt:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-gson:$ktor_version")

    implementation("io.ktor:ktor-server-cors:$ktor_version")
    implementation("io.ktor:ktor-server-compression:$ktor_version")
    implementation("io.ktor:ktor-server-status-pages:$ktor_version")
    implementation("io.ktor:ktor-server-freemarker:$ktor_version")
    implementation("io.ktor:ktor-server-call-logging:$ktor_version")

    // exposed ORM library
    implementation("org.flywaydb:flyway-core:9.16.0")
    implementation("org.flywaydb:flyway-mysql:8.4.4")
    implementation("org.jetbrains.exposed:exposed-core:0.40.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.40.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.40.1")
    implementation("org.jetbrains.exposed:exposed-java-time:0.40.1")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:0.40.1")

    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("mysql:mysql-connector-java:8.0.28")


    // password hashing
    implementation("at.favre.lib:bcrypt:0.10.2")
    // date time
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    // mail server
    implementation("org.apache.commons:commons-email:1.5")

    implementation("com.google.api-client:google-api-client:2.2.0")
    implementation("com.google.oauth-client:google-oauth-client:1.34.1")

    // validator
    implementation("org.valiktor:valiktor-core:0.12.0")

    // file extension
    implementation("commons-io:commons-io:2.11.0")

    //swagger
    implementation("dev.forst:ktor-openapi-generator:0.6.1")

}

tasks {
    shadowJar {
        archiveBaseName.set("ktor-ecommerce")
        archiveVersion.set("0.0.1")
        manifest {
            attributes["Main-Class"] = "site.bluethunder.ApplicationKt" // replace with your main class
        }
    }
}