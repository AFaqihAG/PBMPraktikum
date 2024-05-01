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