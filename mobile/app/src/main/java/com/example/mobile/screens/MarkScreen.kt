package com.example.mobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


enum class Type { HomeWork, Answer, Test, ControlWork }

data class MarkData(
    var a:Notification,
    var mark:Int,
    var Type1:Type,
    var comment:String,
    var Teacher: String,
    var Subject: String

)



@Composable
fun Marks_Screen( navController: NavHostController, viewModel: MarksViewModel ) {


    val grouped = allMarks.groupBy { it.subject }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        grouped.forEach { (subject, marks) ->
            item {
                Text(
                    text = subject,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                FullMarkCard(marks)
            }
        }
    }
}

@Composable
fun FullMarkCard(marks: List<MarkData>) {
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
            .size(width = 60.dp, height = 80.dp)
            .padding(4.dp)
            .clickable { /* TODO: navigate or show detail */ }
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = markData.mark.toString())
            Text(text = markData.comment, fontSize = 10.sp)
        }
    }
}

