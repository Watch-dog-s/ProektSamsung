package com.example.API

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

enum class Type { HomeWork, Answer, Test, ControlWork }

object students : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 100)
    val groupId = integer("group_id") references groups.id
    override val primaryKey = PrimaryKey(id)
}


object groups : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 50)
    override val primaryKey = PrimaryKey(id)
}


object marks : Table() {
    val id = integer("id").autoIncrement()
    val studentId = integer("student_id") references students.id
    val subject = varchar("subject", 100)
    val mark = integer("mark")
    val comment = varchar("comment", 255).nullable()
    val teacher = varchar("teacher", 100).nullable()
    val type = enumerationByName("type", 20, Type::class)
    override val primaryKey = PrimaryKey(id)
}



object schedules : Table() {
    val id = integer("id").autoIncrement()
    val groupId = integer("group_id") references groups.id
    val subject = varchar("subject", 100)
    val room = varchar("room", 20)
    val teacher = varchar("teacher", 100)
    val time = varchar("time", 50)
    val dayOfWeek = enumerationByName("day_of_week", 15, DayOfWeek::class)
    override val primaryKey = PrimaryKey(id)
}

@Serializable
enum class DayOfWeek {
    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
}



@Serializable
data class ScheduleRequest(
    val groupId: Int,
    val subject: String,
    val room: String,
    val teacher: String,
    val time: String,
    val dayOfWeek: String
)


@Serializable
data class ScheduleResponse(
    val id: Int,
    val groupId: Int,
    val subject: String,
    val room: String,
    val teacher: String,
    val time: String,
    val dayOfWeek: String
)


@Serializable
data class MarkResponse(
    val id: Int,
    val studentId: Int,
    val subject: String,
    val mark: Int,
    val comment: String?,
    val teacher: String?,
    val type: String
)










