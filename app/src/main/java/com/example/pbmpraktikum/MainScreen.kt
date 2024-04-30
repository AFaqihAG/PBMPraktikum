package com.example.pbmpraktikum

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pbmpraktikum.ui.screen.About
import com.example.pbmpraktikum.ui.screen.ShowStudentData
import com.example.pbmpraktikum.ui.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val userViewModel: UserViewModel = viewModel(factory = UserViewModel.factory)

    ModalNavigationDrawer(
        drawerContent = {
            SideBar(navController, scope, currentDestination, drawerState)
        },
        drawerState = drawerState,
    ) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButtonContent(scope, drawerState)
            }
        ) { innerPadding ->
            NavigationScreenContent(navController, innerPadding, userViewModel)
        }
    }
}

@Composable
private fun NavigationScreenContent(
    navController: NavHostController,
    innerPadding: PaddingValues,
    userViewModel: UserViewModel
) {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(
            navController = navController,
            startDestination = "show_student_data",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("show_student_data") { ShowStudentData(userViewModel) }
            composable("about") { About(navController) }
        }
    }
}

@Composable
private fun FloatingActionButtonContent(
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    ExtendedFloatingActionButton(
        text = { Text(
            text ="Menu",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelMedium
        ) },
        icon = { Icon(Icons.Filled.Add, contentDescription = "") },
        onClick = {
            scope.launch {
                drawerState.apply {
                    if (isClosed) open() else close()
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        elevation = FloatingActionButtonDefaults.elevation(8.dp),
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = CircleShape
            )
            .clip(CircleShape)
    )
}

@Composable
private fun SideBar(
    navController: NavHostController,
    scope: CoroutineScope,
    currentDestination: String?,
    drawerState: DrawerState
) {
    ModalDrawerSheet(
        drawerContainerColor = MaterialTheme.colorScheme.background,
        drawerContentColor = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.fillMaxWidth(0.5f)
    ) {
        Text(
            text ="Main Menu",
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp),
        )
        Divider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.secondary
        )
        SideBarItem(
            navController,
            scope,
            currentDestination,
            drawerState,
            "Main Content",
            "show_student_data"
        )
        Divider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.secondary
        )
        SideBarItem(
            navController,
            scope,
            currentDestination,
            drawerState,
            "About",
            "about"
        )
        Divider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun SideBarItem(
    navController: NavHostController,
    scope: CoroutineScope,
    currentDestination: String?,
    drawerState: DrawerState,
    name: String,
    destination: String
) {
    NavigationDrawerItem(
        label = {
            Text(
                text = name,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium
            ) },
        selected = false,
        onClick = {
            navigateTo(navController, scope, currentDestination, destination, drawerState)
        },
        shape = RectangleShape
    )
}

fun navigateTo(navController: NavController, scope: CoroutineScope, currentDestination: String?, destination: String, drawerState: DrawerState) {
    scope.launch {
        if (currentDestination != destination) {
            navController.navigate(destination)
        }
        drawerState.apply {
            if (isClosed) open() else close()
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun FloatingActionButtonPreview() {
    FloatingActionButtonContent(scope = rememberCoroutineScope(), rememberDrawerState(initialValue = DrawerValue.Closed))
}