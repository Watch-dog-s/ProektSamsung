package com.example

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.sql.Connection
import java.sql.DriverManager









@Serializable
data class Test1(val name:String,val age:Int)




fun Application.configureRouting() {
    routing {
        get("/") {
            //call.respondText("Hello World!")
            call.respond(

             listOf(
                    Test1("Monkey1",12),
                   Test1("Monkey2",12)
           )

            )

        }
    }
}
