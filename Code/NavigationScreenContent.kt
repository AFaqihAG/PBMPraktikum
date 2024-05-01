@Composable
private fun NavigationScreenContent(
    navController: NavHostController,
    innerPadding: PaddingValues,
) {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(
            navController = navController,
            startDestination = "show_student_data",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("show_student_data") { ShowStudentData() }
            composable("about") { About(navController) }
        }
    }
}