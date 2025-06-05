package com.example.mobile.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Preview
@Composable
fun Shedule_Screen_Prev(){
    Shedule_Screen(navController = rememberNavController())
}





@Composable
fun Shedule_Screen(navController: NavHostController){
    Column (modifier = Modifier.fillMaxSize()) {
        Subject("Математика", "Г-207", 100, "Иванов Иван Иванович", "9:00-10:30")
        Spacer(modifier = Modifier.padding(10.dp))
        chillTime("10 минут")
        Spacer(modifier = Modifier.padding(10.dp))
        Subject("Математика", "Г-207", 100, "Иванов Иван Иванович", "9:00-10:30")

    }
}




@Composable
fun Subject(subject:String,   room:String,    mark:Int,   teacher:String    ,time:String){

    Column(modifier = Modifier.fillMaxWidth().border(width = 2.dp, color = Color.Black).padding(4.dp)) {
        Text(text=subject)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Text(text=room)

            Text(text=mark.toString())
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Text(text=teacher)
            Text(text=time)
        }
    }


}

@Composable
fun chillTime(time:String){
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text=time)

    }


}