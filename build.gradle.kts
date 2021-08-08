import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.2"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.21"
	kotlin("plugin.spring") version "1.4.21"
}

group = "com.gogo.steelbotrun"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_15

repositories {
	mavenCentral()
	maven("https://jitpack.io")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	implementation("com.github.RealCerus:JGif:1.0.0")
	implementation("org.apache.httpcomponents:httpclient:4.3.4")
	implementation("org.apache.httpcomponents:httpmime:4.3.1")
	implementation("com.google.code.gson:gson:2.8.6")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("io.kotest:kotest-runner-junit5:4.3.2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
