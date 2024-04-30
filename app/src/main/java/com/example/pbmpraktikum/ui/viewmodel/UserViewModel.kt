package com.example.pbmpraktikum.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pbmpraktikum.ui.database.DatabaseApplication
import com.example.pbmpraktikum.ui.database.User
import com.example.pbmpraktikum.ui.database.UserDao

class UserViewModel(private val userDao: UserDao): ViewModel() {
    suspend fun addUser(user: User) = userDao.insertUser(user)

    suspend fun getAllUsers() = userDao.getAllUsers()

    suspend fun deleteAllUsers() = userDao.deleteAllUsers()

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DatabaseApplication)
                UserViewModel(application.database.userDao())
            }
        }
    }
}