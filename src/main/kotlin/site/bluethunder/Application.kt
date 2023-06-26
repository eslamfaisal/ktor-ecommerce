package site.bluethunder

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.LoggerFactory
import site.bluethunder.databasehelper.DatabaseFactory
import site.bluethunder.plugins.configureAuthentication
import site.bluethunder.plugins.configureBasic
import site.bluethunder.plugins.configureRouting
import site.bluethunder.plugins.configureStatusPage

fun main() {
    //val environment = System.getenv("KTOR_ENVIRONMENT") ?: "development"
    val configName = "application.conf"
    val appEngineEnv = applicationEngineEnvironment {
        config = HoconApplicationConfig(ConfigFactory.load(configName))
        log = LoggerFactory.getLogger("ktor.application")
        developmentMode = false
        module {
            DatabaseFactory.init()
            configureBasic()
            configureStatusPage()
            configureAuthentication()
            configureRouting()
        }
        connector {
            host = config.property("ktor.deployment.host").getString()
            port = config.property("ktor.deployment.port").getString().toInt()
        }
    }
    embeddedServer(Netty, appEngineEnv).start(wait = true)
}
