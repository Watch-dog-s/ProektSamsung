package com.example.mobile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobile.R


@Preview
@Composable
fun Home_Screen_prev(){
    Home_Screen(navController = rememberNavController())
}



@Composable
fun Home_Screen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {

            val bitmapImage = ImageBitmap.imageResource(id = R.drawable.image1)

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(200.dp)
                    .padding(8.dp)
            ) {
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .size(200.dp)
                    .padding(8.dp))
                {
                    Text(
                        text = "ФИО нашего бэбрика",
                        modifier = Modifier.padding(top = 16.dp).padding(end = 3.dp),
                    )

                    Row(modifier = Modifier.fillMaxSize()) {
                        Image(
                            bitmap = bitmapImage,
                            contentDescription = "Bombardiro Crocadilo",
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.LightGray)
                                .padding(top = 3.dp),
                            contentScale = ContentScale.Crop )

                    }
                }
            }

            val n=Notification(A.Good,B.Mark)

            notification(n)
            notification(n)
            Text(text = "День рождения у ученика ......")
            Text(text = "Турнир по Доте ......")




        }
    }


}





@Composable
fun notification(n:Notification){
    Box(modifier= Modifier.fillMaxWidth()){
        Row(modifier= Modifier.fillMaxWidth()) {
            var icon= if(n.a==A.Good ) painterResource(id = R.drawable.notification) else  painterResource(id = R.drawable.notification)

            Icon(
                painter = icon,
                contentDescription = "",
                modifier = Modifier.size(40.dp)
            )
            Text(text = "Вы получили новую оценку")
        }
    }
}

