plugins {
    id ("com.github.johnrengelman.shadow")
    id ("maven-publish")
}

dependencies {
    implementation ("org.springframework.boot:spring-boot-starter") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    }
    implementation ("org.springframework.boot:spring-boot-configuration-processor")
    implementation ("ru.otus:messager:2.1")

    compileOnly ("org.slf4j:slf4j-api")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "ru.otus"
            artifactId = "messager-starter"
            version = "2.1"
            from(components["java"])
        }
    }
}

tasks {
    build {
        dependsOn(publishToMavenLocal)
    }
}
