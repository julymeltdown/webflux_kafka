import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"
}

group = "com.metlife"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.springframework.security:spring-security-test")

	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.3")

	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")

	//openai
	implementation("com.aallam.openai:openai-client:3.7.0")
	runtimeOnly("io.ktor:ktor-client-okhttp")

	implementation("org.reactivestreams:reactive-streams:1.0.3")
	implementation("io.projectreactor:reactor-core:3.4.10")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.0")

	// Ktor HTTP 클라이언트 코어 라이브러리
	implementation("io.ktor:ktor-client-core-jvm:2.3.2")
	implementation(platform("com.aallam.openai:openai-client-bom:3.7.0"))

	//kafka
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("io.projectreactor.kafka:reactor-kafka")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "21"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
