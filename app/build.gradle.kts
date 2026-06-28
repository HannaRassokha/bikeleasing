plugins {
    kotlin("jvm") version "2.4.0"
    kotlin("plugin.allopen") version "2.4.0"
    id("net.serenity-bdd.serenity-gradle-plugin") version "5.3.9"
}

allOpen {
    annotation("com.bikeleasing.api.Open")
}

repositories {
    mavenCentral()
}

val serenityVersion = "5.3.10"
val junitVersion = "6.0.3"
val junitPlatformEngine = "7.33.0"

dependencies {
    testImplementation("net.serenity-bdd:serenity-core:$serenityVersion")
    testImplementation("net.serenity-bdd:serenity-junit5:$serenityVersion")
    testImplementation("net.serenity-bdd:serenity-screenplay:$serenityVersion")
    testImplementation("net.serenity-bdd:serenity-screenplay-webdriver:$serenityVersion")
    testImplementation("net.serenity-bdd:serenity-rest-assured:$serenityVersion")
    testImplementation("net.serenity-bdd:serenity-ensure:$serenityVersion")
    testImplementation("net.serenity-bdd:serenity-cucumber:$serenityVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.platform:junit-platform-suite:$junitVersion")
    testRuntimeOnly("io.cucumber:cucumber-junit-platform-engine:$junitPlatformEngine")

    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.2")
    testImplementation("io.github.microutils:kotlin-logging-jvm:3.0.5")

    testRuntimeOnly("org.slf4j:slf4j-simple:2.0.12")
}

kotlin{
    jvmToolchain(21)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    systemProperty("webdriver.driver", "chrome")
    System.getProperty("tags")?.let { systemProperty("cucumber.filter.tags", it) }
    testLogging {
        showStandardStreams = true
    }
}

tasks.test {
    finalizedBy("aggregate")
}

tasks.register<Test>("apiTest") {
    description = "Runs the API tests (com.bikeleasing.api)."
    useJUnitPlatform()
    systemProperty("webdriver.driver", "chrome")
    filter { includeTestsMatching("com.bikeleasing.api.*") }
}

tasks.register<Test>("webTest") {
    description = "Runs the BDD Cucumber tests (com.bikeleasing.web)."
    useJUnitPlatform()
    systemProperty("webdriver.driver", "chrome")
    filter { includeTestsMatching("com.bikeleasing.web.*") }
}

serenity {
    reports = listOf("single-page-html")
}
