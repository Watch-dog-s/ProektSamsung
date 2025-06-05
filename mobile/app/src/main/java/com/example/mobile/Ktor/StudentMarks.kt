package com.example.mobile.Ktor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.mobile.ViewModels.StudentViewModel
import com.example.myapplication.Ktor.KtorClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class StudentMarks {

    var studentId by mutableStateOf(0)
    var groupId by mutableStateOf(0)

    fun onStudentIdChange(newId: Int) {
        studentId = newId
        println("studentId: $studentId")
    }

    fun onGroupIdChange(newGroupId: Int) {
        groupId = newGroupId
        println("groupId: $groupId")
    }

    fun onLoadMarksButtonClick(viewModel: StudentViewModel) {
        GlobalScope.launch {
            val result = getStudentMarks(studentId)
            result
                .onSuccess { marks ->
                    viewModel.setMarks(marks)
                    println("Загружено ${marks.size} оценок")
                }
                .onFailure { error ->
                    println("Ошибка при загрузке оценок: ${error.message}")
                    viewModel.setError(error.message ?: "Неизвестная ошибка")
                }
        }
    }

    suspend fun getStudentMarks(studentId: Int): Result<List<MarkResponse>> {
        return try {
            val response = KtorClient.instance.get("http://10.0.2.2:8080/students/$studentId/marks") {
                contentType(ContentType.Application.Json)
            }

            if (response.status.isSuccess()) {
                val marks = response.body<List<MarkResponse>>()
                Result.success(marks)
            } else {
                Result.failure(Exception("Ошибка ответа: ${response.status} - ${response.bodyAsText()}"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
