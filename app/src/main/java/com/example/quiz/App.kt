//package com.example.quiz
//
//import android.app.Application
//import com.example.quiz.di.AppComponent
//import com.example.quiz.di.DaggerAppComponent
//
//
//class App : Application() {
//
//
//    override fun onCreate() {
//        super.onCreate()
//        appComponent =
//            DaggerAppComponent.builder().build()
//    }
//
//    companion object {
//        lateinit var appComponent: AppComponent
//    }
//}