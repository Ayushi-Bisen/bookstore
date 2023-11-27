plugins {
	java
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.flywaydb.flyway") version "9.22.3"
	id("jacoco")
	id("jacoco-report-aggregation")
}

group = "com.project"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("org.flywaydb:flyway-core:9.22.3")
	implementation("org.postgresql:postgresql:42.7.0")
	testImplementation(platform("org.junit:junit-bom:5.9.1"))
	testImplementation("org.junit.jupiter:junit-jupiter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.jacocoTestCoverageVerification {
	dependsOn(tasks.test)
	violationRules {
		rule {
			limit {
				counter = "LINE"
				value = "COVEREDRATIO"
				minimum = "0.8".toBigDecimal()
			}
		}
	}
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)
	reports {
		xml.required = true
		csv.required = true
		html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
	}
}
