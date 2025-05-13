package com.example.mobile.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobile.navigation.Routes


@Preview
@Composable
fun Password_Recovery_Screen_Prev(){
    Password_recovery_Screen(navController = rememberNavController())
}


@Composable
fun Password_recovery_Screen(navController: NavHostController){
    Column(modifier = Modifier.fillMaxSize()) {

        Text(text = "Восстановление пароля");

        Spacer(modifier = Modifier.height(15.dp))
        TextField(value = "Логин", onValueChange ={} )



        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = {navController.navigate(Routes.LOGIN)}) { Text(text="Восстановить") }


    }
}