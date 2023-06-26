package site.bluethunder.plugins

import com.papsign.ktor.openapigen.OpenAPIGen
import com.papsign.ktor.openapigen.schema.namer.DefaultSchemaNamer
import com.papsign.ktor.openapigen.schema.namer.SchemaNamer
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import kotlin.reflect.KType


fun Application.configureBasic() {
//    install(Compression)
//    install(CORS) {
//        anyHost()
//    }
    install(CORS) {
        HttpMethod.DefaultMethods.forEach { allowHeader(it.value) }
        allowHeaders { true }
        allowCredentials = true
        anyHost()
        allowHost("0.0.0.0:8080", schemes = listOf("https","http"))
        allowHost("free.bluethunder.site", schemes = listOf("https","http"))
    }

    install(CallLogging)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
            // serializeNulls()
        }
    }
    // Open api configuration
    install(OpenAPIGen) {
        // basic info
        info {
            version = "0.0.1"
            title = "Ktor Ecommerce"
            description = "Api Documentation for Ktor Ecommerce App"
            contact {
                name = "PLabs Corporation"
                email = "eslamfaisal423@gmail.com"
            }
        }
        // describe the server, add as many as you want
        server("http://0.0.0.0:8080/") {
//        server("https://free.bluethunder.site/") {
            description = "Ktor for production server2"
        }
        //optional custom schema object name
        replaceModule(DefaultSchemaNamer, object : SchemaNamer {
            val regex = Regex("[A-Za-z0-9_.]+")
            override fun get(type: KType): String {
                return type.toString().replace(regex) { it.value.split(".").last() }.replace(Regex(">|<|, "), "_")
            }
        })
    }
}