@Composable
fun MainScreen() {
    val navController = /* TODO : Add navigation controller */

    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(
            navController = navController,
            startDestination = /* TODO : Add composable name start */,
            // modifier = Modifier.padding(innerPadding)
        ) {
            composable(/* TODO : Add composable name  show_student_data */) { ShowStudentData() }
            compsable(/* TODO : Add Composable name about */) { About(navController) }
        }
    }
}