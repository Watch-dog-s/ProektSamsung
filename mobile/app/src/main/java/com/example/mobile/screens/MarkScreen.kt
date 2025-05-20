package com.example.mobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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



@Preview
@Composable
fun Marks_Screen_prev(){
    Marks_Screen(navController = rememberNavController())
}

enum class Type { HomeWork, Answer, Test, MegaTEst }

data class MarkData(
    var a:Notification,
    var mark:Int,
    var Type1:Type,
    var comment:String,
    var Teacher: String,
    var Subject: String

)

@Composable
fun Marks_Screen(navController: NavHostController) {
    val allMarksBySubject = remember  { mutableStateListOf<MarkData>() }

    var n = Notification(A.Good, B.Mark)
    var t = Type.Answer
    var mark1 = MarkData(n, 5, t, "Отлично", "Иванов", "Математика")

    allMarksBySubject.add(mark1)
    allMarksBySubject.add(mark1)
    allMarksBySubject.add(mark1)
    allMarksBySubject.add(mark1)
    allMarksBySubject.add(mark1)

    var AllSubject= listOf("Матан","Линал")


    val grouped = allMarksBySubject.groupBy { it.Subject }



    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(AllSubject) { subject ->

            Text(
                text = subject,
                modifier = Modifier.padding(8.dp)
            )

            FullMarkCard(grouped[subject] ?: emptyList())

        }
    }
}



@Composable
fun FullMarkCard(marks: List<MarkData>) {
    LazyRow(modifier = Modifier.padding(start = 8.dp, bottom = 16.dp)) {
        items(marks) { mark ->
            Mark(mark)
        }
    }
}



@Composable
fun Mark(markData: MarkData) {
    Card(
        modifier = Modifier
            .size(width = 60.dp, height = 80.dp)
            .padding(4.dp)
            .clickable(onClick = {})
            .background(Color.Red)
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