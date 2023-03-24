import org.jetbrains.compose.desktop.application.dsl.TargetFormat

@Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.compose)
}

group = "nolambda.techstack"
version = "1.0-SNAPSHOT"

compose.desktop {
    application {
        mainClass = "nolambda.techstack.desktop.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "desktopApp"
            packageVersion = "1.0.0"
        }
    }
}

dependencies {
    implementation(project(":common"))
    implementation(compose.desktop.currentOs)
}
