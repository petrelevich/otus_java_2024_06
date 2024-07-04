plugins {
    id ("java-library")
}

dependencies {
    implementation("ch.qos.logback:logback-classic")

    api ("com.google.guava:guava") // случай 1
    // implementation ("com.google.guava:guava") //случай 2
}

