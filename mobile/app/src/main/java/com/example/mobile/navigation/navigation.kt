package com.example.mobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mobile.ROOM.AppDatabase
import com.example.mobile.ViewModels.StudentViewModel
import com.example.mobile.ViewModels.ThemeViewModel
import com.example.mobile.screens.Authorization_Screen
import com.example.mobile.screens.Home_Screen
import com.example.mobile.screens.Marks_Screen
import com.example.mobile.screens.Shedule_Screen
import com.example.myapplication.ViewModels.LoginViewModel

@Composable
fun navigation(nav_controller: NavHostController,loginViewModel: LoginViewModel,studentViewModel: StudentViewModel,themeViewModel: ThemeViewModel,scheduleDatabase: AppDatabase)
{
    NavHost(
        navController = nav_controller,
        startDestination = Routes.LOGIN) {

        composable(Routes.SHEDULE){Shedule_Screen(nav_controller,studentViewModel,)}
        composable(Routes.LOGIN){ Authorization_Screen(nav_controller,loginViewModel,studentViewModel) }
        composable(Routes.HOME){ Home_Screen(   navController = nav_controller, themeViewModel = themeViewModel,
            onLogout = {
                loginViewModel.changeFalse()
                nav_controller.navigate(Routes.LOGIN) {
                    popUpTo(Routes.HOME) { inclusive = true }
                }
            }) }
        composable(Routes.MARKS){ Marks_Screen(nav_controller,studentViewModel) }



    }
}