package com.example.quiz

import android.app.Application
import com.example.quiz.ui.DataBase

class MyApplication : Application() {

    init {
        DataBase.initDataBase(applicationContext)
    }
}