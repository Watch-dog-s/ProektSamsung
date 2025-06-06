package com.example.mobile.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobile.Ktor.ScheduleResponse
import com.example.mobile.Ktor.StudentSchedule
import com.example.mobile.ViewModels.StudentViewModel


@Preview
@Composable
fun Shedule_Screen_Prev(){
    //Shedule_Screen(navController = rememberNavController())
}





@Composable
fun Shedule_Screen(navController: NavHostController, studentViewModel: StudentViewModel) {
    val groupId = studentViewModel.groupId.value
    var scheduleList by remember { mutableStateOf<List<ScheduleResponse>>(emptyList()) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(groupId) {
        if (groupId != null) {
            val result = StudentSchedule().getScheduleByGroup(groupId)
            result.onSuccess { scheduleList = it }
                .onFailure { error = it.message }
        }
    }

    if (error != null) {
        Text("Ошибка: $error", color = Color.Red)
        return
    }

    val grouped = scheduleList.groupBy { it.dayOfWeek }

    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        grouped.forEach { (day, items) ->
            item {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(250.dp)
                ) {
                    Text(
                        text = day,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    items.forEach { schedule ->
                        ScheduleCard(schedule)
                    }
                }
            }
        }
    }
}





@Composable
fun ScheduleCard(schedule: ScheduleResponse) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),

    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = schedule.subject, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "Время: ${schedule.time}")
            Text(text = "Аудитория: ${schedule.room}")
            Text(text = "Преподаватель: ${schedule.teacher}")
        }
    }
}
