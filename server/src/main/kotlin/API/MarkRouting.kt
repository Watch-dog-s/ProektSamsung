package com.example.API


import com.example.database.DatabaseConfiguration.db
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

fun Route.markRouting(markDao: MarkDaoImpl) {


    get("/marks/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()

        if (id == null) {
            call.respond(HttpStatusCode.BadRequest, "Неправильное ID")
            return@get
        }

        val mark = markDao.getMarkById(id)
        if (mark == null) {
            call.respond(HttpStatusCode.NotFound, "Оценка не найдена")
        } else {
            call.respond(HttpStatusCode.OK, mark)
        }
    }


    get("/students/{id}/marks") {
        val studentId = call.parameters["id"]?.toIntOrNull()

        if (studentId == null) {
            call.respond(HttpStatusCode.BadRequest, "Неправильное id школьника")
            return@get
        }

        val studentMarks = transaction(db) {
            marks.select { marks.studentId eq studentId }
                .map {
                    MarkResponse(
                        id = it[marks.id],
                        studentId = it[marks.studentId],
                        subject = it[marks.subject],
                        mark = it[marks.mark],
                        comment = it[marks.comment],
                        teacher = it[marks.teacher],
                        type = it[marks.type].name
                    )
                }
        }

        if (studentMarks.isEmpty()) {
            call.respond(HttpStatusCode.NotFound, "У школьника нет оценок")
        } else {
            call.respond(HttpStatusCode.OK, studentMarks)
        }
    }


}