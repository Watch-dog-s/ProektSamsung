package com.example.mobile.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Preview
@Composable
fun Shedule_Screen_Prev(){
    Shedule_Screen(navController = rememberNavController())
}

@Composable
fun Shedule_Screen(navController: NavHostController){
    Text(text = "Shedule")
}