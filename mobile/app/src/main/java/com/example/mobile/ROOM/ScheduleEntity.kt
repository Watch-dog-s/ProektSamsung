package com.example.mobile.ROOM



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class ScheduleEntity(
    @PrimaryKey val id: Int,
    val groupId: Int,
    val subject: String,
    val room: String,
    val teacher: String,
    val time: String,
    val dayOfWeek: String
)
