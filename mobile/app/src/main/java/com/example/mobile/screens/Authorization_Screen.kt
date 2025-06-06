package com.example.mobile.screens

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobile.ViewModels.StudentViewModel
import com.example.mobile.navigation.Routes
import com.example.myapplication.Ktor.Login
import com.example.myapplication.ViewModels.LoginViewModel
import kotlinx.coroutines.delay


@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
fun Authorization_prev(){
    val loginViewModel=LoginViewModel()
  //  Authorization_Screen(navController = rememberNavController(),loginViewModel)
}


@Composable
fun Authorization_Screen(navController: NavHostController,loginViewModel: LoginViewModel,studentViewModel: StudentViewModel) {
    val loginAPI = remember { Login() }
    val context = LocalContext.current
    var colorText by remember { mutableStateOf(Color.White) }

    val loginSuccess by loginViewModel.success.collectAsState()


    LaunchedEffect(loginSuccess) {
        if (loginSuccess) {
            navController.navigate(Routes.HOME)
        }

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Вход")

        Spacer(modifier = Modifier.padding(40.dp))

        TextField(
            value = loginAPI.login,
            onValueChange = { newLogin -> loginAPI.onLoginChange(newLogin) },
            label = { Text("Логин") },
            placeholder = { Text("Введите логин") }
        )

        Spacer(modifier = Modifier.padding(20.dp))

        TextField(
            value = loginAPI.password,
            onValueChange = { newPassword -> loginAPI.onPasswordChange(newPassword) },
            label = { Text("Пароль") },
            placeholder = { Text("Введите пароль") }
        )

        Spacer(modifier = Modifier.padding(20.dp))

        Button(onClick = { loginAPI.onLoginButtonClick(loginViewModel, studentViewModel)
             if (!loginSuccess) {colorText=Color.Red}


        }) {
            Text(text = "Войти")
        }

        Text(text="неверный логин или пароль",color=colorText)

    }


}