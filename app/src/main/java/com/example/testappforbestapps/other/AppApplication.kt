package com.example.testappforbestapps.other

import android.app.Application
import com.example.testappforbestapps.data.db.AppDataBase
import com.example.testappforbestapps.data.repository.Repository

class AppApplication : Application() {
    val database by lazy { AppDataBase.getDatabase(this) }
    val repository by lazy { Repository(database.getDao()) }
}