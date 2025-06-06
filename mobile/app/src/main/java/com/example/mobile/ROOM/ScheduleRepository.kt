package com.example.mobile.ROOM

import com.example.mobile.Ktor.StudentSchedule

class ScheduleRepository(
    private val api: StudentSchedule,
    private val dao: ScheduleDao
) {
    suspend fun getSchedule(groupId: Int): List<ScheduleEntity> {
        return try {
            val result = api.getScheduleByGroup(groupId)
            result.fold(
                onSuccess = { remoteList ->
                    val entities = remoteList.map {
                        ScheduleEntity(
                            id = it.id,
                            groupId = groupId,
                            subject = it.subject,
                            room = it.room,
                            teacher = it.teacher,
                            time = it.time,
                            dayOfWeek = it.dayOfWeek
                        )
                    }
                    dao.deleteByGroup(groupId)
                    dao.insertAll(entities)
                    entities
                },
                onFailure = {
                    dao.getScheduleByGroup(groupId)
                }
            )
        } catch (e: Exception) {
            dao.getScheduleByGroup(groupId)
        }
    }
}
