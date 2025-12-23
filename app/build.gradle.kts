import org.gradle.kotlin.dsl.implementation

plugins {
    id("java")
    id("com.github.ben-manes.versions") version "0.51.0"
    application
    checkstyle
    id("org.sonarqube") version "7.2.1.6560"
    jacoco
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
application {
    mainClass = "hexlet.code.App"
}
sonar {
    properties {
        property("sonar.projectKey", "AlexFrankiv_java-project-71")
        property("sonar.organization", "alexfrankiv")
    }
}
checkstyle {
    toolVersion = "10.12.4"
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation ("info.picocli:picocli:4.7.7")
    annotationProcessor("info.picocli:picocli-codegen:4.7.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.19.2")
}

tasks.test {
    useJUnitPlatform()
}
tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}