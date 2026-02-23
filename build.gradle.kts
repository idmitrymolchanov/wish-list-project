plugins {
    java
    id("com.diffplug.spotless") version "6.8.0"
    id("org.springframework.boot") version "3.5.6"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.openapi.generator") version "7.17.0"
}

group = "ru.newgor"
version = "2.0.0-SNAPSHOT"
description = "Wishlist project"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

object Version {
    const val jwt = "4.4.0"
    const val mockito = "5.12.0"
    const val instancio = "4.8.0"
    const val mapstruct = "1.6.3"
}

dependencies {
    // common
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

    implementation("org.mapstruct:mapstruct:${Version.mapstruct}")
    annotationProcessor("org.mapstruct:mapstruct-processor:${Version.mapstruct}")

    implementation("org.springframework.boot:spring-boot-starter-security")
//    implementation("org.mapstruct:mapstruct:${Version.mapstruct}")
//    annotationProcessor("org.mapstruct:mapstruct-processor:${Version.mapstruct}")
//    compileOnly("org.projectlombok:lombok")
//    annotationProcessor("org.projectlombok:lombok")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("io.jsonwebtoken:jjwt:0.13.0")

    //db
    runtimeOnly("com.h2database:h2")
    implementation("org.liquibase:liquibase-core")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    //test
    testImplementation("org.mockito:mockito-core:${Version.mockito}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.instancio:instancio-junit:${Version.instancio}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
//
//spotless {
//    java {
//        googleJavaFormat("1.22.0")
//            .aosp()
//    }
//}

openApiGenerate {
    generatorName.set("spring")
    inputSpec.set("$rootDir/src/main/resources/openapi/swagger-contract.yaml")
    outputDir.set("$rootDir/build/generated/openapi")
    apiPackage.set("ru.newgor.wishlist.adapter.input.api")
    modelPackage.set("ru.newgor.wishlist.adapter.input.api.dto")
    configOptions.set(
        mapOf(
            "interfaceOnly" to "true",
            "dateLibrary" to "java8",
            "skipDefaultInterface" to "true",
            "legacyDiscriminatorBehavior" to "true",
            "useTags" to "true",
            "swaggerAnnotations" to "true",
            "useFeignClientUrl" to "true",
            "useSpringBoot3" to "true",
            "documentationProvider" to "none",
            "hideGenerationTimestamp" to "true",
            "useResponseEntity" to "false"
        )
    )
}

tasks.named("compileJava") {
    dependsOn("openApiGenerate")
}

sourceSets {
    main {
        java {
            srcDir("$buildDir/generated/openapi/src/main/java")
        }
    }
}