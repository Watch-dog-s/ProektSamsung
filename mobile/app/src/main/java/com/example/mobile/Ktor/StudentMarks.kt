package com.example.mobile.Ktor

import com.example.myapplication.Ktor.KtorClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.serialization.Serializable



@Serializable
data class MarkData(
    val id: Int,
    val studentId: Int,
    val subject: String,
    val mark: Int,
    val comment: String?,
    val teacher: String?,
    val type: String
)



class StudentMark {
    suspend fun downloadMarks(studentId: Int): Result<List<MarkData>> {
        return try {
            val response = KtorClient.instance.get("http://10.0.2.2:8080/students/$studentId/marks")
            if (response.status.isSuccess()) {
                val marks: List<MarkData> = response.body()
                Result.success(marks)
            } else {
                Result.failure(Exception(response.bodyAsText()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
