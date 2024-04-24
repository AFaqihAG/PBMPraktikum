class UserViewModel(/* TODO : Add Parameter UserDao */): ViewModel() {
    suspend fun addUser(user: User) = /* TODO : Add UserDao Implementation */

    suspend fun getAllUsers() = /* TODO : Add UserDao Implementation */

    suspend fun deleteAllUsers() = /* TODO : Add UserDao Implementation */

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DatabaseApplication)
                UserViewModel(application.database.userDao())
            }
        }
    }
}
