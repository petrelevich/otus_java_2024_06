plugins {
    id("java")
    id("org.springframework.boot")
    id("com.google.cloud.tools.jib")
    id("fr.brouillard.oss.gradle.jgitver")
}


apply(plugin = "com.google.cloud.tools.jib")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.redisson:redisson")
}


tasks {
    build {
        dependsOn(spotlessApply)
    }
}