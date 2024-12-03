rootProject.name = "otusJava"
include("L01-gradle")

include("L02-gradle2")
include("L02-gradle2-libApi")
include("L02-gradle2-libApiUse")
include("L02-logging")

include("L03-qa")
include("L04-generics")
include("L05-collections")
include("L06-annotations")
include("L08-gc:demo")
include("L08-gc:homework")
include("L09-docker")
include("L10-byteCodes")
include("L11-java8")

include("L12-solid")
include("L13-creationalPatterns")
include("L14-behavioralPatterns")
include("L15-structuralPatterns:demo")
include("L15-structuralPatterns:homework")

include("L16-io:demo")
include("L16-io:homework")
include("L17-nio")

include("L18-jdbc:demo")
include("L18-jdbc:homework")
include("L19-rdbms")

include("L20-hibernate")

include("L21-jpql:class-demo")
include("L21-jpql:homework-template")

include("L22-cache")
include("L23-redis:counter")
include("L23-redis:data-source")
include("L23-redis:data-transformer")
include("L23-redis:data-listener")

include ("L24-webServer")

include ("L25-di:class-demo")
include ("L25-di:homework-template")

include ("L26-springBootMvc")

include ("L27-websocket:websocket")
include ("L27-websocket:messager")
include ("L27-websocket:messager-starter")
include ("L27-websocket:application")

include ("L28-springDataJdbc")

include ("L29-threads")
include ("L30-JMM")
include ("L31-executors")
include ("L32-virtualThreads:base")
include ("L32-virtualThreads:springBoot")

include ("L33-concurrentCollections:ConcurrentCollections")
include ("L33-concurrentCollections:QueueDemo")

include ("L34-multiprocess:processes-demo")
include ("L34-multiprocess:sockets-demo")
include ("L34-multiprocess:rmi-demo")
include ("L34-multiprocess:grpc-demo")

include ("L35-rabbitMQ:allServicesModels")
include ("L35-rabbitMQ:approvalService")
include ("L35-rabbitMQ:mainService")

include ("L36-NIO")
include ("L37-netty")

include ("L38-webflux:source")
include ("L38-webflux:processor")
include ("L38-webflux:client")
include ("L38-webflux-chat:client-service")
include ("L38-webflux-chat:datastore-service")

include ("L39-kafka:consumer")
include ("L39-kafka:producer")

pluginManagement {
    val jgitver: String by settings
    val dependencyManagement: String by settings
    val springframeworkBoot: String by settings
    val johnrengelmanShadow: String by settings
    val jib: String by settings
    val protobufVer: String by settings
    val sonarlint: String by settings
    val spotless: String by settings

    plugins {
        id("fr.brouillard.oss.gradle.jgitver") version jgitver
        id("io.spring.dependency-management") version dependencyManagement
        id("org.springframework.boot") version springframeworkBoot
        id("com.github.johnrengelman.shadow") version johnrengelmanShadow
        id("com.google.cloud.tools.jib") version jib
        id("com.google.protobuf") version protobufVer
        id("name.remal.sonarlint") version sonarlint
        id("com.diffplug.spotless") version spotless
    }
}
