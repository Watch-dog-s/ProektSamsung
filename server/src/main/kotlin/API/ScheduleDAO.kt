package com.example.API

import io.ktor.server.plugins.*
import org.jetbrains.annotations.Async
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import org.jetbrains.exposed.sql.*
import java.util.UUID


interface ScheduleDao {
    fun getScheduleByGroup(groupId: Int): List<ScheduleResponse>
    fun insertSchedule(schedule: ScheduleRequest): Int
    fun getAllSchedules(): List<ScheduleResponse>
}




class ScheduleDaoImpl(private val db: Database) : ScheduleDao {

    override fun getScheduleByGroup(groupId: Int): List<ScheduleResponse> = transaction(db) {
        if (groups.select { groups.id eq groupId }.empty()) {
            throw NotFoundException("Группа не найдена")
        }
        schedules.select { schedules.groupId eq groupId }.map {
            ScheduleResponse(
                id = it[schedules.id],
                groupId = it[schedules.groupId],
                subject = it[schedules.subject],
                room = it[schedules.room],
                teacher = it[schedules.teacher],
                time = it[schedules.time],
                dayOfWeek = it[schedules.dayOfWeek].name
            )
        }
    }

    override fun insertSchedule(schedule: ScheduleRequest): Int = transaction(db) {
        val insertedId = schedules.insert {
            it[groupId] = schedule.groupId
            it[subject] = schedule.subject
            it[room] = schedule.room
            it[teacher] = schedule.teacher
            it[time] = schedule.time
            it[dayOfWeek] = DayOfWeek.valueOf(schedule.dayOfWeek)
        } get schedules.id

        insertedId!!
    }


    override fun getAllSchedules(): List<ScheduleResponse> = transaction(db) {
        schedules.selectAll().map {
            ScheduleResponse(
                id = it[schedules.id],
                groupId = it[schedules.groupId],
                subject = it[schedules.subject],
                room = it[schedules.room],
                teacher = it[schedules.teacher],
                time = it[schedules.time],
                dayOfWeek = it[schedules.dayOfWeek].name
            )
        }
    }
}
