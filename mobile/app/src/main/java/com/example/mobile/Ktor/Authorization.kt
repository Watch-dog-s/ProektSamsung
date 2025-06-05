package com.example.myapplication.Ktor

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
    val id : Int,
    val group_id : Int

)



class Login()
{

    var login by mutableStateOf("")
    var password by mutableStateOf("")

    fun onPasswordChange(newPassword : String)
    {
        password = newPassword
        println(password)
    }


    fun onLoginChange(NLoggin:String){
        login=NLoggin
        println(login)
    }

    fun onLoginButtonClick( reg1: LoginViewModel){

        val request = LoginRequest(login, password)

        GlobalScope.launch {
            val result = LoginRequest(request)

            result.onSuccess { token -> Log.i("requestAnswer" , token);  reg1.changeTrue()}.onFailure { error -> println(error.message);reg1.changeFalse() }
            Log.d("ViewModel",reg1.success.value.toString())

        }
    }

    suspend fun LoginRequest(request: LoginRequest) : Result<String>


    {
        val response = KtorClient.instance.post("http://10.0.2.2:8080/login"){
            contentType(ContentType.Application.Json)
            setBody(request)
        }

        return if (response.status.isSuccess()) {
            try{

                val LoginResponse = response.body<LoginResponse>()
                println(LoginResponse.token)
                Result.success(LoginResponse.token)
            }
            catch(e : SerializationException)
            {
                Result.failure(Exception("whfwhjfhwwjf"))
            }
        }
        else
        {
            Result.failure(Exception(response.bodyAsText()))
        }
    }
}