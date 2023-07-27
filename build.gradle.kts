import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	groovy
	id("org.springframework.boot") version "3.1.2"
	id("io.spring.dependency-management") version "1.1.2"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
}

group = "io.brandon"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-graphql")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("io.arrow-kt:arrow-core:1.2.0")
	implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
	implementation("org.codehaus.groovy:groovy-all:3.0.8")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.spockframework:spock-core:2.0-groovy-3.0")
	testImplementation("org.springframework.graphql:spring-graphql-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
