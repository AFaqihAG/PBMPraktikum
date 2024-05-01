@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    
    ModalNavigationDrawer(
        drawerContent = {
            SideBar(
                navController,
                scope,
                currentDestination,
                drawerState
            )
        },
        drawerState = drawerState,
    ) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButtonContent(scope, drawerState)
            }
        ) { innerPadding ->
            NavigationScreenContent(navController, innerPadding)
        }
    }
}