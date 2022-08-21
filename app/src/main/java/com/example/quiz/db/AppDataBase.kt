package com.example.quiz.ui

import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.quiz.model.User


@Database(entities = [ User::class], version = 3)
abstract class AppDataBase : RoomDatabase() {


    abstract fun getUserDao(): UserDao

}