package com.example.database

import com.example.API.students
import org.jetbrains.exposed.sql.Table


object users : Table("user") {
    val id = integer("id").autoIncrement()
    val login = varchar("login", 100)
    val password = varchar("password", 100)
    val studentId = integer("student_id") references students.id

    override val primaryKey = PrimaryKey(id)
}

data class UserWithGroupInfo(
    val id: Int,
    val login: String,
    val studentId: Int,
    val groupId: Int,
    val password: String
)
