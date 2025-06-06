package com.example.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.mobile.Ktor.StudentSchedule
import com.example.mobile.ROOM.AppDatabase
import com.example.mobile.ROOM.ScheduleRepository
import com.example.mobile.ViewModels.StudentViewModel
import com.example.mobile.ViewModels.ThemeViewModel
import com.example.mobile.screens.MainScreen
import com.example.mobile.ui.theme.MobileTheme
import com.example.myapplication.ViewModels.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var ScheduleDataBase: AppDatabase
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        val loginViewModel = LoginViewModel()
        val studentViewModel = StudentViewModel()
        val themeViewModel = ThemeViewModel()



        ScheduleDataBase = AppDatabase.getInstance(this)
        setContent {

            val isDarkTheme by themeViewModel.isDarkTheme

            MobileTheme(darkTheme = isDarkTheme) {
                val navController = rememberNavController()
                MainScreen(navController = navController, loginViewModel = loginViewModel, studentViewModel = studentViewModel, themeViewModel = themeViewModel,ScheduleDataBase)
            }
        }
    }
}

