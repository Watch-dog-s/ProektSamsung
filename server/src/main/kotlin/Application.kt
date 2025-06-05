package com.example

import com.example.API.*
import com.example.authorization.LoginRouting
import com.example.database.DatabaseConfiguration
import com.example.database.UserDaoImpl
import com.example.registration.RegisterRouting
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*



fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }

    DatabaseConfiguration.init()

    val userDao = UserDaoImpl()
    val scheduleDao = ScheduleDaoImpl(DatabaseConfiguration.db)
    val markDao = MarkDaoImpl(DatabaseConfiguration.db)



    routing {
        LoginRouting(userDao).apply { configureLoginRouting() }
        RegisterRouting(userDao).apply { configureRegisterRouting() }
        scheduleRouting(scheduleDao)
        markRouting(markDao)
    }
}





