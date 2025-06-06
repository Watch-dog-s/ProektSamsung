package com.example.mobile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.mobile.ViewModels.ThemeViewModel


@Composable
fun Home_Screen(
    navController: NavHostController,
    themeViewModel: ThemeViewModel,
    onLogout: () -> Unit
) {
    val isDarkTheme by themeViewModel.isDarkTheme

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isDarkTheme) Color.Black else Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            val bitmapImage = ImageBitmap.imageResource(id = R.drawable.image1)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { themeViewModel.toggleTheme() }) {
                    Text("Сменить тему")
                }
                Button(onClick = onLogout) {
                    Text("Выход")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(8.dp)
                ) {
                    Text(
                        text = "ФИО",
                        color = if (isDarkTheme) Color.White else Color.Black,
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    Image(
                        bitmap = bitmapImage,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray)
                            .padding(top = 3.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}
