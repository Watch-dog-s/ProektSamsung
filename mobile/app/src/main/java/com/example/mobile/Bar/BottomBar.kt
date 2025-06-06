package com.example.mobile.Bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobile.R
import com.example.mobile.navigation.Routes


@Composable
fun BottomBar(navController: NavController) {

    BottomAppBar(

        actions = {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(onClick = {navController.navigate(Routes.SHEDULE) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.schedule),
                        contentDescription = "",
                        modifier = Modifier.size(60.dp)
                    )
                }



                IconButton(onClick = {navController.navigate(Routes.MARKS) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.marks),
                        contentDescription = "",
                        modifier = Modifier.size(60.dp)
                    )
                }

                IconButton(onClick = { navController.navigate(Routes.HOME) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.home),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

        },

        )


}
