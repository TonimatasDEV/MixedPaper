plugins {
    id("fabric-loom") version "1.+"
}

val modVersion: String by extra
val minecraftVersion: String by extra
val loaderVersion: String by extra

version = modVersion
group = "dev.tonimatas"

base.archivesName.set("mixedpaper")

loom.accessWidenerPath.set(file("src/main/resources/mixedpaper.accesswidener"))

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:$loaderVersion")
}
