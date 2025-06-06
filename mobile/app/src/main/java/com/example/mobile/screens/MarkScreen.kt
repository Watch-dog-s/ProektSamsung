package com.example.mobile.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobile.Ktor.MarkData
import com.example.mobile.Ktor.StudentMark
import com.example.mobile.ViewModels.StudentViewModel


@Composable
fun Marks_Screen(navController: NavHostController, viewModel: StudentViewModel) {

    val studentId = viewModel.userId.value
    Log.d("userId",studentId.toString())
    var marks by remember { mutableStateOf<List<MarkData>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }



    LaunchedEffect(studentId) {
        if (studentId != null) {
            val result = StudentMark().downloadMarks(studentId)
            result.onSuccess {
                marks = it
                isLoading = false
            }.onFailure {
                error = it.message
                isLoading = false
            }
        } else {
            error = "Не найден ID пользователя"
            isLoading = false
        }
    }

    when {
        isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(" $error")
            }
        }

        else -> {
            val grouped = marks.groupBy { it.subject }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                grouped.forEach { (subject, subjectMarks) ->
                    item {
                        Text(
                            text = subject,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        FullMarkRow(subjectMarks)
                    }
                }
            }
        }
    }
}




@Composable
fun FullMarkRow(marks: List<MarkData>) {
    LazyRow(modifier = Modifier.padding(start = 8.dp, bottom = 16.dp)) {
        items(marks) { mark ->
            MarkCard(mark)
        }
    }
}





@Composable
fun MarkCard(markData: MarkData) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .padding(4.dp)
            .clickable { },
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(text = "Оценка: ${markData.mark}")
            markData.comment?.let {
                Text(text = "Комментарий: $it", fontSize = 12.sp)
            }
            Text(text = "Тип: ${markData.type}", fontSize = 12.sp)
            markData.teacher?.let {
                Text(text = "Учитель: $it", fontSize = 12.sp)
            }
        }
    }
}


