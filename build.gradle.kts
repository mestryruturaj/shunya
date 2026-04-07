plugins {
	java
	id("org.springframework.boot") version "4.0.5"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.openapi.generator") version "7.21.0"
}

group = "io.two.bit.saint"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// 1. Spring Boot Starters
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	runtimeOnly("com.mysql:mysql-connector-j")

	// 2. OpenAPI & Swagger Support
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.5")
	implementation("org.openapitools:jackson-databind-nullable:0.2.6")
	implementation("io.swagger.core.v3:swagger-annotations:2.2.20")

	// 3. Lombok (Core & Processor)
	compileOnly("org.projectlombok:lombok:1.18.30")
	annotationProcessor("org.projectlombok:lombok:1.18.30")

	// 4. The "Bridge" Binding (MUST come after Lombok and before MapStruct)
	annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

	// 5. MapStruct (Core & Processor)
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

	// 6. Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

openApiGenerate {
	generatorName.set("spring")
	inputSpec.set("$projectDir/src/main/resources/openapi/shunya-spec.yaml")
	outputDir.set(layout.buildDirectory.dir("generated").get().asFile.absolutePath)
	configOptions.set(mapOf(
		"interfaceOnly" to "false",
		"delegatePattern" to "true",
		"delegatePackage" to "io.two.bit.saint.shunya.delegate",
		"skipDefaultInterface" to "false",
		"useSpringBoot3" to "true",
		"useTags" to "true",
		"ResponseEntity" to "true"
	))
}

// Add the generated code to your project's source set
sourceSets {
	main {
		java {
			// Use this exact syntax to ensure the path is resolved correctly
			srcDir(layout.buildDirectory.dir("generated/src/main/java").get().asFile)
		}
	}
}

// Ensure code is generated before compiling
tasks.compileJava {
	dependsOn(tasks.openApiGenerate)
	options.annotationProcessorPath = configurations.annotationProcessor.get()
}

springBoot {
	mainClass.set("io.two.bit.saint.shunya.ShunyaApplication")
}

