package com.example.mobile.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Preview
@Composable
fun Home_Screen_prev(){
    Home_Screen(navController = rememberNavController())
}

@Composable
fun Home_Screen(navController: NavHostController) {Text(text="A2")}