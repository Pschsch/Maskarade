@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven { //internal use. Can be safely removed to run locally
        fun getEnv(name : String): String {
            return try {
                System.getenv(name) ?: java.util.Properties().let {
                    it.load(gradle.gradleUserHomeDir.listFiles()!!.first { n -> n.name == "artifactory.properties" }.inputStream())
                    it.getProperty(name)
                }
            } catch(e : Exception) {
                throw RuntimeException("Failed to retrieve artifactory.properties", e)
            }
        }
            val path : String = getEnv("ARTIFACTORY_REPOSITORY_URL")
            val username : String = getEnv("ARTIFACTORY_PUBLISHER_NAME")
            val password : String = getEnv("ARTIFACTORY_PUBLISHER_PASS")
            url = uri(path)
            isAllowInsecureProtocol = true
            credentials {
                this.username = username
                this.password = password
            }
        }
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Maskarade"
include(":maskarade-core")
include(":maskarade-compose")
includeBuild("convention-plugins")