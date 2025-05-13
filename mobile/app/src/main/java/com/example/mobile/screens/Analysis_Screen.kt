package com.example.mobile.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Preview
@Composable
fun Analysis_Screen_prev(){
    Analysis_Screen(navController = rememberNavController())
}


@Composable
fun Analysis_Screen(navController: NavHostController){}