package com.example.mobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mobile.screens.Analysis_Screen
import com.example.mobile.screens.Authorization_Screen
import com.example.mobile.screens.Home_Screen
import com.example.mobile.screens.Marks_Screen
import com.example.mobile.screens.Password_recovery_Screen
import com.example.mobile.screens.Shedule_Screen

@Composable
fun navigation(nav_controller: NavHostController)
{
    NavHost(
        navController = nav_controller,
        startDestination = Routes.LOGIN) {

        composable(Routes.SHEDULE){Shedule_Screen(nav_controller)}
        composable(Routes.LOGIN){ Authorization_Screen(nav_controller) }
        composable(Routes.HOME){ Home_Screen(nav_controller) }
        composable(Routes.ANALYSIS){ Analysis_Screen(nav_controller) }
        composable(Routes.PASSWORD_RECOVERY){ Password_recovery_Screen(nav_controller) }
        composable(Routes.MARKS){ Marks_Screen(nav_controller) }



    }
}