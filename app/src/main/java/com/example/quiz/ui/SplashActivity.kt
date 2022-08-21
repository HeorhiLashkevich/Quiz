package com.example.quiz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quiz.R
import com.example.quiz.di.SharedPreferencesRepository


class SplashActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBase.initDataBase(this)
        sharedPreferences = SharedPreferencesRepository(this, "")
        setContentView(R.layout.splash_activity)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SignUpFragment())
            .commit()
    }
}