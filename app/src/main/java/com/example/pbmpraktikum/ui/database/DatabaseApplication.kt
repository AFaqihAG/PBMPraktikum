package com.example.pbmpraktikum.ui.database

import android.app.Application

class DatabaseApplication: Application() {
    val database: UserDatabase by lazy {UserDatabase.getDatabase(this)}
}