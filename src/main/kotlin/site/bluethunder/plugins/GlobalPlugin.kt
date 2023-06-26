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
        anyHost()  // Allow requests from any host
        allowCredentials = true
        allowNonSimpleContentTypes = true  // Allows all headers, including non-simple ones
        allowHeader(HttpHeaders.AccessControlAllowHeaders)  // Allows setting of any headers
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
    }

//
//    install(CORS) {
//        allowMethod(HttpMethod.Options)
//        allowMethod(HttpMethod.Post)
//        allowMethod(HttpMethod.Get)
//        allowHeader(HttpHeaders.AccessControlAllowOrigin)
//        allowHeader(HttpHeaders.ContentType)
//        allowHeader(HttpHeaders.Authorization)
//        HttpMethod.DefaultMethods.forEach { allowHeader(it.value) }
//        allowHeaders { true }
//        allowCredentials = true
//        anyHost()
//        allowHost("0.0.0.0:8080", schemes = listOf("https", "http"))
//        allowHost("free.bluethunder.site", schemes = listOf("https", "http"))
//    }

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
            title = "Free Ktor Ecommerce Apis"
            description = "Api Documentation for Ktor Ecommerce App"
            contact {
                name = "Eslam Faisal"
                email = "eslamfaisal423@gmail.com"
            }
        }
        // describe the server, add as many as you want
//        server("http://0.0.0.0:8080/") {
//            description = "Ktor for production server"
//        }
        server("https://free.bluethunder.site/") {
            description = "Ktor for production server"
        }
//        server("http://free.bluethunder.site/") {
//            description = "Ktor for production server"
//        }

        //optional custom schema object name
        replaceModule(DefaultSchemaNamer, object : SchemaNamer {
            val regex = Regex("[A-Za-z0-9_.]+")
            override fun get(type: KType): String {
                return type.toString().replace(regex) { it.value.split(".").last() }.replace(Regex(">|<|, "), "_")
            }
        })
    }
}