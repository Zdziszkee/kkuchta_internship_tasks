plugins {
    id("java")
}

group = "com.griddynamics.internship"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "com.griddynamics.internship.LetterStatistics"
    }
}
tasks.getByName<Test>("test") {
    useJUnitPlatform()
}