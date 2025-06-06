package com.example.mobile.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobile.Ktor.ScheduleResponse
import com.example.mobile.Ktor.StudentSchedule
import com.example.mobile.ROOM.AppDatabase
import com.example.mobile.ROOM.ScheduleEntity
import com.example.mobile.ROOM.scheduleEntityToResponse
import com.example.mobile.ROOM.scheduleResponseToEntity
import com.example.mobile.ViewModels.StudentViewModel
import android.util.Log




@Composable
fun Shedule_Screen(navController: NavHostController, studentViewModel: StudentViewModel) {
    val context = LocalContext.current
    val database = remember { AppDatabase.getInstance(context) }
    val dao = remember { database.scheduleDao() }

    var scheduleList by remember { mutableStateOf<List<ScheduleEntity>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    val api = remember { StudentSchedule() }
    val groupId = studentViewModel.groupId.value ?: 2

    LaunchedEffect(groupId) {


        try {

            val remoteList = api.getScheduleByGroup(groupId).getOrThrow()
            
            val entities = remoteList.map {
                scheduleResponseToEntity(it, groupId)
            }

            dao.deleteByGroup(groupId)
            dao.insertAll(entities)
            scheduleList = entities

            Log.d("ScheduleScreen", "Обновлен ${entities.size} записей")
        } catch (e: Exception) {

            val cached = dao.getScheduleByGroup(groupId)
            scheduleList = cached



            error = "Ошибка: ${e.message}"
        } finally {
            isLoading = false
            Log.d("ScheduleScreen", isLoading.toString())
        }
    }

    when {
        isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        scheduleList.isEmpty() -> {
            Text("Нет данных", modifier = Modifier.padding(16.dp))
        }

        else -> {
            val grouped = scheduleList.map { scheduleEntityToResponse(it) }
                .groupBy { it.dayOfWeek }

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
