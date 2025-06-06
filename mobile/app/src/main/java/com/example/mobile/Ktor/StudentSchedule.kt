package com.example.mobile.Ktor

import com.example.myapplication.Ktor.KtorClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import kotlinx.serialization.Serializable



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


class StudentSchedule{
    suspend fun getScheduleByGroup(groupId: Int): Result<List<ScheduleResponse>> {
        return try {
            val response = KtorClient.instance.get("http://10.0.2.2:8080/schedule/$groupId")
            if (response.status.isSuccess()) {
                val schedule = response.body<List<ScheduleResponse>>()
                Result.success(schedule)
            } else {
                Result.failure(Exception("Ошибка: ${response.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
