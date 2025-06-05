package com.example.authorization



import com.example.database.UserDao
import com.example.database.UserDaoImpl
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.request.*
import java.util.*


class LoginRouting(
    private val userDao: UserDao
) {
    fun Route.configureLoginRouting() {
        post("/login") {
            val receive = call.receive<LoginReceiveRemote>()
            val user = userDao.getUserByEmail(receive.login)

            if (user == null) {
                call.respond(HttpStatusCode.Unauthorized, "Юзер не найден")
                return@post
            }

            if (user.password != receive.password) {
                call.respond(HttpStatusCode.Unauthorized, "Неправильный пароль")
                return@post
            }

            val token = UUID.randomUUID().toString()
            call.respond(
                LoginResponseRemote(
                    token = token,
                    userId = user.id,
                    groupId = user.groupId
                )
            )
        }
    }
}
