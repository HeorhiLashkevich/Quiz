package com.example.quiz.ui

import android.content.Context
import androidx.room.Room


object DataBase {

    private var db: AppDataBase? = null
    var userDao: UserDao? = null

    fun initDataBase(context: Context) {
        val db = Room.databaseBuilder(
            context,
            AppDataBase::class.java, "database-name"
        )
            .fallbackToDestructiveMigration()
            .build()

        userDao = db.getUserDao()

    }


}