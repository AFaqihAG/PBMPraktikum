package com.example.pbmpraktikum

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pbmpraktikum.ui.screen.About
import com.example.pbmpraktikum.ui.screen.ShowStudentData

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(
            navController = navController,
            startDestination = "show_student_data",
//            modifier = Modifier.padding(innerPadding)
        ) {
            composable("show_student_data") { ShowStudentData() }
            composable("about") { About(navController) }
        }
    }
}

