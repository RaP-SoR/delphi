plugins {
  java
}

group = "com.juliewoolie"

repositories {
  mavenCentral()
}

subprojects {
  apply(plugin = "java")

  group = rootProject.group

  repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
  }

  dependencies {
    // Paper 26.2 — Minecraft versioning switched from 1.21.x to 26.x
    compileOnly("io.papermc.paper:paper-api:26.2.build.121-stable")
    // Paper 26 no longer exposes javax.annotation; Delphi API still uses @Nullable.
    compileOnly("com.google.code.findbugs:jsr305:3.0.2")

    // Test dependencies
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.3")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.3")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("com.google.guava:guava:33.2.1-jre")
    testImplementation("org.slf4j:slf4j-api:2.0.13")
    testImplementation("org.slf4j:slf4j-simple:2.0.13")
    testImplementation("it.unimi.dsi:fastutil:8.5.13")
    testImplementation("io.papermc.paper:paper-api:26.2.build.121-stable")
    testImplementation("com.google.code.findbugs:jsr305:3.0.2")

    if (name != "api" && name != "test-plugin") {
      val lombok = "org.projectlombok:lombok:1.18.38"
      compileOnly(lombok)
      annotationProcessor(lombok)
      testCompileOnly(lombok)
      testAnnotationProcessor(lombok)
    }
  }

  tasks {
    test {
      useJUnitPlatform()
      systemProperty("org.slf4j.simpleLogger.defaultLogLevel", "trace")
      systemProperty("joml.format", "false")
    }
    compileJava {
      options.release = 25
      options.encoding = Charsets.UTF_8.name()
    }
    javadoc {
      options.encoding = Charsets.UTF_8.name()
    }
  }

  java {

  }
}
