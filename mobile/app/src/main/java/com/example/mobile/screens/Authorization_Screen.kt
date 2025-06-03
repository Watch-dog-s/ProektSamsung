package com.example.mobile.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobile.navigation.Routes
import com.example.myapplication.Ktor.Login
import com.example.myapplication.ViewModels.LoginViewModel


@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
fun Authorization_prev(){
    val loginViewModel=LoginViewModel()
    Authorization_Screen(navController = rememberNavController(),loginViewModel)
}


@Composable
fun Authorization_Screen(navController: NavHostController,loginViewModel: LoginViewModel) {
    val loginAPI = remember { Login() }
    val loginSuccess by loginViewModel.success.collectAsState()


    LaunchedEffect(loginSuccess) {
        if (loginSuccess) {
            navController.navigate(Routes.HOME)
        }
    }

    Column(modifier = Modifier.fillMaxSize())
    {
        Text(text="Вход")


        Spacer(modifier=Modifier.padding(60.dp))
        TextField(value = loginAPI.login, onValueChange = {newLogin->loginAPI.onLoginChange(newLogin)}, label = {"Логин"})
        Spacer(modifier=Modifier.padding(20.dp))
        TextField(value = loginAPI.password, onValueChange = {newPassword->loginAPI.onPasswordChange(newPassword)}, label = {"Пароль"})


        Spacer(modifier=Modifier.padding(20.dp))
        Button(onClick = { loginAPI.onLoginButtonClick(loginViewModel) }){Text(text="Войти")}



    }

}