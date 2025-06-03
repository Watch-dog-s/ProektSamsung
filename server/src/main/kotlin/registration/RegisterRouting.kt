package com.example.registration

import com.example.authorization.LoginReceiveRemote
import com.example.authorization.LoginResponseRemote
import com.example.database.UserDaoImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*



class RegisterRouting(
    private val userDao: UserDaoImpl
) {
    fun Application.configureRegisterRouting() {
        routing {
            post("/register") {

                val receive = call.receive<RegisterReceiveRemote>()

                if (userDao.getUserByEmail(receive.login)!= null){
                    return@post call.respond(HttpStatusCode.Conflict,"Логин уже зарегестрирован")
                }

                try{
                    val user = userDao.createUser(receive.login,receive.password,receive.type)

                    val token = UUID.randomUUID().toString()

                    call.respond(RegisterResponseRemote(token))
                }
                catch(e : Exception){
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        "Ошибка регистрации: ${e.localizedMessage}"
                    )
                }

            }
        }
    }
}