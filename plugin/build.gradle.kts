plugins {
  java
  id("io.papermc.paperweight.userdev") version "2.0.0-beta.24"
  id("com.gradleup.shadow") version "9.0.1"
}

// Paper 26.2 uses the new version scheme (no reobf from 26.1+)
val minecraftVersion = "26.2"
val pluginBaseName = "delphi-papermc"

version = "$minecraftVersion-0.7.2-paper26"

repositories {
  mavenCentral()
  maven("https://libraries.minecraft.net/")
  maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
  implementation(project(":dom"))
  implementation(project(":chimera"))
  implementation(project(":api"))
  implementation(project(":nlayout"))
  implementation(project(":render"))
  implementation(project(":hephaestus"))

  implementation("org.bstats:bstats-bukkit:3.0.2")

  paperweight.paperDevBundle("26.2.build.121-stable")
}

java {
  toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}

tasks {
  jar {
    archiveBaseName.set(pluginBaseName)
  }
  shadowJar {
    archiveBaseName.set(pluginBaseName)
    relocate("org.bstats", "com.juliewoolie.libs.stupidbstatsrelocate")

    val outputDir = System.getenv("DELPHI_OUTPUT_DIR")
    if (outputDir != null) {
      destinationDirectory = file(outputDir)
    }
  }

  processResources {
    filesMatching("versions.yml") {
      expand(mapOf(
        "chimeraVersion" to project(":chimera").version,
        "apiVersion" to project(":api").version,
        "domVersion" to project(":dom").version,
        "jsVersion" to project(":hephaestus").version
      ))
    }

    filesMatching("paper-plugin.yml") {
      expand(mapOf("version" to version))
    }
  }
}
