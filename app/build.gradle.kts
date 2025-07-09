plugins {
    kotlin("jvm") version "1.9.10"
    id("net.serenity-bdd.serenity-gradle-plugin") version "3.9.8"

}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("net.serenity-bdd:serenity-core:3.9.8")
    implementation("net.serenity-bdd:serenity-junit5:3.9.8")
    implementation("net.serenity-bdd:serenity-screenplay:3.9.8")
    implementation("net.serenity-bdd:serenity-screenplay-webdriver:3.9.8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
    implementation("org.seleniumhq.selenium:selenium-java:4.20.0")
    implementation("org.seleniumhq.selenium:selenium-remote-driver:4.20.0")
    implementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    implementation("org.slf4j:slf4j-simple:2.0.12")
    implementation("net.serenity-bdd:serenity-rest-assured:3.9.8")
    implementation("io.rest-assured:rest-assured:5.4.0")
    implementation("org.assertj:assertj-core:3.25.1")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    systemProperty("webdriver.driver", "chrome")
}

serenity {
    reports = listOf("single-page-html")
}
