package com.example.quiz.repository

import com.example.quiz.model.User
import com.example.quiz.ui.DataBase


class UserRepository {


    suspend fun insertUser(user: User) {
        DataBase.userDao?.insertUser(user)
    }


    suspend fun deleteUser(user: User) {
        DataBase.userDao?.deleteUser(user)
    }

    suspend fun getAllUsers() {
        DataBase.userDao?.getAllUsers()
    }

    suspend fun findUser(email: String, password: String): User? {
        return DataBase.userDao?.findUserForLogin(email, password)
    }

    suspend fun findCurrentUser(email: String): User? {
        return DataBase.userDao?.currentUser(email)
    }


}