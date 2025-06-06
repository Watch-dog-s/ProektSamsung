package com.example.myapplication.Ktor

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.mobile.ViewModels.StudentViewModel
import com.example.myapplication.ViewModels.LoginViewModel

import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException


@Serializable
data class LoginRequest(
    val login : String,
    val password : String
)



@kotlinx.serialization.Serializable
data class LoginResponse(
    val token : String,
    @SerialName("studentId")
    val id : Int,
    @SerialName("groupId")
    val group_id : Int,
    val type: String

)



class Login {

    var login by mutableStateOf("")
    var password by mutableStateOf("")

    fun onPasswordChange(newPassword: String) {
        password = newPassword
        println(password)
    }

    fun onLoginChange(NLoggin: String) {
        login = NLoggin
        println(login)
    }

    fun onLoginButtonClick(reg1: LoginViewModel, studentViewModel: StudentViewModel) {
        val request = LoginRequest(login, password)

        reg1.viewModelScope.launch {
            val result = LoginRequest(request)

            result.onSuccess { loginResponse ->
                Log.i("requestAnswer", loginResponse.token)
                reg1.changeTrue()
                studentViewModel.saveUserInfo(
                    id = loginResponse.id,
                    groupId = loginResponse.group_id,
                    type = loginResponse.type
                )
            }.onFailure { error ->
                println(error.message)
                reg1.changeFalse()
            }

            Log.d("ViewModel", reg1.success.value.toString())
        }
    }

    suspend fun LoginRequest(request: LoginRequest): Result<LoginResponse> {
        return try {
            val response = KtorClient.instance.post("http://10.0.2.2:8080/login") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }

            if (response.status.isSuccess()) {
                val loginResponse = response.body<LoginResponse>()
                println(loginResponse.token)
                Result.success(loginResponse)
            } else {
                Result.failure(Exception(response.bodyAsText()))
            }

        } catch (e: SerializationException) {
            Result.failure(Exception("Ошибка сериализации"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
