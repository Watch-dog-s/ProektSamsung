package com.example.mobile.ROOM

import com.example.mobile.Ktor.ScheduleResponse

fun scheduleResponseToEntity(response: ScheduleResponse, groupId: Int): ScheduleEntity {
    return ScheduleEntity(
        id = response.id,
        groupId = groupId,
        subject = response.subject,
        room = response.room,
        teacher = response.teacher,
        time = response.time,
        dayOfWeek = response.dayOfWeek
    )
}

fun scheduleEntityToResponse(entity: ScheduleEntity): ScheduleResponse {
    return ScheduleResponse(
        id = entity.id,
        groupId = entity.groupId,
        subject = entity.subject,
        room = entity.room,
        teacher = entity.teacher,
        time = entity.time,
        dayOfWeek = entity.dayOfWeek
    )
}
