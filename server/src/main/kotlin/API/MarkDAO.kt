package com.example.API

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*




interface MarkDao {
    fun getMarkById(id: Int): MarkResponse?
    fun getMarksByStudentId(studentId: Int): List<MarkResponse>
}





class MarkDaoImpl(private val db: Database) : MarkDao {
    override fun getMarkById(id: Int): MarkResponse? = transaction(db) {
        marks.select { marks.id eq id }.singleOrNull()?.let {
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


    override fun getMarksByStudentId(studentId: Int): List<MarkResponse> =
        transaction(db) {
            marks.select { marks.studentId eq studentId }
                .map {
                    MarkResponse(
                        id = it[marks.id],
                        studentId = it[marks.studentId],
                        subject = it[marks.subject],
                        mark = it[marks.mark],
                        comment = it[marks.comment] ?: "",
                        teacher = it[marks.teacher] ?: "",
                        type = it[marks.type].name
                    )
                }
        }
}