plugins {
    id("fabric-loom") version "1.+"
}

val modVersion: String by extra
val minecraftVersion: String by extra
val loaderVersion: String by extra
val reflectionRewriterVersion: String by extra

version = modVersion
group = "dev.tonimatas"

base.archivesName.set("mixedpaper")

loom.accessWidenerPath.set(file("src/main/resources/mixedpaper.accesswidener"))

sourceSets {
    create("paper")
    create("log4jPlugins")
    all {
        compileClasspath += main.get().compileClasspath
        runtimeClasspath += main.get().runtimeClasspath
    }
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public")
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:$loaderVersion")

    // Paper
    implementation("ca.spottedleaf:concurrentutil:0.0.3")
    implementation("org.jline:jline-terminal-ffm:3.27.1") // use ffm on java 22+
    implementation("org.jline:jline-terminal-jni:3.27.1") // fall back to jni on java 21
    implementation("net.minecrell:terminalconsoleappender:1.3.0")
    implementation("io.papermc.adventure:adventure-text-serializer-ansi:4.21.0-mc1215-SNAPSHOT") // Keep in sync with adventureVersion from Paper-API build file // FIXME back to release

    implementation("com.velocitypowered:velocity-native:3.4.0-SNAPSHOT") {
        isTransitive = false
    }

    implementation("io.netty:netty-codec-haproxy:4.1.118.Final") // Add support for proxy protocol
    implementation("org.apache.logging.log4j:log4j-iostreams:2.24.1")
    implementation("org.spongepowered:configurate-yaml:4.2.0-20250225.064233-199")
    implementation("org.spongepowered:configurate-core:4.2.0-20250225.064233-204")

    runtimeOnly("commons-lang:commons-lang:2.6")
    runtimeOnly("org.xerial:sqlite-jdbc:3.49.1.0")
    runtimeOnly("com.mysql:mysql-connector-j:9.2.0")
    runtimeOnly("com.lmax:disruptor:3.4.4")
    implementation("com.googlecode.json-simple:json-simple:1.1.1") { // change to runtimeOnly once Timings is removed
        isTransitive = false // includes junit
    }

    runtimeOnly("org.apache.maven:maven-resolver-provider:3.9.6")
    runtimeOnly("org.apache.maven.resolver:maven-resolver-connector-basic:1.9.18")
    runtimeOnly("org.apache.maven.resolver:maven-resolver-transport-http:1.9.18")

    implementation("net.neoforged:srgutils:1.0.9") // Mappings handling
    implementation("net.neoforged:AutoRenamingTool:2.0.3") // Remap plugins

    implementation("io.papermc.paper:paper-api:1.21.5-R0.1-SNAPSHOT")
    implementation("io.papermc:reflection-rewriter:$reflectionRewriterVersion")
    implementation("io.papermc:reflection-rewriter-runtime:$reflectionRewriterVersion")
    implementation("io.papermc:reflection-rewriter-proxy-generator:$reflectionRewriterVersion")

    // Spark
    implementation("me.lucko:spark-api:0.1-20240720.200737-2")
    implementation("me.lucko:spark-paper:1.10.133-20250413.112336-1") // TODO: Change it to the Fabric version

}