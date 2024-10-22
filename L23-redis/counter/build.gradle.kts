plugins {
    id("org.springframework.boot")
    id("com.google.cloud.tools.jib")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.redisson:redisson")
}

jib {
    container {
        creationTime.set("USE_CURRENT_TIMESTAMP")
    }
    from {
        image = "bellsoft/liberica-openjdk-alpine-musl:21.0.1"
    }

    to {
        image = "localrun/counter"
        tags = setOf(project.version.toString())
    }
}

tasks {
    build {
        dependsOn(spotlessApply)
    }
}
