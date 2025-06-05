package com.example.API

import io.ktor.http.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.response.*


fun Route.scheduleRouting(scheduleDao: ScheduleDaoImpl) {

    get("/schedule/{groupId}") {
        val groupId = call.parameters["groupId"]?.toIntOrNull()

        if (groupId == null) {
            call.respond(HttpStatusCode.BadRequest, "Некорректный groupId")
            return@get
        }

        try {
            val result = scheduleDao.getScheduleByGroup(groupId)
            call.respond(HttpStatusCode.OK, result)
        } catch (e: NotFoundException) {
            call.respond(HttpStatusCode.NotFound, e.message ?: "Группа не найдена")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, "Ошибка сервера: ${e.localizedMessage}")
        }
    }

    post("/schedule") {
        try {
            val request = call.receive<ScheduleRequest>()
            val id = scheduleDao.insertSchedule(request)
            call.respond(HttpStatusCode.Created, mapOf("id" to id))
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, "Ошибка при добавлении расписания: ${e.localizedMessage}")
        }
    }

    get("/schedules") {
        try {
            val schedules = scheduleDao.getAllSchedules()
            call.respond(HttpStatusCode.OK, schedules)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, "Ошибка при получении расписаний")
        }
    }
}
